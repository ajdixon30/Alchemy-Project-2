import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, retry, throwError } from 'rxjs';
import { rating } from '../DTOs/rating';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  baseurl = 'http://localhost:8080/rate-movies';

  constructor(private http:HttpClient) {}

  rateMovie(body:rating):void {
    this.http.post<rating>(this.baseurl, body).pipe(
      retry(0),
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
