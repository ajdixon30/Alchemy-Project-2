import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.css']
})
export class CustomerSignupComponent implements OnInit {



  constructor(
    private registerService: RegisterService,
    private formBuilder: FormBuilder
  ) { }
   registerService = this.formBuilder.group({
    firstName: '',
    lastName: '',
    username: '',
    password: ''
  });
  onSubmit(): void {
    //docs simply state to process data here
    console.log("made it to component.ts");
  }

  ngOnInit(): void {
  }

}
