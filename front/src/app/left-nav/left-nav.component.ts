import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-left-nav',
  templateUrl: './left-nav.component.html',
  styleUrls: ['./left-nav.component.css']
})
export class LeftNavComponent implements OnInit {

  private years = [{value: '0'}];

  constructor() { }

  ngOnInit() {
    this.years = [{value: '2018年'}];
  }

}
