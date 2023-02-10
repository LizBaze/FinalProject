export class Cause {
  id: number;
  name: string;
  description: string;
  icon_url: string;

  constructor(id: number = 0, name: string = '', description: string = '', icon_url: string = '') {
    this.id = id;
    this.name = name;
    this.description = description;
    this.icon_url = icon_url;
  }
}
