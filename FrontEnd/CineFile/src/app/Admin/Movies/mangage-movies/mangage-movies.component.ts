import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/DTOs/movie';
import { MovieService } from 'src/app/Services/movie.service';

@Component({
  selector: 'app-mangage-movies',
  templateUrl: './mangage-movies.component.html',
  styleUrls: ['./mangage-movies.component.css']
})
export class MangageMoviesComponent implements OnInit {
  public movieService: MovieService;
  public movie: Movie[] = [];
  public _title!:string;
  public _id!:number;

  constructor(_movieService: MovieService) {
    this.movieService = _movieService;
   }

  newMovie(){
    this._title = (document.getElementById("title") as HTMLInputElement).value;
    let body = JSON.stringify({title:this._title});
    this.movieService.newMovie(body).subscribe(data => {
      console.log(data)
    })
  }

  deleteMovie(){
    this._id = (document.getElementById("movieId") as HTMLInputElement).valueAsNumber;
    this.movieService.deleteMovie(this._id).subscribe(data => {
      console.log(data)
    })
  }

  ngOnInit(): void {
  }

}
