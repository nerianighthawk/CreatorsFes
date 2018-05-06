import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit {

  public years: any;
  private months: any;
  public term: string;
  public target: string;
  public key: string;

  constructor(
    @Inject('ApiEndpoint') private api_path: string,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit() {
      this.http.get(this.api_path + "axes/year").subscribe(
        json => {
          this.years = json;
        }
      )
      this.months = [
        {value: '年度', key: 'year'},
        {value: '上半期', key: 'half:1'},
        {value: '下半期', key: 'half:2'},
        {value: '第1Q', key: 'quarter:1'},
        {value: '第2Q', key: 'quarter:2'},
        {value: '第3Q', key: 'quarter:3'},
        {value: '第4Q', key: 'quarter:4'},
        {value: '4月', key: 'month:04'},
        {value: '5月', key: 'month:05'},
        {value: '6月', key: 'month:06'},
        {value: '7月', key: 'month:07'},
        {value: '8月', key: 'month:08'},
        {value: '9月', key: 'month:09'},
        {value: '10月', key: 'month:10'},
        {value: '11月', key: 'month:11'},
        {value: '12月', key: 'month:12'},
        {value: '1月', key: 'month:01'},
        {value: '2月', key: 'month:02'},
        {value: '3月', key: 'month:03'},
      ];
  }

  display(target: string) {
    for(let m of this.months){
      console.log(target);
      if(target == m.value){
        this.key = m.key;
      }
    }
    this.router.navigate(['top/' + this.term + '/' + this.key]);
    location.reload();
  }
}
