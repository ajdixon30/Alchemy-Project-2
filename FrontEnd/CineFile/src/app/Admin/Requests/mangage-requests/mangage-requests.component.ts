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
    this.requestService.getCount().subscribe(data =>{
      this.max = data;
      console.log("count: " + data);
      console.log("max: " + this.max);
    })
  }

  getMax(){
      console.log("In getMax function");
      if(this.max===0){
        console.log("In getMax if statement");
        this.checkMax();
      }
  }

  accept() {
    // this.checkMax();
    this._id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
    let body = JSON.stringify({id:this._id,requestStatus:"Accepted"});
    this.requestService.updateRequest(body).subscribe(data => {
      console.log(data);
    })
  }

  reject(){
    this.checkMax();
    this._id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
    let body = JSON.stringify({id:this._id,requestStatus:"Rejected"});
    this.requestService.updateRequest(body).subscribe(data => {
      console.log(data)
    })
  }

  ngOnInit(): void {
  }

}
