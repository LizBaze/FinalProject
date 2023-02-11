import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GroupMessage } from '../models/group-message';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class GroupMessageService {

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

  reload(id: number) {
    return this.http
      .get<GroupMessage[]>(
        this.url + 'api/volunteerevents/' + id + '/groupmessages',
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error('GroupMessageService.reload() error retrieving Org List ' + err)
          );
        })
      );
  }

  createMessage(message: GroupMessage, id: number){
    return this.http.post<GroupMessage>(this.url + 'api/volunteerevents/' + id + '/groupmessages', message, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GroupMessageService.create() error retrieving creating messages ' + err)
        );
      })
    )
  }

  deleteMessage(id: number){
   return this.http.delete<void>(this.url + 'api/groupmessages/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error('GroupMessageService.deleteMessage() error deleteing messages ' + err)
      );
    })
   )
  }
  
}
