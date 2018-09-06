import {Theatre} from "./theatre";

export class Movie {
  id: number;
  name: string;
  backGroundPoster: string;
  cardPoster: string;
  genre: string;
  language: string;
  reviews: string;
  cast: string;
  description: string;
  rating: number;
  duration:number;
  releaseDate: string
  movieEvents: string

  theatre: Array<Theatre>;
}
