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
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=sonar-maven-assessment ^
                        -Dsonar.sources=src/test/java ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=%SONAR_TOKEN%
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
