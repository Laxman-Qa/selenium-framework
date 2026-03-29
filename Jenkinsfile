pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK11'
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'feature/invalid-login-test',
                    url: 'https://github.com/Laxman-Qa/selenium-framework.git'
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Run Tests (Parallel)') {
            parallel {
                stage('Login Tests') {
                    steps {
                        sh 'mvn test -Dtest=LoginLogoutTest'
                    }
                }
                stage('Invalid Login Tests') {
                    steps {
                        sh 'mvn test -Dtest=InvalidLoginTest'
                    }
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/**', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline Passed'
        }
        failure {
            echo '❌ Pipeline Failed'
        }
    }
}