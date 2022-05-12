def imageName = 'stalin.jfrog.io/default-docker-local/twittertrend'
def registry  = 'https://stalin.jfrog.io'
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

    }
 }
