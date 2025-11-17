pipeline {
    agent any

    environment {
        SDKMAN_DIR = "${HOME}/.sdkman"
        GRAALVM_VERSION = "25-graal"
        MAVEN_VERSION = "4.0.0-rc-5"
        PATH = "${SDKMAN_DIR}/candidates/java/${GRAALVM_VERSION}/bin:" +
               "${SDKMAN_DIR}/candidates/maven/${MAVEN_VERSION}/bin:" +
               "${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Environment') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'native-image --version || echo "Native image not installed"'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -U clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
