import { OrganizationService } from './../../services/organization.service';
import { Component, OnInit } from '@angular/core';
import { Organization } from 'src/app/models/organization';
import { AuthService } from 'src/app/services/auth.service';

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

  ngOnInit() {
    this.orgService.index().subscribe({
      next: (orgs: Organization[]) => {
        this.allOrgs = orgs;
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
        this.newOrganization = createdOrg;
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
