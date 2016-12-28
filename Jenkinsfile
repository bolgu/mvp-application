#!groovy

node {


 stage('checkout') {
   checkout scm
 }

 stage('check tools') {
   // sh "cd /opt/bitnami/apps/jenkins/jenkins_home/jobs/pipeline-mvp-application/workspace"
   // sh "export GRADLE_HOME=/home/bitnami/.sdkman/candidates/gradle/3.2"
   // sh "export PATH=$PATH:/home/bitnami/.sdkman/candidates/gradle/3.2/bin"
   sh "pwd"
   sh "./gradlew --version"
 }

 stage('clean') {
   sh "./gradlew clean"
 }

 stage('unit test') {
   sh "./gradlew :web-sandbox:test"
 }

 stage('packaging') {
   sh "./gradlew :web-sandbox:war"
 }

 stage('deploying') {
    sh "./gradlew :web-sandbox:deployTest"
 }
}