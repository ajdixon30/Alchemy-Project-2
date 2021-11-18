import { Login } from '../DTOs/login';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class LoginService {

  baseUrl = 'http://localhost:8080/login';//not sure if this is the right thing to have here for the service

  constructor(private client: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  //get
  getUser(username: string, password: string): Observable<HttpResponse<Login[]>> {
    return this.client.get<Login[]>(this.baseUrl + "?username="+username + "&password="+password, {observe:'response'})
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
