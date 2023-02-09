export class Volunteerevent {

  id: number;
  name: string;
  description: string;
  createdDate: string;
  startDate: string;
  endDate: string;
  organizationId: number;

  constructor(id: number = 0, name: string = '', description: string = '', createdDate: string = '', startDate: string = '', endDate: string = '', organizationId: number = 0) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.createdDate = createdDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.organizationId = organizationId;
  };

}
