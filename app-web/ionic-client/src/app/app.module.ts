import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { JobsBoardApp } from './app.component';
import { JobsData } from '../providers/jobs-data.ts';
import { IndexPage } from '../pages/index/index';
import { JobsPage } from '../pages/jobs/jobs';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {AuthHttp, AuthConfig} from 'angular2-jwt';
import {Http} from "@angular/http";

@NgModule({
  declarations: [
    JobsBoardApp,
    IndexPage,
    JobsPage
  ],
  imports: [
    IonicModule.forRoot(JobsBoardApp),
    FormsModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    JobsBoardApp,
    IndexPage,
    JobsPage
  ],
  providers: [
    {provide: ErrorHandler,
    useClass: IonicErrorHandler}, JobsData,
    {provide: AuthHttp, useFactory: (http) => {
      return new AuthHttp(new AuthConfig, http);
    },
      deps: [Http]}
    ]
})
export class AppModule {}
