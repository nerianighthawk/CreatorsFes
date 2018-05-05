import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TopComponent } from './top/top.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { SideNavComponent } from './side-nav/side-nav.component';


const routes: Routes = [
  { path: 'analysis/:year/:period/:class/:key', component: AnalysisComponent},
  { path: 'top/:year/:key', component: TopComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
