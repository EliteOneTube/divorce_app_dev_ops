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

# Kubernetes + ansible + jenkins
* Install microk8s & kubectl by following the instructions [here](#set-up-microk8s-on-a-remote-machine--kubectl)
* Install ansible `sudo apt install ansible`
* Run the installation playbook for jenkins `ansible-playbook ../playbooks/install_jenkins.yml -i ../hosts.yml --ask-become-pass`
* Visit `localhost:8080` to see the jenkins server and follow the instructions to set it up
* Create a new pipeline job and set the pipeline script to `k8s.Jenkinsfile` of this repo
* Jenkins user should be authorized with the kubernetes server
* Configure the templates files in /k8s/divorce directory
* Run the playbook `ansible-playbook ../playbooks/run_k8s.yml`
* Get pods with `kubectl get pods`
* Forward the port of the pod with `kubectl port-forward pods/<pod-name> 8000:8080`
* Visit `localhost:8000` to see the app running(Takes a few seconds to get up and running)


# Set up microk8s on a remote machine & kubectl
* Install microk8s `sudo snap install microk8s --classic`
* Run `ip a` to get the network interface name
* Run `sudo ufw allow in on eth0 && sudo ufw allow out on eth0` & `sudo ufw default allow routed` (Replace eth0 with the network interface name)
* Access microk8s without sudo 

    `sudo usermod -a -G microk8s $USER`
    
    `mkdir ~/.kube`

    `sudo chown -f -R $USER ~/.kube`

    `sudo su - $USER`

* Enable microk8s addons `microk8s.enable dns storage ingress`
* Install kubectl [here](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)
* On the remote machine run `microk8s.kubectl config view --raw > $HOME/.kube/config`
* Back on the local machine run `scp <remote-user>@<remote-ip>:~/.kube/config ~/.kube/config`
* Replace `certificate-authority-data` line from the config file with `insecure-skip-tls-verify: true`
* Replace the server ip from localhost to the remote machine ip
* Run `kubectl create namespace cert-manager`
* Install helm [here](https://helm.sh/docs/intro/install/)
* Run `helm repo add jetstack https://charts.jetstack.io`
* Run `helm repo update`
* Run `kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.8.0/cert-manager.crds.yaml`
* Run `helm install cert-manager jetstack/cert-manager --namespace cert-manager --create-namespace --version v1.8.0`