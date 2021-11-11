import { HomePageComponent } from './home-page/home-page.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerHomePageComponent } from './User/customer-home-page/customer-home-page.component';
import { AdminHomePageComponent } from './Admin/admin-home-page/admin-home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { MakeRequestComponent } from './User/make-request/make-request.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ManageRequestsComponent } from './Admin/manage-requests/manage-requests.component';
import { ManageMoviesComponent } from './Admin/manage-movies/manage-movies.component';
import { ViewMoviesComponent } from './User/view-movies/view-movies.component';
import { ViewRequestsComponent } from './Admin/view-requests/view-requests.component';
import { CustomerSignupComponent } from './customer-signup/customer-signup.component';
import { AdminLoginComponent } from './Admin/admin-login/admin-login.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerHomePageComponent,
    AdminHomePageComponent,
    LoginPageComponent,
    MakeRequestComponent,
    NavbarComponent,
    ManageRequestsComponent,
    ManageMoviesComponent,
    ViewMoviesComponent,
    AdminHomePageComponent,
    AdminLoginComponent,
    ViewRequestsComponent,
    CustomerSignupComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
