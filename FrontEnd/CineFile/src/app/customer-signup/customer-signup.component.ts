import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../Services/register.service';

@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.scss']
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
    this.registerService.saveNewUser(body).subscribe();

    console.log(body);
  }

  ngOnInit(): void {

  }
}
