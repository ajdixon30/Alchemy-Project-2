import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ErrorLog } from './error-log';

@Injectable({
  providedIn: 'root'
})
export class ErrorLoggerService {

  baseUrl = 'http://localhost:8080/logging';

  constructor(private client: HttpClient, private errorLog: ErrorLog) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  //Still working on this!!!!
  //Save error messages to the database
  // postErrorLog(): Observable<ErrorLog[]> {
  //   //This is not working. watch how Kyle sets up the post request before continuing this part 
  //   return this.client.post(this.baseUrl,this.errorLog: any,this.httpOptions);
  // }
}
