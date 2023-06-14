# Setting up 
* Git clone the repo to your local machine with `git clone https://github.com/EliteOneTube/divorce_app.git`
* NOTE: All executions that run on localhost can be run on a different machine by changing the playbooks

# Docker
* Build and run the container with `docker-compose up --build -d`

# Ansible + Docker
* Install ansible `sudo apt install ansible`
* Run the playbook `ansible-playbook playbooks/docker_run.yml --ask-become-pass`

# Ansible + Vagrant
* Install ansible `sudo apt install ansible`
* Install vagrant `sudo apt install vagrant`
* Install virtualbox `sudo apt install virtualbox`
* Install vagrant plugin `vagrant plugin install vagrant-hostmanager`
* Change directory `cd vagrant`
* Run vagrant `vagrant up`
* Run `vagrant ssh-config >> ~/.ssh/config`
* Run the postgres playbook `ansible-playbook ../playbooks/postgres.yml -i ../hosts.yml`
* Run the divorce app playbook `ansible-playbook ../playbooks/spring-boot.yml -i ../hosts.yml`
* Visit `192.168.56.111:8080` or `192.168.56.112:8080` to see the app running(Takes a few seconds to get up and running)

# Ansible + Vagrant + Jenkins
* Install ansible `sudo apt install ansible`
* Install vagrant `sudo apt install vagrant`
* Install virtualbox `sudo apt install virtualbox`
* Install vagrant plugin `vagrant plugin install vagrant-hostmanager`
* Change directory `cd vagrant`
* Run vagrant `vagrant up`
* Run `vagrant ssh-config >> ~/.ssh/config`
* Run the installation playbook `ansible-playbook ../playbooks/install_jenkins.yml -i ../hosts.yml --ask-become-pass`
* Visit `localhost:8080` to see the jenkins server and follow the instructions to set it up
* Create a new pipeline job and set the pipeline script to `Jenkinsfile` of this repo
* You have to create new ssh key for the jenkins user and add it to the autorized keys of the vagrant vms that run the app
* Build the job and visit `192.168.56.111:8080` to see the app running(Takes a few seconds to get up and running)

# Kubernets
* Install microk8s by following the instructions [here](https://ubuntu.com/tutorials/install-a-local-kubernetes-with-microk8s#1-overview)
* Configure the templates files in /k8s/divorce directory
* Apply the yml files with `kubectl apply -f <name>`
* Forward the port of the pod with `kubectl port-forward pods/<pod-name> 8000:8080`
* Visit `localhost:8000` to see the app running(Takes a few seconds to get up and running)