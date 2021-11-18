import { Component, OnInit } from '@angular/core';
import { RequestService } from '../../../Services/request.service';

@Component({
  selector: 'app-mangage-requests',
  templateUrl: './mangage-requests.component.html',
  styleUrls: ['./mangage-requests.component.css']
})
export class MangageRequestsComponent implements OnInit {
  public requestService : RequestService;
  public _id!:number;
  public max:number = 0;

  constructor(_requestService : RequestService){
    this.requestService = _requestService;
  }

  checkMax(){
    this.requestService.getMax().subscribe(data =>{
      this.max = data;
      console.log("count: " + data);
      console.log("max: " + this.max);
    })
  }

  accept() {
    this._id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
    let body = JSON.stringify({id:this._id,requestStatus:"Accepted"});
    this.requestService.updateRequest(body).subscribe(data => {
      console.log(data);
    })
  }

  reject(){
    this._id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
    let body = JSON.stringify({id:this._id,requestStatus:"Rejected"});
    this.requestService.updateRequest(body).subscribe(data => {
      console.log(data)
    })
  }

  ngOnInit(): void {
    this.checkMax();
  }
}
