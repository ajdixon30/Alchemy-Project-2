import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError, catchError, retry } from 'rxjs';
import { ErrorLog } from './error-log';


//Currently does not work (not sure if the problem is in the service or somewhere else)
@Injectable({
  providedIn: 'root'
})
export class ErrorLoggerService {
  // public errorLog: ErrorLog[] = []

  baseUrl = 'http://localhost:8080/logging';

  constructor(private client: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;charset=utf-8'
    })
  }

  postErrorLog(errorLog:ErrorLog[]): Observable<ErrorLog[]> {
    const body = JSON.stringify(errorLog)
    console.log("this is the error log body: " + body);
    return this.client.post<ErrorLog[]>(this.baseUrl, body, this.httpOptions).pipe(
      retry(3),
      catchError(this.errorHandler)
    )
  }

  errorHandler(error: any) {
    let message = '';
    if(error.error instanceof ErrorEvent) {
        //Get client-side error
        message = error.error.message;
    } else {
        //Get server-side error
        message = `Error Code: ${error.status}\nMessage: ${error.message}`;
    } 

    console.log(message);
    return throwError(() => new Error(message));
  }

}
