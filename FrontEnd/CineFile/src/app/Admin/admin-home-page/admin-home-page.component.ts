import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent implements OnInit {
  public manage:boolean = true;

  constructor(private router:Router) {   }

  manageTrue(){
    this.manage = true;
  }

  manageFalse(){
    this.manage = false;
    this.router.navigate(['/manage-movies']);
  }

  ngOnInit(): void {
  }

}
