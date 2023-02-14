import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private auth: AuthService, private router: Router) {}

  loggedIn(): boolean {
    return this.auth.checkLogin();
  }

  logout(){
    this.auth.logout()
    this.router.navigateByUrl("home");
  }
}
