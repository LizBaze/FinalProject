import { Address } from "./address";

export class Volunteerevent {

  id: number;
  name: string;
  description: string;
  createdDate: string;
  startDate: string;
  endDate: string;
  organizationId: number;
  address: Address;

  constructor(id: number = 0, name: string = '', description: string = '', createdDate: string = '', startDate: string = '', endDate: string = '', organizationId: number = 0, address: Address = new Address()) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdDate = createdDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.organizationId = organizationId;
    this.address = address;
  };

}
