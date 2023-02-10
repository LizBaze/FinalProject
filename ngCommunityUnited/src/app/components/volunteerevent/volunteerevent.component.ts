import { VolunteereventService } from './../../services/volunteerevent.service';
import { Volunteerevent } from './../../models/volunteerevent';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-volunteerevent',
  templateUrl: './volunteerevent.component.html',
  styleUrls: ['./volunteerevent.component.css']
})
export class VolunteereventComponent implements OnInit {

  events: Volunteerevent[] = [];
  selected: null | Volunteerevent = null;
  newVolunteerevent: Volunteerevent = new Volunteerevent();
  editVolunteerevent: Volunteerevent | null = null;

  constructor(private volunteerEventService:VolunteereventService, private route: ActivatedRoute, private router: Router) {};

  //add incomplete pipe to constructor after creating update method

  ngOnInit(): void {
    if (!this.selected && this.route.snapshot.paramMap.get('id')) {
      let idString = this.route.snapshot.paramMap.get('id');
      if(idString) {
        let id = +idString;
        if(!isNaN(id)) {
          this.volunteerEventService.show(id).subscribe({
            next: (volunteerevent) =>{
              this.selected = volunteerevent;
            },
            error: (fail) => {
              console.error(fail);
              this.router.navigateByUrl('volunteereventNotFound');
            }
          });
        } else {
          this.router.navigateByUrl('invalidVolunteereventId');
        }
      }
      console.log("volunteerevent ID: " + idString)
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
      }
    });

  }
  show(id: number) {
    this.volunteerEventService.show(id).subscribe({
})
}



}
