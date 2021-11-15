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
  public title!:string;
  public id!:number;

  constructor(_movieService: MovieService) {
    this.movieService = _movieService;
   }

  submitMovie(){
    this.title = (document.getElementById("title") as HTMLInputElement).value;
    this.movieService.newMovie(this.title).subscribe(data => {
      console.log(data)
    })
  }

  deleteMovie(){
    this.id = (document.getElementById("request") as HTMLInputElement).valueAsNumber;
    this.movieService.deleteMovie(this.id).subscribe(data => {
      console.log(data)
    })
  }

  ngOnInit(): void {
  }

}
