import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import { GlobalVariable } from '../app/global';
import {AuthHttp} from 'angular2-jwt';

@Injectable()
export class JobsData {

  constructor(public http: Http, public authHttp: AuthHttp) {
    console.log('Enter JobsData Provider');
  }

  listJobs() {
    return this.authHttp.get(GlobalVariable.SECURED_API_URL + "/jobs").map((res) => res.json())
  }

  listFeaturedJobs() {
      return this.http.get(GlobalVariable.SECURED_API_URL + "/featuredJobs").map((res) => res.json())
  }
}
