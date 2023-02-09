import { OrganizationService } from './../../services/organization.service';
import { Component, OnInit } from '@angular/core';
import { Organization } from 'src/app/models/organization';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

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

  user: User | null = null;



  ngOnInit() {
    this.index();
    this.getUser();

  }

  index() {
    this.orgService.index().subscribe({
      next: (orgs: Organization[]) => {
        this.allOrgs = orgs;
        console.log(orgs);
      },
      error: (err: any) => {
        console.error(err);
      },
    });
  }

  constructor(
    private auth: AuthService,
    private orgService: OrganizationService
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
        },
        error: () => {

          console.error('not logged In');
        },
      });
    }
  }

  checkAdmin() {
    let found = undefined;
    if (this.user && this.selectedOrganization) {
      for (let member of this.selectedOrganization.members) {
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
}
