import { Organization } from './organization';
import { User } from './user';
export class Member {

  user: User;
  org: Organization;
  admin: boolean;
  dateJoined: string;

  constructor(

    user: User = new User(),
    org: Organization = new Organization(),
    admin: boolean = false,
    dateJoined: string = ""
  ) {

    this.user = user;
    this.org = org;
    this.admin = admin;
    this.dateJoined = dateJoined;
  }
}
