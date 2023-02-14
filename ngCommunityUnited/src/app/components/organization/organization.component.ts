import { VolunteereventComponent } from './../volunteerevent/volunteerevent.component';
import { OrganizationService } from './../../services/organization.service';
import { Component, OnInit } from '@angular/core';
import { Organization } from 'src/app/models/organization';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { VolunteereventService } from 'src/app/services/volunteerevent.service';
import { Volunteerevent } from 'src/app/models/volunteerevent';
import { EventImg } from 'src/app/models/event-img';

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

  newEventImage: EventImg | null = null;

  user: User | null = null;

  showId: Organization | null = null;

  ngOnInit() {
    this.index();

  }

  index() {
    this.orgService.index().subscribe({
      next: (orgs: Organization[]) => {
        this.allOrgs = orgs;

        this.getUser();

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
    if (this.checkLogIn()) {
      this.auth.getLoggedInUser().subscribe({
        next: (loggedInUser) => {

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

        // console.log(this.user);

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

  addedMemberToOrg(id: number) {
    this.orgService.addedMemberToOrg(id).subscribe({
      next: (addedMember) => {
        this.showById(id);
        this.index();

      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  checkMember() {
    let found = false;
    if (this.user && this.selectedOrganization) {
      for (let member of this.selectedOrganization.members) {
        if (member.user.id === this.user.id) {
          found = true;
          break;
        }
      }
    }
    return found;
  }

  showById(id: number) {
    this.orgService.showById(id).subscribe({
      next: (org: Organization) => {
        this.showId = org;
        this.selectedOrganization = this.showId;
      },
      error: (err) => {
        console.error(err);
        return null;
      },
    });
  }

  createVolunteerevent(volunteerevent: Volunteerevent) {
    if (this.newEventImage && this.newVolunteerevent) {
      this.newVolunteerevent.eventImages.push(this.newEventImage);
      // this.newEventImage.volunteerEvent = this.newVolunteerevent;
    }
    if (this.newVolunteerevent) {
      this.orgService
        .createVolunteerevent(
          this.newVolunteerevent,
          this.selectedOrganization!.id
        )
        .subscribe({
          next: (volunteerevent) => {
            this.newVolunteerevent = new Volunteerevent();
          },
          error: (err) => {
            console.log(
              'VolunteereventComponent.createVolunteerevent(): error creating event'
            );
            console.log(err);
          },
        });
    }
    this.index();
  }

  newEvent() {
    this.newVolunteerevent = new Volunteerevent();
    this.newEventImage = new EventImg();
  }

  removedFromOrg(id: number) {
    this.orgService.removeUserFromOrg(id).subscribe({
      next: () => {
        this.showById(id);
        this.index();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
