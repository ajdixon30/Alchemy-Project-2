import { Component, OnInit } from '@angular/core';
import { Request } from '../../../DTOs/request';
import { RequestService } from '../request.service';

@Component({
  selector: 'app-mangage-requests',
  templateUrl: './mangage-requests.component.html',
  styleUrls: ['./mangage-requests.component.css']
})
export class MangageRequestsComponent implements OnInit {
  public request:Request[] = [];
  constructor() { }

  /* If there is time, create a function that gets the number of requests in the table
  and sets that value to "max".
*/

accept() {
  
    
}

reject(){
  
}

  ngOnInit(): void {
  }

}
