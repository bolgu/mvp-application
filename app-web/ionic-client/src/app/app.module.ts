import { NgModule, ErrorHandler } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { JobsBoardApp } from './app.component';
import { JobsData } from '../providers/jobs-data.ts';
import { IndexPage } from '../pages/index/index';
import { JobsPage } from '../pages/jobs/jobs';

@NgModule({
  declarations: [
    JobsBoardApp,
    IndexPage,
    JobsPage
  ],
  imports: [
    IonicModule.forRoot(JobsBoardApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    JobsBoardApp,
    IndexPage,
    JobsPage
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, JobsData]
})
export class AppModule {}
