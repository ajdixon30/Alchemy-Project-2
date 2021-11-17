import { AdminHomePageComponent } from './Admin/admin-home-page/admin-home-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AdminLoginComponent } from './Admin/admin-login/admin-login.component';
import { CustomerSignupComponent } from './customer-signup/customer-signup.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewRequestsComponent } from './Admin/Requests/view-requests/view-requests.component';
import { AdminMovieTableComponent } from './Admin/Movies/admin-movie-table/admin-movie-table.component';

const routes: Routes = [
  { path: "admin-login", component: AdminLoginComponent },
  { path: "customer-signup", component: CustomerSignupComponent},
  { path: "customer-login", component: LoginPageComponent },
  { path: "view-request", component: ViewRequestsComponent},
  { path: "user-home", component: HomePageComponent },
  { path: "admin-home", component: AdminHomePageComponent}
  { path: "admin-movie-table", component: AdminMovieTableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
