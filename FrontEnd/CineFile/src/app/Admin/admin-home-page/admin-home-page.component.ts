import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent implements OnInit {
  public request:boolean=true;
  public movie:boolean=false;

  constructor() { }

  manageRequests(){
    this.request = true;
    this.movie = false;
  }

  manageMovies(){
    this.request = false;
    this.movie = true;
  }

  ngOnInit(): void {
  }

}
