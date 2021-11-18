import { RegisterUser } from './../DTOs/register';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
      'Content-Type': 'application/json'
    })
  }
  //post
  saveNewUser(body: Object): Observable<RegisterUser[]> {
    console.log(JSON.stringify(body));
    body = JSON.stringify(body);
    return this.http.post<RegisterUser[]>(this.baseUrl, body, this.httpOptions)
    .pipe(
      catchError(this.errorHandler)
    );
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
