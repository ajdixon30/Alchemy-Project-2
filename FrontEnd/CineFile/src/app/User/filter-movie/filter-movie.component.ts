import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/Services/movie.service';
import { display } from 'src/app/DTOs/displayMovie';
import { RatingService } from 'src/app/Services/rating.service';

@Component({
  selector: 'app-filter-movie',
  templateUrl: './filter-movie.component.html',
  styleUrls: ['./filter-movie.component.css']
})
export class FilterMovieComponent implements OnInit {
  movieService: MovieService;
  rateService:RatingService;
  genres = new Set();
  years = new Set();
  movieList:display[] = [];
  filter: string = "genre";
  value!: string;
  str!:string;
  
  

  constructor(_movieService: MovieService, rateService:RatingService) {
    this.movieService = _movieService;
    this.rateService = rateService;
   }

  ngOnInit(): void {
    this.movieService.getMoviesWithRating().subscribe(data => {
      for(const item of data) {
        let {id, title, genre, picture_id, year, ratingsByMovie} = item;

        this.rateService.getAverage(id).subscribe(data=>{
          this.str = data.toString();
        });

        this.movieList.push({id, title, genre, picture_id, year, ratingsByMovie});
        this.genres.add(genre);
        this.years.add(year.substring(0,4));
        console.log(item);
      }
    })
    this.movieList = [];
  }

  onSearch(): void {
    this.movieService.filterRatedMovies(this.filter, this.value).subscribe(data => {
      for(const item of data) {
        let {id, title, genre, picture_id, year, ratingsByMovie} = item;
        this.movieList.push({id, title, genre, picture_id, year, ratingsByMovie})
        console.log(item);
      }
    })
    this.movieList = [];
  }

}
