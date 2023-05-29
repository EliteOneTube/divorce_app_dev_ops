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