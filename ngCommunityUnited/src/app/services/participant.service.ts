import { OrganizationComponent } from './../components/organization/organization.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { Participant } from '../models/participant';
import { catchError, throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ParticipantService {
  private url = environment.baseUrl;

  constructor(
    private auth: AuthService,
    route: Router,
    private http: HttpClient
  ) {}

  getHttpOptions() {
    let httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${this.auth.getCredentials()}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return httpOptions;
  }

  addParticipant(id: number): Observable<Participant> {
    return this.http
      .post<Participant>(
        this.url + 'api/participants/' + id,
        {},
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'ParticipantService.addParticipant(): error adding participant.'
              )
          );
        })
      );
  }

  removeParticipant(id: number) {
    return this.http
      .delete<void>(
        this.url + 'api/volunteerevents/' + id + '/users',
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'ParticipantService.addParticipant(): error adding participant.'
              )
          );
        })
      );
  }
}
