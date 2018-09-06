import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { RegistrationFormComponent } from "./registration-form/registration-form.component";
import { EventTypeComponent } from "./event-type/event-type.component";
import { OtherTicketedEventComponent } from "./other-ticketed-event/other-ticketed-event.component";
import { MovieEventComponent } from "./movie-event/movie-event.component";
import { HomePageComponent } from "./home-page/home-page.component";
import { RsvpEventComponent } from "./rsvp-event/rsvp-event.component";
import { RsvpInvitationComponent } from "./rsvp-invitation/rsvp-invitation.component";
import { LandingPageComponent } from "./landing-page/landing-page.component";
import { UserLoginComponent } from "./user-login/user-login.component"
import { AuthGuard } from "./guards/auth.guard"
import { TheatreComponent } from "./theatre/theatre.component";
import { RsvpEventpageComponent } from "./rsvp-eventpage/rsvp-eventpage.component";
import { SearchResultsComponent } from "./search-results/search-results.component";
import { MovieInfoComponent } from "./movie-info/movie-info.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";
import {TheatreLayoutComponent} from "./theatre-layout/theatre-layout.component";
import { SocketConnectionComponent } from "./socket-connection/socket-connection.component";
import { InviteeDetailsComponent } from "./invitee-details/invitee-details.component";
import {PaymentPageComponent} from "./payment-page/payment-page.component"
import{OrganiserRsvpViewComponent} from "./organiser-rsvp-view/organiser-rsvp-view.component";
import { BookingHistoryComponent } from "./booking-history/booking-history.component";
import {TicketedInfoComponent} from "./ticketed-info/ticketed-info.component";

const routes: Routes = [
  {
    path: "",
    component: LandingPageComponent
  },
  {
    path : "changeCity",
    component : LandingPageComponent
  },
  {
    path :"home",
    component :HomePageComponent
  },
  {
    path :"payment",
    component :PaymentPageComponent
  },
  {
    path :"profile",
    component :UserProfileComponent, canActivate:[AuthGuard]
  },
  {
    path :"search",
    component :SearchResultsComponent
  },
  {
    path: "userRegistration",
    component: RegistrationFormComponent
  },
  {
    path: "userLogin",
    component: UserLoginComponent
  },
  {
    path: "createEvent",
    component: EventTypeComponent, canActivate:[AuthGuard]
    // component: EventTypeComponent
  },
  {
    path: "createRSVPEvent",
    component: RsvpEventComponent, canActivate:[AuthGuard]
    // component: RsvpEventComponent
  },
  {
    path: ":id/rsvpInvitation",
    component: RsvpInvitationComponent ,canActivate:[AuthGuard]
    // component: RsvpInvitationComponent 
  },
  {
    path: "bookingHistory",
    component: BookingHistoryComponent, canActivate:[AuthGuard]
    // component: BookingHistoryComponent
  },
  {
    path: "movieinfo",
    component: MovieInfoComponent
  },
  {
    path: "ticketinfo",
    component: TicketedInfoComponent
  },
  {
    path: "createOtherTicketedEvent",
    component: OtherTicketedEventComponent, canActivate:[AuthGuard]
    // component: OtherTicketedEventComponent
  },
  {
    path: "createMovieEvent",
    component: MovieEventComponent, canActivate:[AuthGuard]
    // component: MovieEventComponent
  },
  {
    path: "theatre",
    component: TheatreComponent, canActivate:[AuthGuard]
    // component: TheatreComponent
  },
  {
    path:'rsvpEvent/:id',
    component:RsvpEventpageComponent
  },
  {
    path:"theatreLayout",
    component:TheatreLayoutComponent
  },
  {
    path: "socketConnection",
    component: SocketConnectionComponent
  },
  {
    path:":id/InviteeDetails",
    component:InviteeDetailsComponent, canActivate:[AuthGuard]
    // component:InviteeDetailsComponent
  },
  {
    path:":id/organiserRsvpView",
    component:OrganiserRsvpViewComponent, canActivate:[AuthGuard]
    // component:OrganiserRsvpViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents = [
  RegistrationFormComponent,
  EventTypeComponent,
  OtherTicketedEventComponent,
  MovieEventComponent,
  RsvpEventComponent,
  SearchResultsComponent,
  TheatreComponent,
  UserProfileComponent,
  UserLoginComponent,
  TheatreComponent,
  UserProfileComponent
];
