package racetrack

class SingleResult {

    static constraints = {
    }

    static belongsTo = [searchResult:SearchResult]

    String id
    String site_id
    String title
    String image
    int price
}
