import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Request } from 'src/app/DTOs/request';
import { catchError, retry } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
  
export class RequestService {

  baseUrl = 'http://localhost:8080/request';

  constructor(private client: HttpClient){}

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

    //Get all of the requests
    getOneRequest(id:number): Observable<Request[]> {
      return this.client.get<Request[]>(this.baseUrl + "?id=" + id).pipe(
        retry(3),
        catchError(this.errorHandler)
      )
    }

  //Update request status
  updateRequest(body:string):Observable<Request[]> {
    return this.client.put<Request[]>(this.baseUrl, body, this.httpOptions).pipe(
      retry(3), 
      catchError(this.errorHandler)
    )
  }


  errorHandler(error: any) {
    let message = "";
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
