pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token')
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run Automation Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Run Tests and Coverage') {
        steps {
            bat 'mvn clean verify'
        }
    }

      stage('SonarQube Analysis') {
    steps {
        withSonarQubeEnv('sonarqube') {
            bat """
                mvn sonar:sonar ^
                -Dsonar.projectKey=sonar-maven-assessment ^
                -Dsonar.java.binaries=target/classes ^
                -Dsonar.java.test.binaries=target/test-classes ^
                -Dsonar.host.url=http://localhost:9000 ^
                -Dsonar.login=%SONAR_TOKEN% ^
                -Dsonar.sources=src/main/java ^
                -Dsonar.tests=src/test/java ^
                -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
            """
        }
    }
}

    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
