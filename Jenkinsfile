node {
 
  stage('check tools') {
  sh "./gradlew --version"
  }

  stage('checkout') {
   checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '9282e8f4-0a01-4dc0-b392-c8a3ec152bf1', url: 'https://github.com/colaru/mvp-application.git']]])
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