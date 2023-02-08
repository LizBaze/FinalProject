import { OrganizationService } from './../../services/organization.service';
import { Component } from '@angular/core';
import { Organization } from 'src/app/models/organization';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css']
})
export class OrganizationComponent {

  newOrganization: Organization | null = new Organization();


  constructor(private auth: AuthService, private orgService: OrganizationService ) {}

  createOrg(org: Organization){
    this.orgService.createOrg(org).subscribe({
      next: (createdOrg) => {
         console.log(createdOrg);
         this.newOrganization = createdOrg;

      },
      error: (fail) => {
        console.error('OrganizationComponent.createOrg(): Error creating organization');
        console.error(fail);
      }
    });
  }
  }




