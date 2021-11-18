import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { rating } from '../DTOs/rating';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  baseurl = 'http://localhost:8080/rate-movies';

  constructor(private http:HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  rateMovie(body:string):Observable<rating> {
    return this.http.post<rating>(this.baseurl, body, this.httpOptions).pipe(
      retry(0),
      catchError(this.errorHandler)
    );
  }

  getRatings():Observable<rating[]>{
    return this.http.get<rating[]>(this.baseurl + "/{username}").pipe(
      catchError(this.errorHandler)
    );
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
