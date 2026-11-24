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
        stage('Build and Deploy to JFrog') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'artifactory-student-9', usernameVariable: 'JFROG_USER', passwordVariable: 'JFROG_PASS')]) {
                    sh '''
                        cat > .mvn-settings.xml <<EOF
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
  <servers>
    <server>
      <id>artifactory-student-9</id>
      <username>${JFROG_USER}</username>
      <password>${JFROG_PASS}</password>
    </server>
  </servers>
</settings>
EOF
                        mvn -B -s .mvn-settings.xml clean deploy
                        rm -f .mvn-settings.xml
                    '''
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
