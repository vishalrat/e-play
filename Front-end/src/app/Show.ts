import { ArenaLayout } from "./models/ArenaLayout";

export class Show {
    showId: number;
    price: number;
    seatRemaining: number;
    startTime: Date;
    date: Date;
    movieEventId: number;
    status:boolean;
    layout: ArenaLayout;
}

