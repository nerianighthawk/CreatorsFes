import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { provideRoutes, RouterModule } from '@angular/router';
import {
  MatInputModule,
  MatButtonModule,
  MatSelectModule,
  MatToolbarModule,
  MatRadioModule,
  MatCheckboxModule,
  MatTabsModule,
  MatExpansionModule,
  MatSidenavModule,
  MatListModule,
  MatCardModule,
} from '@angular/material';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ChartsModule } from 'ng2-charts'
import { TopComponent } from './top/top.component';
import { AppRoutingModule } from './app-routing.module';
import { SideNavComponent } from './side-nav/side-nav.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { LeftNavComponent } from './left-nav/left-nav.component';
import { MasterService } from './service/master.service';


@NgModule({
  declarations: [
    AppComponent,
    TopComponent,
    SideNavComponent,
    AnalysisComponent,
    LeftNavComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    RouterModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatToolbarModule,
    MatRadioModule,
    MatCheckboxModule,
    MatExpansionModule,
    MatSidenavModule,
    MatTabsModule,
    MatListModule,
    MatCardModule,
    ChartsModule,
    HttpClientModule,
  ],
  providers: [
    MasterService,
    {provide: 'ApiEndpoint', useValue: 'http://localhost:8080/api/v1/'},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
