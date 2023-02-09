import { VolunteereventService } from './../../services/volunteerevent.service';
import { Volunteerevent } from './../../models/volunteerevent';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-volunteerevent',
  templateUrl: './volunteerevent.component.html',
  styleUrls: ['./volunteerevent.component.css']
})
export class VolunteereventComponent implements OnInit {

  events: Volunteerevent[] = [];

  constructor(private volunteerEventService:VolunteereventService) {};

  ngOnInit() {
    this.reload();
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
}
