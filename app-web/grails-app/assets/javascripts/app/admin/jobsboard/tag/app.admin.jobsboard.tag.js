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

angular.module("app.admin.jobsboard.tag", ["ui.router", "ngResource", "app.admin.jobsboard.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('tag', {
            url: "/tag",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('tag.list', {
            url: "",
            templateUrl: "/app/admin/jobsboard/tag/list.html",
            controller: "TagListController as vm"
        })
        .state('tag.create', {
            url: "/create",
            templateUrl: "/app/admin/jobsboard/tag/create.html",
            controller: "TagCreateController as vm"
        })
        .state('tag.edit', {
            url: "/edit/:id",
            templateUrl: "/app/admin/jobsboard/tag/edit.html",
            controller: "TagEditController as vm"
        })
        .state('tag.show', {
            url: "/show/:id",
            templateUrl: "/app/admin/jobsboard/tag/show.html",
            controller: "TagShowController as vm"
        });
}
