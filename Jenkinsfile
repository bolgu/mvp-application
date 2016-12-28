#!groovy

node {

 stage('checkout') {
   checkout scm
   // checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '9282e8f4-0a01-4dc0-b392-c8a3ec152bf1', url: 'https://github.com/colaru/mvp-application.git']]])
 }

 stage('check tools') {
   // sh "cd /opt/bitnami/apps/jenkins/jenkins_home/jobs/mvp-application-pipeline/workspace"
   sh "export PATH=$PATH:/home/bitnami/.sdkman/candidates/gradle/3.2/bin"
   sh "pwd"
   sh "./gradle --version"
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