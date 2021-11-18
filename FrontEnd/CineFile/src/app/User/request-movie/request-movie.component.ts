import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/Services/request.service';

@Component({
  selector: 'app-request-movie',
  templateUrl: './request-movie.component.html',
  styleUrls: ['./request-movie.component.css']
})
export class RequestMovieComponent implements OnInit {
  public requestService:RequestService;
  public title!:string;

  constructor(_requestService:RequestService) {
    this.requestService = _requestService;
   }

   //Adds a new add movie request
   addNewRequest(){
    this.title = (document.getElementById("title") as HTMLInputElement).value;
    let body = JSON.stringify({addRequest:this.title});
    this.requestService.newRequest(body).subscribe(data => {
      alert("Request for " + this.title +" has been received. Thank you!")
      console.log(data)
    })
  }

  ngOnInit(): void {
  }

}
