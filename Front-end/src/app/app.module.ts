import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MaterialModule } from "./material";
import { MoviesComponent } from "./movies/movies.component";
import { HomePageComponent } from "./home-page/home-page.component";
import { MovieCardComponent } from "./movies/movie-card/movie-card.component";
import { AppRoutingModule } from "./app.routing.module";
import { RegistrationFormComponent } from "./registration-form/registration-form.component";
import { EventTypeComponent } from "./event-type/event-type.component";
import { MatCardModule } from "@angular/material/card";
import { OtherTicketedEventComponent } from "./other-ticketed-event/other-ticketed-event.component";
import { MovieEventComponent } from "./movie-event/movie-event.component";
import { MatSelectModule } from "@angular/material/select";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule, MatNavList } from "@angular/material";
import { ReactiveFormsModule } from "@angular/forms";
import { RsvpInvitationComponent } from "./rsvp-invitation/rsvp-invitation.component";
import { RsvpEventComponent } from "./rsvp-event/rsvp-event.component";
import { FlexLayoutModule } from "@angular/flex-layout";
import { LandingPageComponent } from "./landing-page/landing-page.component";
import { EventCardComponent } from "./home-page/event-card/event-card.component";
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { UserLoginComponent } from "./user-login/user-login.component";
import { TheatreComponent } from "./theatre/theatre.component";
import { RsvpEventpageComponent } from "./rsvp-eventpage/rsvp-eventpage.component";
import { MatRadioModule } from "@angular/material/radio";
import { SearchResultsComponent } from "./search-results/search-results.component";
import { MovieInfoComponent } from "./movie-info/movie-info.component";
import { MovieTheatreListComponent } from "./movie-info/movie-theatre-list/movie-theatre-list.component";
import { SideNavComponent } from "./side-nav/side-nav.component";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { UserProfileComponent } from "./user-profile/user-profile.component";
// import { ArenaLayoutSocketComponent } from './arena-layout-socket/arena-layout-socket.component';
import { TheatreLayoutComponent } from "./theatre-layout/theatre-layout.component";
import { Ng2CarouselamosModule } from "ng2-carouselamos";
import { SocketConnectionComponent } from "./socket-connection/socket-connection.component";
import { MultipleCheckboxesModule } from "multiple-checkboxes";
import {MatExpansionModule} from '@angular/material/expansion';
import { FooterComponent } from './footer/footer.component';
import {MatIconModule} from '@angular/material/icon';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import {MatMenuModule} from '@angular/material/menu';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { InviteeDetailsComponent } from './invitee-details/invitee-details.component';
import { ExistingUsernameValidatorDirective } from './registration-form/customValidaters/existingUserName';
import { PaymentPageComponent } from './payment-page/payment-page.component';
import { OrganiserRsvpViewComponent } from './organiser-rsvp-view/organiser-rsvp-view.component';
import { BookingHistoryComponent } from './booking-history/booking-history.component';
import { TicketedInfoComponent } from './ticketed-info/ticketed-info.component'
import { AlertsModule } from 'angular-alert-module';

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    HomePageComponent,
    MovieCardComponent,
    RegistrationFormComponent,
    EventTypeComponent,
    OtherTicketedEventComponent,
    RsvpEventComponent,
    MovieEventComponent,
    RsvpInvitationComponent,
    LandingPageComponent,
    EventCardComponent,
    NavBarComponent,
    RsvpEventpageComponent,
    SearchResultsComponent,
    MovieInfoComponent,
    MovieTheatreListComponent,
    UserLoginComponent,
    TheatreComponent,
    UserProfileComponent,
    SideNavComponent,
    UserProfileComponent,
    // ArenaLayoutSocketComponent
    TheatreLayoutComponent,
    SocketConnectionComponent,
    FooterComponent,
    InviteeDetailsComponent,
    ExistingUsernameValidatorDirective,
    PaymentPageComponent,
    OrganiserRsvpViewComponent,
    BookingHistoryComponent,
    TicketedInfoComponent
  ],
  imports: [
   
    OwlNativeDateTimeModule,
    OwlDateTimeModule,
    MatMenuModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    AppRoutingModule,
    MatCardModule,
    MatRadioModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatCheckboxModule,
    MultipleCheckboxesModule,
    Ng2CarouselamosModule,
    MatExpansionModule,
    MatSnackBarModule,
    MatIconModule,
    AlertsModule.forRoot()
  ],
  providers: [MatNativeDateModule],

  bootstrap: [AppComponent]
})
export class AppModule {}
