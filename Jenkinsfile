#!groovy

node {

 stage('checkout') {
   checkout scm
 }

 stage('check tools') {
   sh "cd /opt/bitnami/apps/jenkins/jenkins_home/jobs/mvp-application-pipeline/workspace"
   sh "export PATH=$PATH:/home/bitnami/.sdkman/candidates/gradle/3.2/bin"
   sh "pwd"
   sh "gradle --version"
 }

 stage('clean') {
   sh "./gradlew clean"
 }

 stage('test') {
   sh "./gradlew test"
 }

 stage('packaging') {
   sh "./gradlew :app-admin:war"
 }

 stage('deploying') {

 }
}