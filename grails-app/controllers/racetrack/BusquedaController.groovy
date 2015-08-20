package racetrack

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class BusquedaController {

    def page
    def off
    def searchResult
    def query
    def site

    def index() {
        render view: "busqueda"
    }


    /*
    def http = new HTTPBuilder("https://api.mercadolibre.com")

        http.request(Method.GET, ContentType.JSON) {
            uri.path = "/sites/" + params.site + "/search"

            params.search = params.search.replaceAll("\\<.*?>", "")

            uri.query = ["q": params.search]

            // response handler for a success response code
            response.success = { resp, JSON ->

                def searchResult = new SearchResult()
                //Header
                searchResult.site_id = JSON.get("site_id")
                searchResult.query = JSON.get("query")
                //Iterate results
                def results = JSON.get("results")
                //def currencies = [:]
//                    results.each {
//                        def singleResult = new SingleResult()
//                        singleResult.id = it.id
//                        singleResult.site_id = it.site_id
//                        singleResult.title = it.title
//                        //singleResult.price = it.price
//                        singleResult.save()
//                        searchResult.addToSingleResult(singleResult)
//                    }
                searchResult.save()
                render view: "searchResults"
            }
        }

     */


    def meli_new_search(params) {
        def httpP = new HTTPBuilder("https://api.mercadolibre.com")
        off = params.offset

        httpP.request(Method.GET, ContentType.JSON) {
            uri.path = "/sites/" + params.site + "/search"
            params.search = params.search.replaceAll("\\<.*?>", "")
            query = params.search
            site = params.site
            uri.query = ["q": params.search, "offset": off,"limit":10]

            // response handler for a success response code
            response.success = { resp, json ->

                def searchResult = new SearchResult()
                //Header
                searchResult.site_id = json.get("site_id")
                searchResult.query = json.get("query")
                def paging = json.get("paging")
                def cantidad = paging.total

                List<SingleResult> lista = new ArrayList<SingleResult>()

                def results = json.get("results")

                results.each {

                    def singleResult = new SingleResult()
                    singleResult.id = it.id
                    singleResult.site_id = it.site_id
                    singleResult.title = it.title
                    singleResult.image = it.thumbnail

                    lista.add(singleResult)

                    //singleResult.price = it.price

                   // singleResult.save()

                    //searchResult.addToSingleResult(singleResult)
                }

                //searchResult.save()

                render(view: "searchResult", model:[list:lista, cant:cantidad])
            }
        }
    }

    def list(params) {
        def par = ["offset":params.offset,"search":query,"site":site]
        meli_new_search(par)

//        def httpN = new HTTPBuilder("https://api.mercadolibre.com")
//        def offset = Integer.parseInt(params.offset)
//        if(offset > 1) {
//            off = offset + 1
//        }else{
//            off = offset
//        }
//        render "QUERY: " + query + " SITE: " + site + " OFFSET: " + off + " MAX: " + params.max
//        httpN.request(Method.GET, ContentType.JSON) {
//            uri.path = "/sites/" + site + "/search"
//
//            uri.query = ["q": query, "offset": off]
//
//            response.success = { resp, json ->
//
//                def searchResult = new SearchResult()
//                //Header
//                searchResult.site_id = json.get("site_id")
//                searchResult.query = json.get("query")
//
//                def results = json.get("results")
//
//                results.each {
//                    def singleResult = new SingleResult()
//                    singleResult.id = it.id
//                    singleResult.site_id = it.site_id
//                    singleResult.title = it.title
//                    //singleResult.price = it.price
//
//                    //if(singleResult.find()==null){
//                        singleResult.save()
//                    //}
//                    searchResult.addToSingleResult(singleResult)
//                }
//
//                //if(searchResult.find()==null){
//                    searchResult.save()
//                //}
//                render(view: "searchResult")
//            }
        //}
    }



    def meli_search(params) {
        def httpOld = new HTTPBuilder("https://api.mercadolibre.com")
        page = Integer.parseInt(params.page)
        if (page == 0) {
            page = 1
        }
        off = (page - 1) * 50 + 1;

        httpOld.request(Method.GET, ContentType.JSON) {
            uri.path = "/sites/" + params.site + "/search"

            params.search = params.search.replaceAll("\\<.*?>", "")

            uri.query = ["q": params.search, "offset": off]

            // response handler for a success response code
            response.success = { resp, json ->

                render "<body>"
                render "<h4> Mostrando " + off + "  - " + (page*50) + "</h4>"

                render "Usted ha buscado: " + json.get("query") + " en el sitio: " + json.get("site_id") + "<br/>" + "<br/>"

                def paging = json.get("paging")

                render "Se han encontrado " + paging.get("total") + " registros" + "<br>" + "<br>"

                def results = json.get("results")

                render "<table>"

                render "<thead><th>Code</th><th>Title</th><th>Price</th><th>Thumbnail</th></thead>"

                HashMap<String, String> monedas = new HashMap<String, String>()

                results.each {
                    render "<tr>"
                    render "<td><a href=meli_detail_item/" + it.id + ">" + it.id + "</a> </td><td> " + it.title + " </td>"

                    def moneda

                    if (monedas.containsKey(it.currency_id)) {
                        moneda = monedas.get(it.currency_id)
                    } else {
                        moneda = buscarSimbolo(it.currency_id)
                        monedas.putAt(it.currency_id, moneda)
                    }

                    if (it.price == null) it.price = "Consultar"

                    render "<td>" + moneda + " " + it.price + "</td>"
                    render "<td><img src=\"" + it.thumbnail + "\"</td>"
                    render "</tr>"
                }
                render "</table>"
                render "<body>"
            }
        }
    }


    String buscarSimbolo(moneda) {

        if(moneda == null) return ""
        def httpMon = new HTTPBuilder("https://api.mercadolibre.com")

        httpMon.request(Method.GET, ContentType.JSON) {
            uri.path = "/currencies/" + moneda
            response.success = { resp, json ->
                return json.get("symbol")
            }

        }
    }

    def meli_detail_item(params) {
        def http = new HTTPBuilder("https://api.mercadolibre.com")

        http.request(Method.GET, ContentType.JSON) {
            uri.path = "/items/" + params.id

            response.success = { resp, json ->
                render "<table>"
                render "<tr><td><b>Identifier</b></td><td>"+json.id+"</td></tr>"
                render "<tr><td><b>Title</b></td><td>"+json.title+"</td></tr>"
                render "<tr><td><b>Category Id</b></td><td>"+json.category_id+"</td></tr>"
                render "<tr><td><b>Available qty</b></td><td>"+json.available_quantity+"</td></tr>"
                render "</table>"

                render "<h1>Product details</h1>"
                def pictures = json.get("pictures")

                pictures.each{
                    render "<img src=" + it.url + "alt =NO IMAGE" + "/>"

                }
            }
        }
    }
}
