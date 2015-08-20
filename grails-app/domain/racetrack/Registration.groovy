package racetrack

class Registration {

    String name
    Date dateOfBirth
    String gender
    String address
    String city
    String state
    String zipcode
    String email
    static constraints = {
        race()
        runner()
        paid()
        dateCreated()
    }
    static belongsTo = [race:Race, runner:Runner]
    Boolean paid
    Date dateCreated
    Date lastUpdated
}
