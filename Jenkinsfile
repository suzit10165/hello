pipeline {
  agent any
  environment {
    IMAGE = "suzit10165/hello:${BUILD_NUMBER}"
  }
  stages {
    stage('Checkout'){ steps { checkout scm } }

    stage('Build JAR'){ steps { bat 'mvn -q -DskipTests package' } }

    stage('Docker Build'){ steps { bat 'docker build -t %IMAGE% .' } }

    stage('Push to Docker Hub'){
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub',
                                         usernameVariable: 'DOCKER_USER',
                                         passwordVariable: 'DOCKER_PASS')]) {
          bat 'echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin'
          bat 'docker tag %IMAGE% suzit10165/hello:latest'
          bat 'docker push suzit10165/hello:%BUILD_NUMBER%'
          bat 'docker push suzit10165/hello:latest'
          bat 'docker logout'
        }
      }
    }
  }
  post { success { echo "Pushed image successfully." } }
}
