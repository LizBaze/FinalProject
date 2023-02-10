import { Volunteerevent } from './../models/volunteerevent';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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


  constructor(private authService: AuthService, route: Router, private http: HttpClient) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

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


  createVolunteerevent(volunteerevent: Volunteerevent, oid: number): Observable<Volunteerevent> {
    return this.http.post<Volunteerevent>(this.url + 'api/organizations/' + oid + 'volunteerevents/', volunteerevent, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VolunteereventService.create(): error creating volunteerevent: ' + err)
        );
      })
    );
  }

  update(volunteerevent: Volunteerevent):Observable<Volunteerevent> {
    const credentials = this.authService.getCredentials();
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http.put<Volunteerevent>(this.url + 'api/volunteerevents/' + volunteerevent.id, volunteerevent, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('Volunteerevent.update(): error updating volunteerevent: ' + err)
        );
      })
    );
  }





  // updateVolunteerevent(id, volunteerevent) {
  //   return this.http.put(this.url + '/volunteerevents/' + id, volunteerevent);
  // }

  // deleteVolunteerevent(id) {
  //   return this.http.delete(this.url + '/volunteerevents/' + id);
  // }



}
