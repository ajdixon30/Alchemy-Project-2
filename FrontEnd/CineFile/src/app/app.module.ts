import { HomePageComponent } from './home-page/home-page.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerHomePageComponent } from './User/customer-home-page/customer-home-page.component';
import { AdminHomePageComponent } from './Admin/admin-home-page/admin-home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { MakeRequestComponent } from './User/make-request/make-request.component';
import { ManageRequestComponent } from './Admin/view-requests/manage-request.component';
import { ManageRequestButtonsComponent } from './Admin/manage-request-buttons/manage-request-buttons.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CustomerSignupComponent } from './customer-signup/customer-signup.component';
import { AdminLoginComponent } from './Admin/admin-login/admin-login.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerHomePageComponent,
    AdminHomePageComponent,
    LoginPageComponent,
    MakeRequestComponent,
    ManageRequestComponent,
    ManageRequestButtonsComponent,
    NavbarComponent,
    HomePageComponent,
    CustomerSignupComponent,
    AdminLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
