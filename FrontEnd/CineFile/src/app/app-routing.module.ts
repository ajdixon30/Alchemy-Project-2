import { AdminLoginComponent } from './Admin/admin-login/admin-login.component';
import { CustomerSignupComponent } from './customer-signup/customer-signup.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewMoviesComponent } from './User/view-movies/view-movies.component';
import { MakeRequestComponent } from './User/make-request/make-request.component';
import { RateMovieComponent } from './User/rate-movie/rate-movie.component';

const routes: Routes = [
  { path: "admin-login", component: AdminLoginComponent },
  { path: "customer-signup", component: CustomerSignupComponent},
  { path: "customer-login", component: LoginPageComponent },
  { path: "view-movies", component: ViewMoviesComponent},
  { path: "make-request", component: MakeRequestComponent},
  { path: "rate-movie", component: RateMovieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
