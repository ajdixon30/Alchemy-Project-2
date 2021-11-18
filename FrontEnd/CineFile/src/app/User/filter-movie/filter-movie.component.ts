import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MovieService } from 'src/app/Services/movie.service';
import { Movie } from 'src/app/DTOs/movie';

@Component({
  selector: 'app-filter-movie',
  templateUrl: './filter-movie.component.html',
  styleUrls: ['./filter-movie.component.css']
})
export class FilterMovieComponent implements OnInit {
  movieService: MovieService;
  movieStorage: Movie[] = [];
  genres = new Set();
  years = new Set();
  movies: Movie[] = [];
  filter: string = "genre";
  value!: string;

  constructor(_movieService: MovieService) {
    this.movieService = _movieService;
   }

  /*  
  Function to get all movies in the database
  Stores the movie objects in an array
  Stores the genres and years in sets for use 
  in movie filtering
  */
  ngOnInit(): void {
    this.movieService.getMovies().subscribe(data => {
      for(const item of data) {
        let {id, title, genre, picture_id, year} = item;
        this.movies.push({id, title, genre, picture_id, year})
        this.genres.add(genre)
        this.years.add(year.substring(0,4))
      }
    })
    this.movies = [];
  }
/*
Function to search for gathering the list of filtered movies
This function returns all movies which match th criteria of 
the search filter
*/
  onSearch(): void {
    this.movieService.filterMovies(this.filter, this.value).subscribe(data => {
      for(const item of data) {
        let {id, title, genre, picture_id, year} = item;
        this.movies.push({id, title, genre, picture_id, year})
        console.log(item);
      }
    })
    this.movies = [];
  }

}
