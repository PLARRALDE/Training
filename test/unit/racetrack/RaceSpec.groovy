package racetrack

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Race)
class RaceSpec extends Specification {

    def setup() {


    }

    def cleanup() {


    }

    void testInMiles() {
        given: "test given"
        def race = new Race(distance:5.0)
        assertEquals 3.107, race.inMiles(),0
    }


    void "test something"() {
    }
}
