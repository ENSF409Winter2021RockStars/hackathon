@ECHO OFF

## Run the Main Program
java -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/system-rules-1.19.0.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.SupplyChainManagerTest
