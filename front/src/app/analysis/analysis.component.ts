import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

import { MasterService } from '../service/master.service';

@Component({
  selector: 'app-analysis',
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.css']
})
export class AnalysisComponent implements OnInit {

  public barChartOptions:any = {
    scaleShowVerticalLines: false,
    responsive: true,
  };
  public barChartLabels:string[] = ['4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月', '1月', '2月', '3月'];
  public barChartType:string = 'bar';
  public barChartLegend:boolean = true;

  public barChartData:any[];

  public year: string;
  public period: string;
  public class: string;
  public key: string[];
  public loadPoint;
  public count = 0;

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }

  constructor(
    @Inject('ApiEndpoint') private api_path: string,
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private master: MasterService,
  ) { }

  ngOnInit() {
    this.year = this.route.snapshot.params['year'];
    this.period = this.route.snapshot.params['period'];
    this.class = this.route.snapshot.params['class'];
    this.key = this.route.snapshot.params['key'].split(':');
    this.key.pop();
    let chartData: any;
    this.loadPoint = this.key.length;
    this.http.get(this.api_path + "overtimes?axis00=" + this.class + "&axis10=year:" + this.year + "&axis20=month").subscribe(
      json => {
        for(let c in json['children']) {
          for(let k of this.key) {
            if(this.class + ':' + k == c){
              let d = json['children'][c];
              if(d.axisName == this.class){
                this.master.getDepartment(d.axisValue, d.axisName).then(
                  res => {
                    if(this.period == 'quarter'){
                      chartData = {
                        data: [
                          d['children']['month:04']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'] + d['children']['month:06']['result']['average'],
                          d['children']['month:07']['result']['average'],
                          d['children']['month:07']['result']['average'] + d['children']['month:08']['result']['average'],
                          d['children']['month:07']['result']['average'] + d['children']['month:08']['result']['average'] + d['children']['month:09']['result']['average'],
                          d['children']['month:10']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'] + d['children']['month:12']['result']['average'],
                          d['children']['month:01']['result']['average'],
                          d['children']['month:01']['result']['average'] + d['children']['month:02']['result']['average'],
                          d['children']['month:01']['result']['average'] + d['children']['month:02']['result']['average'] + d['children']['month:03']['result']['average'],
                        ],
                        label: res,
                      }
                    } else if(this.period == 'half'){
                      chartData = {
                        data: [
                          d['children']['month:04']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'] + d['children']['month:06']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'] + d['children']['month:06']['result']['average'] + d['children']['month:07']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'] + d['children']['month:06']['result']['average'] + d['children']['month:07']['result']['average'] + d['children']['month:08']['result']['average'],
                          d['children']['month:04']['result']['average'] + d['children']['month:05']['result']['average'] + d['children']['month:06']['result']['average'] + d['children']['month:07']['result']['average'] + d['children']['month:08']['result']['average'] + d['children']['month:09']['result']['average'],
                          d['children']['month:10']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'] + d['children']['month:12']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'] + d['children']['month:12']['result']['average'] + d['children']['month:01']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'] + d['children']['month:12']['result']['average'] + d['children']['month:01']['result']['average'] + d['children']['month:02']['result']['average'],
                          d['children']['month:10']['result']['average'] + d['children']['month:11']['result']['average'] + d['children']['month:12']['result']['average'] + d['children']['month:01']['result']['average'] + d['children']['month:02']['result']['average'] + d['children']['month:03']['result']['average'],
                        ],
                        label: res,
                      }
                    }
                    if(this.barChartData){
                      this.barChartData.push(chartData);
                    } else {
                      this.barChartData = [chartData];
                    }
                    this.count++;
                    console.log(this.barChartData);
                  }
                )
              }
            }
          }
        }
      }
    )
  }

}
