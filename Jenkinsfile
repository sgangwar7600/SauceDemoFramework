pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Check Java') {
            steps {
                bat 'java -version'
            }
        }

        stage('Check Maven') {
            steps {
                bat 'mvn -version'
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/sgangwar7600/SauceDemoFramework.git'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }
    }

    post {

        always {

            archiveArtifacts(
                artifacts: 'Output/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'test-output/**/*',
                allowEmptyArchive: true
            )
        }

        success {
            echo 'SauceDemo Framework Execution Successful'
        }

        failure {
            echo 'SauceDemo Framework Execution Failed'
        }
    }
}

