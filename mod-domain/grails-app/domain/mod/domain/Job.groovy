package mod.domain

class Job {

    String title
    String description
    Date expirationDate

    String jobUrl
    String contactEmail
    String applyInstructions

    Boolean remote
    Boolean active

    Date dateCreated
    Date lastUpdated

    Type type
    Publisher publisher

    static hasMany = [tags: Tag]

    static constraints = {
        title nullable: false, blank: false
        description nullable: false, blank: false
        jobUrl nullable: false, blank: false
        contactEmail nullable: false, blank: false
        applyInstructions nullable: false, blank: false
    }
}
