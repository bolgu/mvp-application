import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { JobsData } from "../../providers/jobs-data";
import { GlobalVariable } from '../../app/global';

import { InAppBrowser } from 'ionic-native';

@Component({
  selector: 'page-jobs',
  templateUrl: 'jobs.html'
})
export class JobsPage {

  selectedItem: any;
  icons: string[];
  items: any;
  contextPath: string;

  constructor(public navCtrl: NavController, public navParams: NavParams, public jobsData: JobsData) {
    // If we navigated to this page, we will have an item available as a nav param
    this.selectedItem = navParams.get('item');
    this.contextPath = GlobalVariable.BASE_API_URL

    jobsData.listJobs().subscribe(data => {
      this.items = data;
      console.log("Data: " + JSON.stringify(this.items, null, 2))
    });
  }

  itemTapped(event, item) {
    // we use _self instead of _system
    new InAppBrowser(item.jobUrl, '_self');
  }

}
