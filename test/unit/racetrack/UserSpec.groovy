package racetrack

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        given:
            User u = new User();
            testSimpleConstraints();
        //when:

        //then:
    }

    void testSimpleConstraints() {
        def user = new User(login:"someone",
                password:"blah",
                role:"SuperUser")
// oops—role should be either 'admin' or 'user'
// will the validation pick that up?
        assertFalse user.validate()
    }

}
