import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChartConst } from 'o2-chart-lib';

@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.css']
})
export class TopComponent implements OnInit {

  private departments = [{
    id: 'd00000',
    name: '開発部'
  }]
  private departmentId = 'd00000';
  private year;
  private month;

  constructor(
    private router: Router,
    route: ActivatedRoute,
  ) {}

  departmentStyle(id: string) {
    var css = {
      'width': '200px',
      'height': '200px',
      'background-color': 'blue'
    }
    return css
  }

  onDepartment(id: string) {
    this.router.navigate(['department/' + id + '/' + this.year + this.month]);
  }

  ngOnInit() {
  }

}
