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

angular.module("app.admin.jobsboard.publisher", ["ui.router", "ngResource", "app.admin.jobsboard.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('publisher', {
            url: "/publisher",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('publisher.list', {
            url: "",
            templateUrl: "/app/admin/jobsboard/publisher/list.html",
            controller: "PublisherListController as vm"
        })
        .state('publisher.create', {
            url: "/create",
            templateUrl: "/app/admin/jobsboard/publisher/create.html",
            controller: "PublisherCreateController as vm"
        })
        .state('publisher.edit', {
            url: "/edit/:id",
            templateUrl: "/app/admin/jobsboard/publisher/edit.html",
            controller: "PublisherEditController as vm"
        })
        .state('publisher.show', {
            url: "/show/:id",
            templateUrl: "/app/admin/jobsboard/publisher/show.html",
            controller: "PublisherShowController as vm"
        });
}
