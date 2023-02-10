import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable, OnInit } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Organization } from '../models/organization';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Member } from '../models/member';
import { Volunteerevent } from '../models/volunteerevent';

@Injectable({
  providedIn: 'root',
})
export class OrganizationService {
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

  index(): Observable<Organization[]> {
    return this.http.get<Organization[]>(this.url + 'api/organizations').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('OrgService.index() error retrieving Org List ' + err)
        );
      })
    );
  }

  createOrg(org: Organization) {
    const credentials = this.auth.getCredentials();
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http
      .post<Organization>(this.url + 'api/organizations', org, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'OrganizationService.createOrg(): error creating organization user.'
              )
          );
        })
      );
  }

  updateOrg(org: Organization, id: number) {
    const credentials = this.auth.getCredentials();
    // Send credentials as Authorization header specifying Basic HTTP authentication
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http
      .put<Organization>(this.url + 'api/organizations/' + id, org, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'OrganizationService.createOrg(): error creating organization user.'
              )
          );
        })
      );
  }

  checkAdmin(orgId: number, userId: number) {
    return this.http
      .get<boolean>(
        this.url + 'api/organizations/' + orgId + '/users/' + userId,
        this.getHttpOptions()
      )
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'OrganizationService.createOrg(): error creating organization user.'
              )
          );
        })
      );
  }

  addedMemberToOrg(orgId: number){
    return this.http.post<Member>(this.url + 'api/organizations/' + orgId + '/users', null, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'OrganizationService.addedMemberToOrg(): error adding member.'
            )
        );
      })
    )

  }


  showById(id: number){
   return this.http.get<Organization>(this.url + 'api/organizations/' + id, this.getHttpOptions()).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error(
            'OrganizationService.showById(): error getting Id.'
          )
      );
    })
   )
  }

<<<<<<< HEAD
  removeUserFromOrg(id: number){
    return this.http.delete<void>(this.url + 'api/organizations/' + id + '/users',  this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'OrganizationService.removedUserFromOrg(): error removing.'
            )
        );
      })
    )
=======
  createVolunteerevent(volunteerevent: Volunteerevent, oid: number): Observable<Volunteerevent> {
    return this.http.post<Volunteerevent>(this.url + 'api/organizations/' + oid + '/volunteerevents/', volunteerevent, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('VolunteereventService.create(): error creating volunteerevent: ' + err)
        );
      })
    );
>>>>>>> b3d37d4e2d44fdb4851b47df49a57beb50ceee91
  }
}
