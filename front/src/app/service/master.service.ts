import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class MasterService {

  constructor(
    @Inject('ApiEndpoint') private api_path: string,
    private http: HttpClient,
  ) { }

  getDepartment(departmentId: string,type: string) {
    let array: any;
    let result: string;
      return this.http.get(this.api_path + "masters/" + type).toPromise().then(
        json => {
          array = json;
          for(let d of array){
            if(d['key'] == departmentId){
              result = d.value
            }
          }
          return result;
        }
      )
  }
}
