import { Organization } from './organization';
import { User } from './user';
export class Member {

  user: User;
  organization: Organization;
  admin: boolean;
  dateJoined: string;

  constructor(

    user: User = new User(),
    organization: Organization = new Organization(),
    admin: boolean = false,
    dateJoined: string = ""
  ) {

    this.user = user;
    this.organization = organization;;
    this.admin = admin;
    this.dateJoined = dateJoined;
  }
}
