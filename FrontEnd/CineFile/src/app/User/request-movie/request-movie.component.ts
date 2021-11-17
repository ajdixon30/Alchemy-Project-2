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

   addNewRequest(){
    this.title = (document.getElementById("title") as HTMLInputElement).value;
    let body = JSON.stringify({addRequest:this.title});
    this.requestService.newRequest(body).subscribe(data => {
      console.log(data)
    })
  }

  ngOnInit(): void {
  }

}
