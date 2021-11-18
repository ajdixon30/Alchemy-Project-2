import { Movie } from 'src/app/DTOs/movie';
import { MovieService } from 'src/app/Services/movie.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main-page-movies',
  templateUrl: './main-page-movies.component.html',
  styleUrls: ['./main-page-movies.component.css']
})
export class MainPageMoviesComponent implements OnInit {
  public movieService: MovieService;
  public movies: Movie[] = [];

  constructor(_movieService : MovieService) {
    this.movieService = _movieService;
   }

  ngOnInit(): void {
    this.movieService.getMovies().subscribe(data => {
      console.log(data);

      for(const item of data) {
        let {id, title, genre, picture_id, year} = item;
        if(this.movies.length<=4){
          this.movies.push({id, title, genre, picture_id, year})
        }
      }
    })
  }

}
