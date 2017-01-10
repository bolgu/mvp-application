package app.admin.jobsboard

class Type {

//    Commission
//    Volunteer
//    Part-time
//    Internship
//    Full-time
//    Temporary
//    Apprenticeship
//    Contract
//    Permanent

    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
    }
}
