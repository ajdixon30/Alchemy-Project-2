import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomePageComponent } from './home-page/home-page.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerHomePageComponent } from './User/customer-home-page/customer-home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ViewRequestsComponent } from './Admin/Requests/view-requests/view-requests.component';
import { CustomerSignupComponent } from './customer-signup/customer-signup.component';
import { AdminLoginComponent } from './Admin/admin-login/admin-login.component';
import { HttpClientModule } from '@angular/common/http';

import { AdminHomePageComponent } from './Admin/admin-home-page/admin-home-page.component';
import { MangageRequestsComponent } from './Admin/Requests/mangage-requests/mangage-requests.component';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [
    AppComponent,
    CustomerHomePageComponent,
    LoginPageComponent,
    NavbarComponent,
    ViewRequestsComponent,
    HomePageComponent,
    CustomerSignupComponent,
    AdminLoginComponent,
    AdminHomePageComponent,
    MangageRequestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
