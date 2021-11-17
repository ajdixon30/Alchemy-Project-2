import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { observeNotification } from 'rxjs/internal/Notification';
import { RegisterUser } from '../DTOs/register';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.css']
})
export class CustomerSignupComponent implements OnInit {
  public registerService:RegisterService;

  firstName: string = "";
  lastName: string = "";
  username: string = "";
  password: string = "";
  admin: boolean = false;


  constructor(_registerService: RegisterService){
    this.registerService = _registerService;
  }


  onSubmit(): void {
    // //docs simply state to process data here
    let body = {firstName: this.firstName, lastName: this.lastName, username: this.username, password: this.password, admin: this.admin};
    // this.id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;\
    this.registerService.saveNewUser(body);

    console.log(body);
    // this.firstName="";
    // this.lastName="";
    // this.username="";
    // this.password="";
  }

  ngOnInit(): void {

  }
}
