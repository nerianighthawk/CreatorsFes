import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {

  public years: any;
  public departments = [];
  public users = [];
  public class: string;
  public term: string;
  public period: string;

  constructor(
    @Inject('ApiEndpoint') private api_path: string,
    private http: HttpClient,
    private router: Router
  ) {}

  display(): void {
    let key = '';
    if(this.class == "department"){
      for(let d of this.departments){
        if(d.selected){
          key = key + d.key + ':'
        }
      }
    } else if(this.class == "user"){
      for(let u of this.users){
        if(u.selected){
          key = key + u.key + ':'
        }
      }
    }
    this.router.navigate(['analysis/' + this.term + '/' + this.period + '/'+ this.class + '/' + key]);
    location.reload();
  }

  dchanged(): void {
    let count = 0;
    for(let d of this.departments){
      if(d.selected){
        count++;
      }
    }
    for(let d of this.departments){
      if((count >= 3) && !(d.selected)){
        d.disabled = true;
      } else {
        d.disabled = false;
      }
    }
  }

  uchanged(): void {
    let count = 0;
    for(let u of this.users){
      if(u.selected){
        count++
      }
    }
    for(let u of this.users){
      if((count >= 3) && !(u.selected)){
        u.disabled = true;
      } else {
        u.disabled = false;
      }
    }
  }

  ngOnInit() {
    let department: any;
    let user: any;
    let array: any;
      this.http.get(this.api_path + "masters/department").subscribe(
        json => {
          array = json;
          for(let d of array){
            department = {
              key: d['key'],
              value: d['value'],
              selected: false,
              disabled: false,
            };
            this.departments.push(department);
          }
        }
      )
      this.http.get(this.api_path + "axes/year").subscribe(
        json => {
          this.years = json;
        }
      )
      this.http.get(this.api_path + "masters/user").subscribe(
        json => {
          array = json
          for(let u of array){
            user = {
              key: u['key'],
              value: u['value'],
              selected: false,
              disabled: false,
            }
            this.users.push(user)
          }
        }
      )
  }
}
