# mvp-application

## MVP Grails 3 Sample Application

This is the source code for the Grails 3 - Step by Step Book sample application. The presentations site for this book is https://grailsthreebook.com. You can buy the book from https://leanpub.com/grails3book. You can read the sample chapter from https://leanpub.com/grails3book/read

The application is based on a multi project Gradle build so the application can be automatically built using Gradle - the build tool used by Grails 3 - a build scan here: https://scans.gradle.com/s/udze3slaacijm/infrastructure . This application is available online and the deployment is done automatically to AWS cloud in a Continuous Deployment style using Jenkins on this location: http://application.eu-central-1.elasticbeanstalk.com. Serenity living documentation: http://serenity-mvp.s3-website.eu-central-1.amazonaws.com

## Main applictions and plugins

We structured our project in multiple applications and plugins, each having a clear responsability and a given [Grails 3 profile](http://docs.grails.org/latest/guide/profiles.html):

| *Name*            | *Type*        |  *Grails profile*  | *Description*                                                     |
| ----------------- |:-------------:| ------------------:| -----------------------------------------------------------------:|
| app-admin         | application   | web                | The admin portal application (for intranet) used for administration
| app-web           | application   | angular2           | The web application exposed to the customers (for internet)
| mod-domain        | plugin        | plugin             | A plugin with the application domain (domain services and entities)
| mod-mobile        | plugin        | rest-api-plugin    | A plugin containing the REST API consumed by a mobile app
| app-microservice  | application   | rest-api           | A miroservice exposing a REST API for used by the other apps

The *mod-domain* plugin will be a dependency for all the applications because is the herth of the application. The *micro-search* microservice will be called by the others applications for some specific services using a REST API.

## Sandbox application and plugin

There is a dependency of *mod-sandbox* in *web-sandbox*:
We have edited the *build.gradle* and added the dependency:

    nano web-sandbox/build.gradle
    
We added this before *dependencies* section:

    grails {
        plugins {
            compile project(":mod-sandbox")
        }
    }

We can run the *web-sandbox* application:

    grails run-app --verbose
    
We get (you can seee that *mod-sandbox* is compiled before *web-sandbox*):

    | Running application...
    :mod-sandbox:compileAstJava UP-TO-DATE
    :mod-sandbox:compileAstGroovy UP-TO-DATE
    :mod-sandbox:processAstResources UP-TO-DATE
    :mod-sandbox:astClasses UP-TO-DATE
    :mod-sandbox:compileJava UP-TO-DATE
    :mod-sandbox:configScript UP-TO-DATE
    :mod-sandbox:compileGroovy UP-TO-DATE
    :mod-sandbox:assetPluginPackage UP-TO-DATE
    :mod-sandbox:copyCommands UP-TO-DATE
    :mod-sandbox:copyTemplates UP-TO-DATE
    :mod-sandbox:processResources UP-TO-DATE
    :web-sandbox:compileJava UP-TO-DATE
    :web-sandbox:compileGroovy UP-TO-DATE
    :web-sandbox:buildProperties UP-TO-DATE
    :web-sandbox:processResources UP-TO-DATE
    :web-sandbox:classes UP-TO-DATE
    :web-sandbox:findMainClass
    :web-sandbox:bootRun
    
    .........................................................

    Grails application running at http://localhost:8080 in environment: development
