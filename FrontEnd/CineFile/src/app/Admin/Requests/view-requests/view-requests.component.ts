import { Component, OnInit } from '@angular/core';
import { Request } from '../../../DTOs/request';
 
@Component({
  selector: 'app-view-requests',
  templateUrl: './view-requests.component.html',
  styleUrls: ['./view-requests.component.css']
})

export class ViewRequestsComponent {

  requests : Request[] = [
    {id : 1, addRequest : "Dragon Heart", requestStatus : "Pending"},
    {id : 2, addRequest : "Raya and the Last Dragon", requestStatus : "Pending"},
    {id : 3, addRequest : "Eragon", requestStatus : "Pending"}
  ];
}