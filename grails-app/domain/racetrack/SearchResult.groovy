package racetrack

class SearchResult {

    static constraints = {
    }

    static  hasMany = [singleResult:SingleResult]

    String site_id

    String query
}
