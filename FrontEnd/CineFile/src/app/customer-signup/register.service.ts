import { Register } from './../DTOs/register';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  baseUrl = 'http://localhost:8080/register';//not sure if this is the right thing to have here for the service

  constructor(private client: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  //get
  post(username: string, password: string, firstName: string, lastName: string): Observable<Register> {
    return this.client.get<Register>(this.baseUrl + username + password + firstName + lastName)
    .pipe(
      retry(0),
      catchError(this.errorHandler)
    )
  }

  errorHandler(error: any) {
    let message = '';
    // let warningLevel = 3;
    if(error.error instanceof ErrorEvent) {
        //Get client-side error
        message = error.error.message;
    } else {
        //Get server-side error
        message = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    //Save the error message here//

    console.log(message);
    return throwError(() => new Error(message));
  }

}
