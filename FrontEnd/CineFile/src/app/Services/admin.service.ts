import { Observable, catchError, retry, throwError } from 'rxjs';
import { HttpResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import { Login } from '../DTOs/login';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  baseUrl = "http://localhost:8080/admin";

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
      retry(1),
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
