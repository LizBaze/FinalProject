import { Volunteerevent } from 'src/app/models/volunteerevent';
export class EventImg {
  id: number;
  imgUrl: string;
  caption: string;

  constructor(
    id: number = 0,
    imgUrl: string = '',
    caption: string = '',
  ) {
    this.id = id;
    this.imgUrl = imgUrl;
    this.caption = caption;
  }
}
