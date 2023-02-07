import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private auth: AuthService){};

  ngOnInit(){
    this.tempTestDeleteLater();  // FIXME: DELETE

  };

  //temporary function
  tempTestDeleteLater(){
   this.auth.login('john@email.com', 'wombat1').subscribe({
    next: (data) => {
      console.log('Logged In Success');
      console.log(data);
    },
    error: (fail) => {
      console.error('Error Authenticating login');
      console.error(fail);
    }
   });
  }

}
