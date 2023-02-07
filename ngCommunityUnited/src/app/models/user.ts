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

  constructor(id: number = 0, firstName: string = '', lastName: string ='', email: string = '', password: string = '', role: string = '', imgUrl: string = '', enabled: boolean = false, bio: string = '') {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
    this.imgUrl = imgUrl;
    this.enabled = enabled;
    this.bio = bio;
  };


}
