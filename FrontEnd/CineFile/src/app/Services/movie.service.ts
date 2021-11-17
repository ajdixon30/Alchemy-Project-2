import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs';
import { Movie } from '../DTOs/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  
  baseUrl = 'http://localhost:8080/movie';
  

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
  filterMovies(filter:string,value:string): Observable<Movie[]> {
    //Set the parameters for the Http request
    const params = new HttpParams()
    .set("filter", filter)
    .set("value", value);

    return this.client.get<Movie[]>(this.baseUrl + "/filter", {params})
  }

  //Add new movie to the database
  newMovie(body:string):Observable<Movie> {

    return this.client.post<Movie>(this.baseUrl + "/newMovie", body, this.httpOptions).pipe(
      retry(3), 
      catchError(this.errorHandler)
    )
  }

  //Delete a movie from the database
  deleteMovie(id:number):Observable<Movie> {
    //Set the parameters for the Http request
    const params = new HttpParams()
    .set("id",id);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.client.delete<Movie>(this.baseUrl + "/delete", {headers, params}).pipe(
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
