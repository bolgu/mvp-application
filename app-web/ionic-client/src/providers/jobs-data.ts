import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { GlobalVariable } from '../app/global';

/*
 Generated class for the JobsData provider.

 See https://angular.io/docs/ts/latest/guide/dependency-injection.html
 for more info on providers and Angular 2 DI.
 */
@Injectable()
export class JobsData {

  data;

  constructor(public http: Http) {
    console.log('Enter JobsData Provider');
  }

  listJobs() {
    return this.http.get(GlobalVariable.BASE_API_URL + "/job").map((res) => res.json())
  }
}
