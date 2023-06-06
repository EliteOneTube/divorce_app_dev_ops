# Setting up 
* Git clone the repo to your local machine with `git clone https://github.com/EliteOneTube/divorce_app.git`
* Create a .env file following the template.env file example

# Execution Using maven
* Initialize the database with `docker run --name spb_db --rm -e POSTGRES_DB=divorces -e POSTGRES_PASSWORD=pass123  -p 5432:5432 -v pgdata14:/var/lib/postgresql/data  -d postgres:14`
* Install  `mvn clean install`
* Package `mvn package`
* Run `mvn exec:java -Dexec.mainClass=gr.dit.hua.divorce.DivorceApplication`

#  Execution Using Docker
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