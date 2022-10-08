def imageName = 'stalin.jfrog.io/default-docker-local/valaxy-rtp'
def registry  = 'https://stalin.jfrog.io'
def version   = '1.0.3'
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
                sh 'printenv
                echo '<------------- Build completed --------------->'
            }
        }
       
    }
}
