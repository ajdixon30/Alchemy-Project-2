import { Component, OnInit } from '@angular/core';
import { rating } from 'src/app/DTOs/rating';
import { RatingService } from 'src/app/Services/rating.service';

@Component({
  selector: 'app-customer-home-page',
  templateUrl: './customer-home-page.component.html',
  styleUrls: ['./customer-home-page.component.css']
})
export class CustomerHomePageComponent implements OnInit {
  public ratingService:RatingService;
  public rating:rating[] = [];
  public _id!:number;
  public _rating!:number;

  _username : String | null = localStorage.getItem('user');

  constructor(_ratingService:RatingService) {
    this.ratingService = _ratingService;
   }

  submitRating(){
    this._id = (document.getElementById("id") as HTMLInputElement).valueAsNumber;
    this._rating = (document.getElementById("rating") as HTMLInputElement).valueAsNumber;
    let body = JSON.stringify({rating:this._rating,movie:{id:this._id},user:{username:this._username}});
    console.log(body);
    this.ratingService.rateMovie(body).subscribe(data => {
      alert("Thank you for your rating!");
      console.log(data);
    })
  }

  ngOnInit(): void {
  }

}
