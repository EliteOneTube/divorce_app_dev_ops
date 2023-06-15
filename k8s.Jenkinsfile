pipeline {
    agent any
    
    stages {
        stage('Git clone') {
            steps {
                git branch: 'main', url: 'https://github.com/EliteOneTube/divorce_app.git'
            }
        }
        stage('Apply the k8s files') {
            steps {
                sh '''
                    ansible-playbook -i ~/workspace/divorce_app/hosts.yml ~/workspace/divorce_app/playbooks/run_k8s.yml
                '''
            }
        }
    }
}