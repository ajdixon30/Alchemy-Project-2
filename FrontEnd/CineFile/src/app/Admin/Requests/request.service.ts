import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Request } from 'src/app/DTOs/request';
import { catchError, retry } from 'rxjs';
import { ErrorLoggerService } from 'src/app/error-logger.service';
import { ErrorLog } from 'src/app/error-log';


@Injectable({
  providedIn: 'root'
})
  
export class RequestService {
  public errorLogger : ErrorLoggerService;
  public errorLog : ErrorLog[] = [];

  baseUrl = 'http://localhost:8080/request';

  constructor(private client: HttpClient, private _errorLogger : ErrorLoggerService) {
    this.errorLogger = _errorLogger;
   }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  //Get all of the requests
  getRequests(): Observable<Request[]> {
    return this.client.get<Request[]>(this.baseUrl + "/list").pipe(
      retry(3),
      catchError(this.errorHandler)
    )
  }

  errorHandler(error: any) {
    let message = "";
    let warningLevel;
    if(error.error instanceof ErrorEvent) {
        //Get client-side error
        message = error.error.message;
        warningLevel = 3;
    } else {
        //Get server-side error
        message = `Error Code: ${error.status}\nMessage: ${error.message}`;
        warningLevel = 3;
    } 
    return throwError(() => new Error(message));
  }
}
