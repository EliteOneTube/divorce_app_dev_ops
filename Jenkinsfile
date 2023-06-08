pipeline {
    agent any
    environment {
        mavenHome = tool 'jenkins-maven'
        DB_SERVER = 'db:5432'
    }

    tools {
        jdk 'java-17'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'nohup mvn exec:java -Dexec.mainClass=gr.dit.hua.divorce.DivorceApplication &'
            }
        }
    }
}