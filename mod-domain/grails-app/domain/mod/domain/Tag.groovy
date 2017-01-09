package mod.domain

class Tag {

    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
    }
}
