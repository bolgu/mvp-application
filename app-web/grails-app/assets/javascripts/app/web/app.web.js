//= wrapped
//= require /angular/angular
//= require /app/web/core/app.web.core
//= require /app/web/index/app.web.index
//= require /app/admin/jobsboard/core/app.admin.jobsboard.core
//= require /app/admin/jobsboard/job/app.admin.jobsboard.job
//= require /app/admin/jobsboard/publisher/app.admin.jobsboard.publisher
//= require /app/admin/jobsboard/tag/app.admin.jobsboard.tag
//= require /app/admin/jobsboard/type/app.admin.jobsboard.type

angular.module("app.web", [
        "app.web.core",
        "app.web.index",
        "app.admin.jobsboard.core",
        "app.admin.jobsboard.job",
        "app.admin.jobsboard.tag",
        "app.admin.jobsboard.type",
        "app.admin.jobsboard.publisher"
    ]);
