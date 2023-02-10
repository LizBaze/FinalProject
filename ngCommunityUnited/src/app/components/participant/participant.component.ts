import { Participant } from './../../models/participant';
import { Volunteerevent } from 'src/app/models/volunteerevent';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { ParticipantService } from 'src/app/services/participant.service';
import { VolunteereventService } from 'src/app/services/volunteerevent.service';
import { VolunteereventComponent } from '../volunteerevent/volunteerevent.component';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {
  ngOnInit(): void {

  }
  constructor(
    private auth: AuthService,
    private partService: ParticipantService,
    public eventService: VolunteereventService,
    private volCom: VolunteereventComponent,
  ) {}

  addParticipant(id: number){
    this.partService.addParticipant(id).subscribe({
      next: (addedParticipant: Participant) => {
         this.volCom.show(id);
      },
      error: (err) => {
        console.error(err);
      }
    })
  }



}
