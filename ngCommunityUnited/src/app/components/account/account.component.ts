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

  constructor(private auth: AuthService) {}

  editAccount() {
    this.auth.getLoggedInUser().subscribe({
      next: (user: User) => {this.editUser = user},
      error: (error: any) => {
        console.log(error);
      },
    });
  }
}
