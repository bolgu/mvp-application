package mod.domain

class Publisher {

    String name

    byte[] logo
    String url
    String twitterId
    String contactEmail

    String location
    Long salaryEstimate

    Date dateCreated
    Date lastUpdated

    static constraints = {

        name nullable: false, blank: false
        url nullable: false, blank: false
        contactEmail nullable: false, blank: false
        location nullable: false, blank: false

    }
}
