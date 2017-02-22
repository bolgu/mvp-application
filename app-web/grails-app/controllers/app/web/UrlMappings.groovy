package app.web

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

//        "/"(view: '/index')
//        "/"(uri: "/html5.html")
        "/"(view: '/html5')
        "500"(view: '/error')
        "404"(view: '/notFound')

        "400"(view: '/error400')
        "401"(view: '/error401')
    }
}
