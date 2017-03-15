import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar, Splashscreen } from 'ionic-native';

import { IndexPage } from '../pages/index/index';
import { JobsPage } from '../pages/jobs/jobs';
import {AuthService} from "../providers/auth";


@Component({
  templateUrl: 'app.html'
})
export class JobsBoardApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = IndexPage;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform) {
    this.initializeApp();

    // used for an example of ngFor and navigation

    this.pages = [
      { title: 'Home', component: IndexPage},
      { title: 'Jobs List', component: JobsPage}
    ];
  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      StatusBar.styleDefault();
      Splashscreen.hide();
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }
}
