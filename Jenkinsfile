pipeline {
    agent any

    options {
        skipDefaultCheckout()
    }

    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
        }
        stage('Cleanup Docker') {
            steps {
                sh '''
                    containers=$(docker ps -aq)
                    if [ -n "$containers" ]; then
                        docker rm -f $containers
                    fi

                    images=$(docker images -aq)
                    if [ -n "$images" ]; then
                        docker rmi -f $images
                    fi
                '''
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -f Dockerfile.calculator -t calculator-app:${BUILD_NUMBER} .'
                sh 'docker images calculator-app'
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8094:80 --name calculator-app calculator-app:${BUILD_NUMBER}'
                sh 'docker ps --filter name=calculator-app'
            }
        }
    }
}
