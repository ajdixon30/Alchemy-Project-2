import { Router } from '@angular/router';
import { LoginService } from './../Services/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../Services/login.service';
import { RegisterService } from '../Services/register.service';

@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.css']
})
export class CustomerSignupComponent implements OnInit {
  public registerService:RegisterService;
  public loginService:LoginService;

  private route:Router;


  firstName: string = "";
  lastName: string = "";
  username: string = "";
  password: string = "";
  admin: boolean = false;


  constructor(_registerService: RegisterService, loginService: LoginService, route:Router){
    this.registerService=_registerService;
    this.loginService=loginService;
    this.route=route;

  }


  onSubmit(): void {
    // //docs simply state to process data here
    let body = {firstName: this.firstName, lastName: this.lastName, username: this.username, password: this.password, admin: this.admin};
    this.part1(body);

    console.log(body);
  }

  part1(body:Object):void{
    this.registerService.saveNewUser(body).subscribe({
      next: data=> {
          this.part2(body);
      }
    });
  }

  part2(body:Object):void{
    this.loginService.getUser(this.username, this.password).subscribe(data=>{
      if (data.status == 200) {
        localStorage.setItem('user', this.username);
        this.route.navigate(['/user-home']);
      } else{
        this.username += " is taken";        
      }
    });
  }
  ngOnInit(): void {

  }
}
