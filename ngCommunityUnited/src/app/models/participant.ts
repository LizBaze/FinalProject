import { Volunteerevent } from './volunteerevent';
import { User } from './user';

export class Participant {
  user: User;
  volunteerEvent: Volunteerevent;
  description: string;
  imgUrl: string;
  rating: string;
  dateJoined: string;

  constructor(
    description: string = '',
    imgUrl: string = '',
    rating: string = ' ',
    dateJoined: string = '',
    user: User = new User(),
    volunteerEvent: Volunteerevent = new Volunteerevent()
  ) {
    this.description = description;
    this.imgUrl = imgUrl;
    this.rating = rating;
    this.dateJoined = dateJoined;
    this.user = user;
    this.volunteerEvent = volunteerEvent;
  }
}
