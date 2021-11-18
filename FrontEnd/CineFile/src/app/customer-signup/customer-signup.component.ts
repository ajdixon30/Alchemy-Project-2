import { Router } from '@angular/router';
import { LoginService } from './../Services/login.service';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../Services/register.service';

@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.css']
})
export class CustomerSignupComponent implements OnInit {
  public registerService:RegisterService;
  public loginService:LoginService;
  firstName: string = "";
  lastName: string = "";
  username: string = "";
  password: string = "";
  admin: boolean = false;


  constructor(_registerService: RegisterService, _loginService : LoginService, router : Router){
    this.registerService = _registerService;
    this.loginService = _loginService;
  }


  onSubmit(): void {
    // //docs simply state to process data here
    let body = {firstName: this.firstName, lastName: this.lastName, username: this.username, password: this.password, admin: this.admin};
    this.registerService.saveNewUser(body).subscribe(Response);
    this.loginService.getUser(body.username, body.password);


    console.log(body);
  }

  ngOnInit(): void {

  }
}
