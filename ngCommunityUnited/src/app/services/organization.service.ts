import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Organization } from '../models/organization';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {
  private url = environment.baseUrl;

  constructor(private auth: AuthService, route: Router, private http: HttpClient) { }


  createOrg(org: Organization){
    const credentials = this.auth.getCredentials();
   // Send credentials as Authorization header specifying Basic HTTP authentication
   const httpOptions = {
     headers: new HttpHeaders({
       Authorization: `Basic ${credentials}`,
       'X-Requested-With': 'XMLHttpRequest',
     }),
   };
    return this.http.post<Organization>(this.url + 'api/organizations', org, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('OrganizationService.createOrg(): error creating organization user.')
        );
      })
    );
  }


}
