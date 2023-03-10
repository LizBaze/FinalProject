import { Address } from './address';
import { Member } from './member';

export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  role: string;
  imgUrl: string;
  enabled: boolean;
  bio: string;
  address: Address | null;

  members: Member[];

  constructor(
    id: number = 0,
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    password: string = '',
    role: string = '',
    imgUrl: string = '',
    enabled: boolean = false,
    bio: string = '',
    members: Member[] = [],
    address: Address | null = null
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.imgUrl = imgUrl;
    this.enabled = enabled;
    this.bio = bio;
    this.members = members;
    this.address = address;
  }
}
