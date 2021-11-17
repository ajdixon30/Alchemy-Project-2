import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/DTOs/movie';
import { MovieService } from 'src/app/Services/movie.service';

@Component({
  selector: 'app-admin-movie-table',
  templateUrl: './admin-movie-table.component.html',
  styleUrls: ['./admin-movie-table.component.css']
})
export class AdminMovieTableComponent implements OnInit {
  public movieService: MovieService;
  public movies: Movie[] = [];

  constructor(_movieService : MovieService) {
    this.movieService = _movieService;
   }

  ngOnInit(): void {
    this.movieService.getMovies().subscribe(data => {
      console.log(data);
      for(const item of data) {
        let {id, title, genre, pictureId, year} = item;
        this.movies.push({id, title, genre, pictureId, year})
        console.log(item);
      }
    })
  }

}
