import { Component, OnInit } from '@angular/core';
import { Request } from '../../../DTOs/request';
import { RequestService } from '../request.service';

@Component({
  selector: 'app-view-requests',
  templateUrl: './view-requests.component.html',
  styleUrls: ['./view-requests.component.css']
})

export class ViewRequestsComponent implements OnInit {
  public requestService : RequestService;
  public requests: Request[] = [];

  constructor(_requestService : RequestService){
    this.requestService = _requestService;
  }

  ngOnInit(): void {
    this.requestService.getRequests().subscribe(data => {
      console.log(data);
      for(const item of data) {
        let {id, addRequest, requestStatus} = item;
        this.requests.push({id, addRequest, requestStatus})
        console.log(item);
      }
    })
  }


}
