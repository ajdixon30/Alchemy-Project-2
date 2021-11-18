import { tinyRate } from "./tinyRate";

export interface display {
    id: number;
    title: string;
    genre: string;
    picture_id: string;
    year: string;
    ratingsByMovie: tinyRate[];
}