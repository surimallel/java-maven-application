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
        stage('Check Java') {
            steps {
                sh '''
                    if ! command -v javac >/dev/null 2>&1; then
                        echo "ERROR: javac is missing. Install a full JDK 17+ on this Jenkins agent and add its bin directory to PATH." >&2
                        exit 1
                    fi
                    java -version
                    javac --release 17 -version
                '''
            }
        }
        stage('Clean') {
            steps {
                sh 'mvn -B clean'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn -B compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn -B package'
            }
        }
        stage('Verify') {
            steps {
                sh 'mvn -B verify'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -f Dockerfile.calculator -t calculator-app:${BUILD_NUMBER} -t calculator-app:latest .'
                sh 'docker images calculator-app'
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'docker rm -f calculator-app || true'
                sh 'docker run -d -p 8093:80 --name calculator-app calculator-app:latest'
            }
        }
        /*
        stage('SonarQube Analysis') {
            steps {
                    withSonarQubeEnv('SonarQube') {
                       sh '''
                        mvn -B sonar:sonar \
                            -Dsonar.projectKey=sonar-first-project
                          '''
                        
                }
            }
        }
        stage ('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        */
        stage('Run sample application') {
            steps {
                sh 'mvn -B exec:java'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}
