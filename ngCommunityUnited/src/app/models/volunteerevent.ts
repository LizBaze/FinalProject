import { Organization } from 'src/app/models/organization';
import { Address } from "./address";

export class Volunteerevent {

  id: number;
  name: string;
  description: string;
  createdDate: string;
  startDate: string;
  endDate: string;
  organization: Organization;
  address: Address;

  constructor(id: number = 0, name: string = '', description: string = '', createdDate: string = '', startDate: string = '', endDate: string = '', organization: Organization = new Organization(), address: Address = new Address()) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdDate = createdDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.organization = organization;
    this.address = address;
  };

}
