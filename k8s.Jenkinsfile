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
                    ansible-playbook -i ~/workspace/k8s/hosts.yml ~/workspace/k8s/playbooks/run_k8s.yml
                '''
            }
        }
    }
}