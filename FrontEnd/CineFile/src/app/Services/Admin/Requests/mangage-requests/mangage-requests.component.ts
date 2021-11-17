import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../Services/request.service';

@Component({
  selector: 'app-mangage-requests',
  templateUrl: './mangage-requests.component.html',
  styleUrls: ['./mangage-requests.component.css']
})
export class MangageRequestsComponent implements OnInit {
  public requestService : RequestService;
  public id!:number;

  constructor(_requestService : RequestService){
    this.requestService = _requestService;
  }

accept() {
  this.id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
  let body = JSON.stringify({id:this.id,requestStatus:"Accepted"});
  this.requestService.updateRequest(body).subscribe(data => {
    console.log(data)
  })
  
}

reject(){
  this.id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
  let body = JSON.stringify({id:this.id,requestStatus:"Rejected"});
  this.requestService.updateRequest(body).subscribe(data => {
    console.log(data)
  })
}

  ngOnInit(): void {
  }

}
