package app.microservice

class UrlMappings {

    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/"(controller: 'application', action:'index')

        "/api/v1.0/jobsImports"(controller: "importJobs", action: "startJobsImport")
        "/api/v1.0/jobsDeltas"(controller: "importJobs", action: "importDeltaJobs")

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
