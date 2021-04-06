#Hackathon
Project files for working on the hackathon

This git repo will contain our preliminary UML and some preliminary classes to solve
the INVENTORY Furniture combination of minimum cost problem.

##BUILD INSTRUCTIONS

###Linux or MacOS
`$./build.sh`

### Windows NT
> build

## RUN PROGRAM

* first make sure to load the inventory SQL database

`$ mysql -u username -p
mysql> source inventory.sql;
mysql> quit; `

`java -cp .;lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.SupplyChainManager`

Or type the following command at the command console (CMD,SH,BASH):<p>

###Linux or MacOS
`$ scm`

### Windows NT
`> scm`


## RUN TEST FILE

### Linux of MacOS
`$ ./test`

### Windows NT

`> test`
