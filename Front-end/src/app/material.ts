import { MatButtonModule, MatCheckboxModule, MatNavList } from "@angular/material";
import { NgModule } from "@angular/core";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatIconModule } from "@angular/material/icon";
import { MatMenuModule } from "@angular/material/menu";
import { MatCardModule } from "@angular/material/card";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatInputModule } from "@angular/material/input";
import { MatTooltipModule } from "@angular/material/tooltip";
import { MatTabsModule } from "@angular/material/tabs";
import { MatSelectModule } from "@angular/material/select";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { NativeDateModule } from "@angular/material";
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatRadioModule} from '@angular/material/radio';
@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatCardModule,
    MatGridListModule,
    MatInputModule,
    MatTooltipModule,
    MatTabsModule,
    MatSelectModule,
    MatDatepickerModule,
    NativeDateModule,
    MatSidenavModule,
    MatRadioModule
   
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatCardModule,
    MatGridListModule,
    MatInputModule,
    MatTooltipModule,
    MatTabsModule,
    MatSelectModule,
    MatDatepickerModule,
    NativeDateModule,
    MatSidenavModule,
    MatRadioModule
    
  ]
})
export class MaterialModule {}
