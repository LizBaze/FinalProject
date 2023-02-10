import { VolunteereventComponent } from './../volunteerevent/volunteerevent.component';
import { OrganizationService } from './../../services/organization.service';
import { Component, OnInit } from '@angular/core';
import { Organization } from 'src/app/models/organization';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { VolunteereventService } from 'src/app/services/volunteerevent.service';
import { Volunteerevent } from 'src/app/models/volunteerevent';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css'],
})
export class OrganizationComponent implements OnInit {
  allOrgs: Organization[] | null = null;

  selectedOrganization: Organization | null = null;

  editOrganization: Organization | null = null;

  newOrganization: Organization | null = null;

  newVolunteerevent: Volunteerevent | null = null;

  user: User | null = null;



  ngOnInit() {
    this.index();
    console.log(this.user);

  }

  index() {
    this.orgService.index().subscribe({
      next: (orgs: Organization[]) => {
        this.allOrgs = orgs;
        console.log(orgs);
        this.getUser();
        console.log(this.user);
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  constructor(
    private auth: AuthService,
    private orgService: OrganizationService,
    public eventService: VolunteereventService
  ) {}

  newOrg() {
    this.newOrganization = new Organization();
  }

  editOrg(org: Organization) {
    this.editOrganization = org;
    this.selectedOrganization = null;
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

  checkAdmin() {
    let found = undefined;
    console.log(this.user);
    if (this.user && this.selectedOrganization) {
      for (let member of this.selectedOrganization.members) {
        console.log(this.user + ' - ' + member);
        if (member.user.id === this.user.id && member.admin === true) {
          found = true;
          break;
        }
      }
    }

    return found !== undefined;
  }

  checkLogIn() {
    if (this.auth.checkLogin() === true) {
      return true;
    } else {
      return false;
    }
  }

  createOrg(org: Organization) {
    this.orgService.createOrg(org).subscribe({
      next: (createdOrg) => {
        console.log(createdOrg);
        this.newOrganization = null;
        this.index();
      },
      error: (fail) => {
        console.error(
          'OrganizationComponent.createOrg(): Error creating organization'
        );
        console.error(fail);
      },
    });
  }

  updateOrg(org: Organization, id: number) {
    this.orgService.updateOrg(org, id).subscribe({
      next: (updatedOrg: Organization) => {
        this.editOrganization = null;
        this.selectedOrganization = updatedOrg;
      },
      error: (fail) => {
        console.error(
          'OrganizationComponent.updateOrg(): error updating org' + fail
        );
      },
    });
  }
  createVolunteerevent(volunteerevent : Volunteerevent) {
    if (this.newVolunteerevent) {
    this.eventService.createVolunteerevent(this.newVolunteerevent, this.selectedOrganization!.id).subscribe({
      next: (volunteerevent) => {
        this.newVolunteerevent = new Volunteerevent();
      },
      error: (err) => {
        console.log('VolunteereventComponent.createVolunteerevent(): error creating event');
        console.log(err);
      }
    });
  }
    this.index();
    }

    newEvent(){
      this.newVolunteerevent = new Volunteerevent();
    }


}
