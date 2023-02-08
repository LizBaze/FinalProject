import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginUser: User = new User();


  constructor(private authService: AuthService, private router: Router) {}


  login(loginUser: User) {
    this.authService.login(loginUser.email, loginUser.password).subscribe({
      next:(user: User) => {
        this.router.navigateByUrl("home");
        loginUser = new User();
      },
      error: (problem: any) => {
        console.log(problem);
      }
    });
  }

}
