@ECHO OFF


REM ############### Build Instructions ##########################

ECHO Build Started at:  
DATE /T
TIME /T 

REM ###### BUILD SIMPLE CLASSES
ECHO javac edu/ucalgary/ensf409/Furniture.java
javac edu/ucalgary/ensf409/Furniture.java
ECHO javac edu/ucalgary/ensf409/Desk.java
javac edu/ucalgary/ensf409/Desk.java
ECHO javac edu/ucalgary/ensf409/Filing.java
javac edu/ucalgary/ensf409/Filing.java
ECHO javac edu/ucalgary/ensf409/Chair.java
javac edu/ucalgary/ensf409/Chair.java
ECHO javac edu/ucalgary/ensf409/Lamp.java
javac edu/ucalgary/ensf409/Lamp.java
ECHO javac edu/ucalgary/ensf409/Manufacturer.java
javac edu/ucalgary/ensf409/Manufacturer.java
ECHO javac edu/ucalgary/ensf409/FurnitureOrder.java
javac edu/ucalgary/ensf409/FurnitureOrder.java
ECHO javac edu/ucalgary/ensf409/FurnitureOrderForm.java
javac edu/ucalgary/ensf409/FurnitureOrderForm.java
ECHO javac edu/ucalgary/ensf409/FurnitureOrderFormFile.java
javac edu/ucalgary/ensf409/FurnitureOrderFormFile.java
ECHO javac edu/ucalgary/ensf409/FurnitureSelector.java
javac edu/ucalgary/ensf409/FurnitureSelector.java


REM #############  Build the Database Interaction units
ECHO javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/DataBaseManager.java
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/DataBaseManager.java

REM ##############   BUILD SCM Control unit
ECHO javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManager.java
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManager.java


REM ##############

ECHO Build Completed at: 
DATE /T
TIME /T
REM ############################## END OF FILE ###############################################
