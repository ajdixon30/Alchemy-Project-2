import { AdminLoginComponent } from './Admin/admin-login/admin-login.component';
import { CustomerSignupComponent } from './customer-signup/customer-signup.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MangageRequestsComponent } from './Admin/Requests/mangage-requests/mangage-requests.component';
import { MangageMoviesComponent } from './Admin/Movies/mangage-movies/mangage-movies.component';

const routes: Routes = [
  { path: "admin-login", component: AdminLoginComponent },
  { path: "customer-signup", component: CustomerSignupComponent},
  { path: "customer-login", component: LoginPageComponent },
  { path: "manage-requests", component: MangageRequestsComponent},
  { path: "manage-movies", component: MangageMoviesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
