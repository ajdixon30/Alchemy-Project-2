import { RegisterUser } from './../DTOs/register';

import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError, retry, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  baseUrl = 'http://localhost:8080/register';//not sure if this is the right thing to have here for the service

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;charset=utf-8'
    })
  }
  //post
  saveNewUser(body: Object): Observable<RegisterUser[]> {
    console.log(JSON.stringify(body));
    return this.http.post<RegisterUser[]>(this.baseUrl, JSON.stringify(body), this.httpOptions)
    .pipe(
      retry(0),
      catchError(this.errorHandler)
    );
   }//Trying to redirect user to customer home pahe after registration is success. Might just need to pull the plug on this idea


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

    // console.log(message);
    return throwError(() => new Error(message));
  }

}
