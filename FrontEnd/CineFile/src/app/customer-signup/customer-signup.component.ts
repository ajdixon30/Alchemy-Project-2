import { Register } from './../DTOs/register';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { RegisterService } from './register.service';
import { firstValueFrom } from 'rxjs';
@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.css']
})
export class CustomerSignupComponent implements OnInit {

  registerForm = this.formBuilder.group({
    firstName: '',
    lastName: '',
    username: '',
    password: ''
  });

  constructor(
    private registerService: RegisterService,
    private formBuilder: FormBuilder
  ) { }
  onSubmit(): void {
    //docs simply state to process data here
    this.registerForm.get()
  }

  ngOnInit(): void {
  }

}
