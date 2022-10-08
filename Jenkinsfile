def imageName = 'stalin.jfrog.io/default-docker-local/valaxy-rtp'
def registry  = 'https://stalin.jfrog.io'
def version   = '1.0.0'
def app
pipeline {
    agent {
       node {
         label "valaxy"
      }
    }
    stages {
        stage('Build') {
            steps {
                echo '<--------------- Building --------------->'
                sh 'printenv'
                sh 'mvn clean deploy -Dmaven.test.skip=true'
                echo '<------------- Build completed --------------->'
            }
        }
        
        stage(' Unit Test ') {
            steps {
                  echo '<--------------- Unit Testing started --------------->'
                  sh 'mvn surefire-report:report'
                  echo '<--------------- Unit Testing stopped --------------->'
            }
        }
        
        stage("Docker Build") {
            steps {
                  echo '<--------------- Docker Build started --------------->'
                  app = docker.build(imageName)
                  echo '<--------------- Docker Build stopped --------------->'               
            }   
        }
    }
 }
