import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerHomePageComponent } from './User/customer-home-page/customer-home-page.component';
import { AdminHomePageComponent } from './Admin/admin-home-page/admin-home-page.component';

const routes: Routes = [
  { path: "admin", component: AdminHomePageComponent },
  { path: "customer", component: CustomerHomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
