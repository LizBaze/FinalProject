import { Cause } from './cause';
import { Member } from './member';

export class Organization {
  id: number;
  name: string;
  logo: string;
  description: string;
  members: Member[];
  causes: Cause[];

  constructor(
    id: number = 0,
    name: string = '',
    logo: string = '',
    description: string = '',
    members: Member[] = [],
    causes: Cause[] = []
  ) {
    this.id = id;
    this.name = name;
    this.logo = logo;
    this.description = description;
    this.members = members;
    this.causes = causes;
  }
}
