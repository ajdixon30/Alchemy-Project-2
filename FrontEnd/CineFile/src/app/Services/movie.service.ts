import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ObservableInput, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs';
import { Movie } from '../DTOs/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  baseUrl = 'http://localhost:8080';
  

  constructor(private client: HttpClient){}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  //Get all of the movies
  getMovies(): Observable<Movie[]> {
    return this.client.get<Movie[]>(this.baseUrl + "/display").pipe(
      retry(3),
      catchError(this.errorHandler)
    )
  }

  //Get filtered movies
  filterMovies(filter:string, value:string): Observable<Movie[]> {
    console.log(this.baseUrl + "/filter?filter=" + filter + "&value=" + value);
    return this.client.get<Movie[]>(this.baseUrl + "/filter?filter=" + filter + "&value=" + value)
  }

  //Add new movie to the database
  newMovie(title:string):Observable<Movie> {
    console.log(this.baseUrl + "/newMovie?title=" + title);
    return this.client.post<Movie>(this.baseUrl + "/newMovie?title=" + title, this.httpOptions).pipe(
      retry(3), 
      catchError(this.errorHandler)
    )
  }

  //Delete a movie from the database
  deleteMovie(id:number):Observable<Movie> {
    return this.client.delete<Movie>(this.baseUrl + "/delete?id=" + id, this.httpOptions).pipe(
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
