# Setting up 
  * Git clone the repo to your local machine with `git clone https://github.com/EliteOneTube/divorce_app.git`
\


  * Initialize the database with `docker run --name spb_db --rm -e POSTGRES_DB=divorces -e POSTGRES_PASSWORD=pass123  -p 5432:5432 -v pgdata14:/var/lib/postgresql/data  -d postgres:14`

# Execution from terminal
  * Compile the code with `mvn clean install`
  * Package the code with `mvn package`
  * Run the code with `mvn exec:java -Dexec.mainClass=gr.dit.hua.divorce.DivorceApplication`