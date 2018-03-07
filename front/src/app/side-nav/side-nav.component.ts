import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})
export class SideNavComponent implements OnInit {

  private years = [{value: '0'}];
  private months = [{value: '0'}];

  public year: string;
  public key: string;

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.years = [{value: '2018年'}];
    this.months = [
      {value: '年度'},
      {value: '上半期'},
      {value: '下半期'},
      {value: '第1Q'},
      {value: '第2Q'},
      {value: '第3Q'},
      {value: '第4Q'},
      {value: '4月'},
      {value: '5月'},
      {value: '6月'},
      {value: '7月'},
      {value: '8月'},
      {value: '9月'},
      {value: '10月'},
      {value: '11月'},
      {value: '12月'},
      {value: '1月'},
      {value: '2月'},
      {value: '3月'},
    ];
  }

  display() {
    this.router.navigate(['top/' + this.year + '/' + this.key]);
  }
}
