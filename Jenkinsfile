node {
 
  stage('check tools') {
  sh "gradle --version"
  }

  stage('checkout') {
    git url: 'https://github.com/colaru/mvp-application.git'
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