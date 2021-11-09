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

@NgModule({
  declarations: [
    AppComponent,
    CustomerHomePageComponent,
    AdminHomePageComponent,
    LoginPageComponent,
    MakeRequestComponent,
    ManageRequestComponent,
    ManageRequestButtonsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
