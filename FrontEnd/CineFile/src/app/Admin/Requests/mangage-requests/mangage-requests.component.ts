import { Component, OnInit } from '@angular/core';
import { Request } from '../../../DTOs/request';
import { RequestService } from '../request.service';
import { ViewRequestsComponent } from '../view-requests/view-requests.component';

@Component({
  selector: 'app-mangage-requests',
  templateUrl: './mangage-requests.component.html',
  styleUrls: ['./mangage-requests.component.css']
})
export class MangageRequestsComponent implements OnInit {
  public requestService : RequestService;
  public request: Request[] = [];
  public id!:number;
  

  constructor(_requestService : RequestService){
    this.requestService = _requestService;
  }

  /* If there is time, create a function that gets the number of requests in the table
  and sets that value to "max".
*/

accept() {
  this.id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
  let body = JSON.stringify({id:this.id,requestStatus:"Accepted"});
  this.requestService.updateRequest(body).subscribe(data => {
    console.log(data)
  })
  
}

reject(){
  
}

  ngOnInit(): void {
  }

}
