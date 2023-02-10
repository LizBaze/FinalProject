import { ParticipantService } from './../../services/participant.service';
import { VolunteereventService } from './../../services/volunteerevent.service';
import { Volunteerevent } from './../../models/volunteerevent';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ParticipantComponent } from '../participant/participant.component';
import { Participant } from 'src/app/models/participant';

@Component({
  selector: 'app-volunteerevent',
  templateUrl: './volunteerevent.component.html',
  styleUrls: ['./volunteerevent.component.css'],
})
export class VolunteereventComponent implements OnInit {
  events: Volunteerevent[] = [];
  selected: null | Volunteerevent = null;
  newVolunteerevent: Volunteerevent = new Volunteerevent();
  editVolunteerevent: Volunteerevent | null = null;

  constructor(
    private volunteerEventService: VolunteereventService,
    private route: ActivatedRoute,
    private router: Router,
    private partService: ParticipantService
  ) {}

  //add incomplete pipe to constructor after creating update method

  ngOnInit(): void {
    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      let idString = this.route.snapshot.paramMap.get('id');
      if (idString) {
        let id = +idString;
        if (!isNaN(id)) {
          this.volunteerEventService.show(id).subscribe({
            next: (volunteerevent) => {
              this.selected = volunteerevent;
            },
            error: (fail) => {
              console.error(fail);
              this.router.navigateByUrl('volunteereventNotFound');
            },
          });
        } else {
          this.router.navigateByUrl('invalidVolunteereventId');
        }
      }
      console.log('volunteerevent ID: ' + idString);
      // console.log(this.todoService.show(id))
      // this.todoService.show(id).subscribe;
    }

    this.reload();
  }

  displayEvent(volunteerevent: Volunteerevent | null) {
    this.selected = volunteerevent;
  }

  displayAllEvents() {
    this.selected = null;
  }

  reload() {
    this.volunteerEventService.index().subscribe({
      next: (events) => {
        this.events = events;
      },
      error: (oof) => {
        console.error('Error loading events:');
        console.error(oof);
      },
    });
  }
  show(id: number) {
    this.volunteerEventService.show(id).subscribe({});
  }

  setEditEvent() {
    this.editVolunteerevent = Object.assign({}, this.selected);
  }

  updateEvent(volunteerevent: Volunteerevent, goToDetail = true): void {
    console.log(volunteerevent);
    this.volunteerEventService.update(volunteerevent).subscribe({
      next: (updateEvent) => {
        if (goToDetail) {
          this.selected = updateEvent;
        } else {
          this.selected = null;
        }
        this.editVolunteerevent = null;
        this.reload();
      },
      error: (toobad) => {
        console.error('VolunteereventComponent.updateEvent: error updating');
        console.error(toobad);
      },
    });
  }

  addParticipant(id: number){
    this.partService.addParticipant(id).subscribe({
      next: (addedParticipant: Participant) => {
        this.show(id);
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

}
