import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AccountComponent } from './components/account/account.component';
import { OrganizationComponent } from './components/organization/organization.component';
import { VolunteereventComponent } from './components/volunteerevent/volunteerevent.component';
import { ParticipantComponent } from './components/participant/participant.component';
import { GroupMessageComponent } from './components/group-message/group-message.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    NavbarComponent,
    AccountComponent,
    OrganizationComponent,
    VolunteereventComponent,
    ParticipantComponent,
    GroupMessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
