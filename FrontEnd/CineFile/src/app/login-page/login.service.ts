import { Login } from './../DTOs/login';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class LoginService {

  baseUrl = 'http://localhost:8080/login';

  constructor(private client: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
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
