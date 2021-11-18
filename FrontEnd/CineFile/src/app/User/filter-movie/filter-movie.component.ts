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
  years: string[] = [];
  movies: Movie[] = [];
  filter: string = "genre";
  value!: string;

  constructor(_movieService: MovieService) {
    this.movieService = _movieService;
   }

  ngOnInit(): void {
    this.movieService.getMovies().subscribe(data => {
      for(const item of data) {
        let {id, title, genre, picture_id, year} = item;
        this.movies.push({id, title, genre, picture_id, year})
        this.genres.add(genre)
        this.years.push(year.substring(0,4))
      }
    })
    this.movies = [];
  }

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
