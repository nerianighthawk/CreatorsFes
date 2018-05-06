import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { Router, ActivatedRoute } from '@angular/router';

import { MasterService } from '../service/master.service';

@Component({
  selector: 'app-top',
  templateUrl: './top.component.html',
  styleUrls: ['./top.component.css']
})
export class TopComponent implements OnInit {

  public departments = [];
  private department: any;
  public year;
  public key;

  constructor(
    @Inject('ApiEndpoint') private api_path: string,
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private master: MasterService,
  ) {}

  departmentStyle(dep: any) {
    let radius = (dep.average - 70) * 30;
    let color = 'yellow';
    if(dep.standardDeviation >= 83){
      color = 'red'
    } else if(dep.standardDeviation >= 80) {
      color = 'orange';
    }
    var css = {
      'width': radius + 'px',
      'height': radius + 'px',
      'background-color': color,
    }
    return css
  }

  onDepartment(id: string) {
    this.router.navigate(['department/' + id + '/' + this.year]);
    this.ngOnInit();
  }

  ngOnInit() {
    this.year = this.route.snapshot.params['year'];
    this.key = this.route.snapshot.params['key'];
    this.http.get(this.api_path + "overtimes?axis00=department&axis10=year:" + this.year + "&axis20=" + this.key).subscribe(
      json => {
        for(let c in json['children']) {
          let d = json['children'][c];
          if(d.axisName == "department"){
            this.master.getDepartment(d.axisValue, d.axisName).then(
              res => {
                this.department = {
                  departmentId: d.axisValue,
                  departmentName: res,
                  average: d.result.average,
                  standardDeviation: d.result.standardDeviation,
                }
                console.log(this.department);
                this.departments.push(this.department);
              }
            )
          }
        }
      }
    )
  }

}
