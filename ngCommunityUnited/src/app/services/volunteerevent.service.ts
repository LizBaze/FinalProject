import { Volunteerevent } from './../models/volunteerevent';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class VolunteereventService {
  private url = environment.baseUrl;

  constructor(private auth: AuthService, route: Router, private http: HttpClient) { }

  index(): Observable<Volunteerevent[]>{
    return this.http.get<Volunteerevent[]>(this.url + 'api/volunteerevents').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VolunteereventService.index(): error retrieving event list: ' + err)
        );
      })
    );
  }


  show(id: number): Observable<Volunteerevent> {
    return this.http.get<Volunteerevent>(this.url+ 'api/volunteerevents/'  +id,).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VolunteereventService.index(): error retrieving event: ' + err)
        );
      })
    );
  }

  // createVolunteerevent(volunteerevent) {
  //   return this.http.post(this.url + '/volunteerevents', volunteerevent);
  // }

  // updateVolunteerevent(id, volunteerevent) {
  //   return this.http.put(this.url + '/volunteerevents/' + id, volunteerevent);
  // }

  // deleteVolunteerevent(id) {
  //   return this.http.delete(this.url + '/volunteerevents/' + id);
  // }



}
