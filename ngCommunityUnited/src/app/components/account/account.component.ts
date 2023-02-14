import { AuthService } from 'src/app/services/auth.service';
import { Component } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent {
  editUser: User | null = null;
  deleteUser: User | null = null;
  user: User | null = null;

  constructor(private auth: AuthService) {}

  ngOnInit() {
    this.getUser();
  }

  editAccount() {
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.editUser = user;
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  updateAccount() {
    if (this.editUser) {
      this.auth.updateUser(this.editUser).subscribe({
        next: (user: User) => {
          this.editUser = null;
          this.getUser();
        },
        error: () => {},
      });
    }
  }

  disableAccount() {
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {
        this.deleteUser = user;
        if (this.deleteUser) {
          this.auth.disableAccount(this.deleteUser).subscribe({
            next:()=>{
              this.deleteUser = null;

            },
            error:(e)=>{
              console.log('error' + e);
            }
          });
        }
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  getUser() {
    if (this.checkLogIn() ) {
      this.auth.getLoggedInUser().subscribe({
        next: (loggedInUser) => {
          console.log(loggedInUser);
          this.user = loggedInUser;
          console.log(this.user);
        },
        error: () => {

          console.error('not logged In');
        },
      });
    }
  }

  checkLogIn() {
    if (this.auth.checkLogin() === true) {
      return true;
    } else {
      return false;
    }
  }


}
