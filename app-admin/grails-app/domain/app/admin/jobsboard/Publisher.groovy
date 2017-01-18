package app.admin.jobsboard

class Publisher {

    String name

    byte[] logo
    String url
    String twitterId
    String contactEmail

    String location

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
        url nullable: false, blank: false
        contactEmail nullable: false, blank: false, email: true
        location nullable: false, blank: false
        logo size: 0..1024 * 1024 * 1, maxSize: 1024 * 1024 * 1, nullable: false // 5M
    }

    static mapping = {
        cache true
    }

    @Override
    public String toString() {
        return name
    }
}
