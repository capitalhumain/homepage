package app

import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import spock.lang.Specification

class FunctionalSpec extends Specification {
    void "default handler should render Hello, World!"() {
        setup:
        def aut = new GroovyRatpackMainApplicationUnderTest()
        
        when:
        def response = aut.httpClient.text

        then:
        response == "Hello, World!"

        cleanup:
        aut.close()
    }
}
