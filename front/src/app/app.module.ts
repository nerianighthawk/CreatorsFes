import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
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
} from '@angular/material'

import { AppComponent } from './app.component';
import { O2ChartComponent, ChartConst } from 'o2-chart-lib';
import { ChartsModule } from 'ng2-charts'
import { TopComponent } from './top/top.component';
import { AppRoutingModule } from './app-routing.module';
import { SideNavComponent } from './side-nav/side-nav.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { LeftNavComponent } from './left-nav/left-nav.component';


@NgModule({
  declarations: [
    AppComponent,
    O2ChartComponent,
    TopComponent,
    SideNavComponent,
    AnalysisComponent,
    LeftNavComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
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
    ChartsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
