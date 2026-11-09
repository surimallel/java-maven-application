pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Check Java') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'java -version && javac -version'
                        sh 'javac --release 17 -version'
                    } else {
                        bat 'java -version && javac --release 17 -version'
                    }
                }
            }
        }
        stage('Build and test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B clean verify'
                    } else {
                        bat 'mvn -B clean verify'
                    }
                }
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}
