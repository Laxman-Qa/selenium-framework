pipeline {
    agent any

    tools {
        maven 'Maven'        // Must match Jenkins Global Tool name
        jdk 'JAVA_HOME'      // Must match Jenkins Global Tool name
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
                bat 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Run Tests (Parallel)') {
            parallel {

                stage('Login Tests') {
                    steps {
                        bat 'mvn test -Dtest=LoginLogoutTest'
                    }
                }

                stage('Invalid Login Tests') {
                    steps {
                        bat 'mvn test -Dtest=InvalidLoginTest'
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
            echo '✅ Pipeline Passed - All tests successful'
        }
        failure {
            echo '❌ Pipeline Failed - Check console output'
        }
        always {
            echo '📊 Execution Completed'
        }
    }
}