import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-home-page',
  templateUrl: './customer-home-page.component.html',
  styleUrls: ['./customer-home-page.component.scss']
})
export class CustomerHomePageComponent implements OnInit {

  username : String | null = localStorage.getItem('user');

  constructor() { }

  ngOnInit(): void {
  }

}
