//= wrapped

angular
    .module("app.admin.jobsboard.job")
    .controller("JobListController", JobListController);

function JobListController(Job, Publisher) {
    var vm = this;

    var max = 10, offset = 0;

    vm.publisherList = Publisher.list();

    Job.list({max: max, offset: offset}, function(data) {
        vm.jobList = data;
    });
}
