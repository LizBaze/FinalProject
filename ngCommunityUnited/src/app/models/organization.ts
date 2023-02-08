export class Organization {

  id: number;
  name: string;
  logo: string;
  description: string;

  constructor(id: number = 0, name: string = '', logo: string = '', description: string = '') {
    this.id = id;
    this.name = name;
    this.logo = logo;
    this.description = description;
  }


}
