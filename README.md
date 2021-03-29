#Hackathon
Project files for working on the hackathon

This will git repo will contain our preliminary UML and some preliminary classes

##BUILD INSTRUCTIONS

###Linux or MacOS
`$./build.sh `

### Windows NT
> build

## RUN PROGRAM

* first make sure to load the inventory SQL database

`$ mysql -u username -p
mysql> source inventory.sql;
mysql> quit; `

`java -cp .;lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.SupplyChainManager`

or type at the console

##Linux or MacOS
`$ scm`

## Windows NT
`> scm`



