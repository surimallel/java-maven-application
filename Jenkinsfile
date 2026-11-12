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
                        sh '''
                            if ! command -v javac >/dev/null 2>&1; then
                                echo "ERROR: javac is missing. Install a full JDK 17+ on this Jenkins agent and add its bin directory to PATH." >&2
                                exit 1
                            fi
                            java -version
                            javac --release 17 -version
                        '''
                    } else {
                        bat '''
                            @where javac >nul 2>nul
                            @if errorlevel 1 (
                                echo ERROR: javac is missing. Install a full JDK 17+ on this Jenkins agent and add its bin directory to PATH. 1>&2
                                exit /b 1
                            )
                            @java -version
                            @javac --release 17 -version
                        '''
                    }
                }
            }
        }
        stage('Clean') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B clean'
                    } else {
                        bat 'mvn -B clean'
                    }
                }
            }
        }
        stage('Compile') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B compile'
                    } else {
                        bat 'mvn -B compile'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B test'
                    } else {
                        bat 'mvn -B test'
                    }
                }
            }
        }
        stage('Package') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B package'
                    } else {
                        bat 'mvn -B package'
                    }
                }
            }
        }
        stage('Verify') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B verify'
                    } else {
                        bat 'mvn -B verify'
                    }
                }
            }
        }
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
        stage('Run sample application') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B exec:java'
                    } else {
                        bat 'mvn -B exec:java'
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
