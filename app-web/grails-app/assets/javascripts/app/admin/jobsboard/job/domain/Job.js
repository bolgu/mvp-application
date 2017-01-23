//= wrapped
//= require /angular/angular-resource

angular
    .module("app.admin.jobsboard.job")
    .factory("Job", Job);

function Job($resource, domainListConversion, domainConversion, domainToManyConversion) {
    var Job = $resource(
        "job/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true, transformResponse: [angular.fromJson, domainListConversion("Type", "type", "domainConversion"), domainListConversion("Publisher", "publisher", "domainConversion"), domainListConversion("Tag", "tags", "domainToManyConversion")]},
         "get": {method: 'GET', transformResponse: [angular.fromJson, domainConversion("Type", "type"), domainConversion("Publisher", "publisher"), domainToManyConversion("Tag", "tags")]}}
    );

    Job.list = Job.query;

    Job.prototype.toString = function() {
        return 'app.admin.jobsboard.Job : ' + (this.id ? this.id : '(unsaved)');
    };

    return Job;
}
