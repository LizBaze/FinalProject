import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  private url = environment.baseUrl;

  constructor(private auth: AuthService,
    route: Router,
    private http: HttpClient) { }
}
