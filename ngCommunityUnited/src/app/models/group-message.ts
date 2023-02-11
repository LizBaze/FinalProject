import { Volunteerevent } from 'src/app/models/volunteerevent';
import { User } from './user';
export class GroupMessage {
  id: number;
  description: string;
  user: User;
  volunteerEvent: Volunteerevent;
  datePosted: string;
  inReplyTo: GroupMessage;

  constructor(
    id: number = 0,
    description: string = '',
    user: User = new User(),
    volunteerEvent: Volunteerevent = new Volunteerevent(),
    datePosted: string = '',
    inReplyTo: GroupMessage = new GroupMessage()
  ) {
    this.id = id;
    this.description = description;
    this.user = user;
    this.volunteerEvent = volunteerEvent;
    this.datePosted = datePosted;
    this.inReplyTo = inReplyTo;
  }
}
