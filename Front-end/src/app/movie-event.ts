import { Show } from "./Show";
export class MovieEvent {
    movieEventId: number;
    movieId:number;
    theatreId: number;
    showCount: number;
    week:number;
    shows:Show[];
    city:string;
    userName:string;
    showTimes:string;
  }