# mvp-application
MVP Grails 3 Sample Application

This is the source code for the Grails 3 - Step by Step Book sample application. The presentations site for this book is https://grailsthreebook.com. You can buy the book from https://leanpub.com/grails3book

The application is based on a multi project Gradle build so the application can be automatically built using Gradle - the build tool used by Grails 3. This application is available online and the deployment is done automatically to AWS cloud in a Continuous Deployment style using Jenkins on this location: http://application.eu-central-1.elasticbeanstalk.com

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
