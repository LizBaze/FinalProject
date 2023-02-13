import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  newUser: User = new User();

 constructor(private auth: AuthService, private router: Router) {}

  register(user: User): void {
    console.log('register user:');
    console.log(user);
    user.imgUrl = 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/1200px-Default_pfp.svg.png';
    this.auth.register(user).subscribe({
      next: (registeredUser) => {
        this.auth.login(user.email, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/home');
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });
  }
}

