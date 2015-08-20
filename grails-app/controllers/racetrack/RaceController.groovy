
package racetrack

import grails.converters.JSON
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import net.sf.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONElement

class RaceController {

    //def scaffold = true

    def index() {

        def http = new HTTPBuilder("https://api.mercadolibre.com")

        http.request(Method.GET, ContentType.JSON) {
            uri.path = "/sites/MLA/search"
            uri.query = ["q":"ipod"]

            // response handler for a success response code
            response.success = { resp, json ->

                def restResponse = '[{"uid":10512213, "name":"Bob"},{"uid":7208201, "name":"John"},{"uid":10570, "name":"Jim"},{"uid":1799657, "name":"Sally"}]'

                render "Usted ha buscado: " + json.get("query") + " en el sitio: " + json.get("site_id") + "<br/>" + "<br/>"

                def paging = json.get("paging")

                render "Se han encontrado " + paging.get("total") + " registros" + "<br>" + "<br>"

                def results = json.get("results")

                render "<table>"

                render "<thead><th>Code</th><th>Title</th><th>Price</th><th>Currency Id</th><th>Thumbnail</th></thead>"

                results.each{
                    render "<tr>"
                    render "<td>" + it.id + " </td><td> " + it.title + " </td><td> " + it.price  + " </td><td>" + it.currency_id + "</td>"
                    render "<td><img src=\"" + it.thumbnail + "\"</td>"
                    render "</tr>"
                }

                render "</table>"

            }
        }
    }
}
