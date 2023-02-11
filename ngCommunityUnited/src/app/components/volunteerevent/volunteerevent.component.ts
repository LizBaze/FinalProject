import { ParticipantService } from './../../services/participant.service';
import { VolunteereventService } from './../../services/volunteerevent.service';
import { Volunteerevent } from './../../models/volunteerevent';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ParticipantComponent } from '../participant/participant.component';
import { Participant } from 'src/app/models/participant';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { GroupMessageService } from 'src/app/services/group-message.service';
import { GroupMessage } from 'src/app/models/group-message';

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
  user: User | null = null;

  newMessage: GroupMessage = new GroupMessage();

  constructor(
    private volunteerEventService: VolunteereventService,
    private route: ActivatedRoute,
    private router: Router,
    private partService: ParticipantService,
    private auth: AuthService,
    private messageService: GroupMessageService
  ) {}

  //add incomplete pipe to constructor after creating update method

  messages: GroupMessage[] | null = null;

  ngOnInit(): void {
    this.getUser();
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
    if(this.selected){
      this.reloadMessage(this.selected.id);
      
    }
  }

  displayAllEvents() {
    this.selected = null;
  }

  reload() {
    this.volunteerEventService.index().subscribe({
      next: (events) => {
        this.events = events;
        console.log(this.events);
      },
      error: (oof) => {
        console.error('Error loading events:');
        console.error(oof);
      },
    });
  }
  show(id: number) {
    this.volunteerEventService.show(id).subscribe({
      next: (volunteerevent) => {
        this.selected = volunteerevent;
      }
    });
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
        this.reload();
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  removeParticipant(id: number){
    this.partService.removeParticipant(id).subscribe({
      next: () => {
        this.show(id);
        this.reload();
      },
      error: (err: any) => {
        console.error(err);
      }
    })
  }

  checkParticipant(){
    let found = undefined;
    if (this.user && this.selected) {
      for (let member of this.selected.participants) {
        console.log(member);
        console.log(this.user);
        console.log(this.selected);
        if (member.user.id === this.user.id) {
          found = true;
          break;
        }
      }
    }
    return found !== undefined;
  }

  checkAdmin(event: Volunteerevent){
    for (let member of event.organization.members) {
      if (this.user && member.user.id === this.user.id && member.admin === true) {
        return true;
      }

  }
  return false;
}

  getUser() {
    if (this.checkLogIn() ) {
      this.auth.getLoggedInUser().subscribe({
        next: (loggedInUser) => {
          console.log(loggedInUser);
          this.user = loggedInUser;
          console.log(this.user);
        },
        error: () => {

          console.error('not logged In');
        },
      });
    }
  }

  checkLogIn() {
    if (this.auth.checkLogin() === true) {
      return true;
    } else {
      return false;
    }
  }

  reloadMessage(id: number){
    this.messageService.reload(id).subscribe({
      next: (messages: GroupMessage[]) => {
        this.messages = messages;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  createMessage(message: GroupMessage, id: number){
    this.messageService.createMessage(message, id).subscribe({
      next: (message: GroupMessage)=>{
        if(this.selected){
          this.reloadMessage(this.selected.id);
        }
      },
      error: (err)=>{
        console.log(err);
      }
    })
  }

  deleteMessage(id: number){
    this.messageService.deleteMessage(id).subscribe({
      next: (deletedMessage) => {
        if(this.selected){
          this.reloadMessage(this.selected.id);
        }
      },
      error:(err) => {
        console.log(err);
      }
    })
  }


  checkMessageOwner(message: GroupMessage){
     if(this.user && message.user.id === this.user.id){
        return true;
     }
     return false;
     
  }


}
