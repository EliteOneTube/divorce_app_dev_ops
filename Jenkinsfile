pipeline {
    agent any
    
    stages {
        stage('Git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/EliteOneTube/divorce_app.git'
            }
        }
        stage('Install postgres using ansible') {
            steps {
                sh '''
                    sudo ansible-playbook -i ~/workspace/divorce_app/hosts.yml ~/workspace/divorce_app/playbooks/postgres.yml
                '''
            }
        }
        stage('Install Divorce app') {
            steps {
                sh '''
                    ansible-playbook -i ~/workspace/divorce_app/hosts.yml ~/workspace/divorce_app/playbooks/spring-boot.yml
                '''
            }
        }
    }
}