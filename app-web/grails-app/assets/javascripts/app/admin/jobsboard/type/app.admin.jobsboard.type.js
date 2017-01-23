//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /app/admin/jobsboard/core/app.admin.jobsboard.core
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("app.admin.jobsboard.type", ["ui.router", "ngResource", "app.admin.jobsboard.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('type', {
            url: "/type",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('type.list', {
            url: "",
            templateUrl: "/app/admin/jobsboard/type/list.html",
            controller: "TypeListController as vm"
        })
        .state('type.create', {
            url: "/create",
            templateUrl: "/app/admin/jobsboard/type/create.html",
            controller: "TypeCreateController as vm"
        })
        .state('type.edit', {
            url: "/edit/:id",
            templateUrl: "/app/admin/jobsboard/type/edit.html",
            controller: "TypeEditController as vm"
        })
        .state('type.show', {
            url: "/show/:id",
            templateUrl: "/app/admin/jobsboard/type/show.html",
            controller: "TypeShowController as vm"
        });
}
