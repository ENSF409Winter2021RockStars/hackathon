@ECHO OFF
REM #########################################################################################
REM # Author: Zorondras Rodriguez
REM # Team: ENSF409 Group 48
REM # Team Members: Zorondras Rodriguez, Jade Meggitt, Matthew Pelletier, Quinn Ledingham
REM #
REM # Description: Build Script to build all Java Class Files for Linux or MacOS
REM #########################################################################################

REM ############### Build Instructions ##########################

ECHO Build Started at:  
DATE /T
TIME /T 

REM ###### BUILD SIMPLE CLASSES
ECHO javac edu/ucalgary/ensf409/Furniture.java
REM javac edu/ucalgary/ensf409/Furniture.java
ECHO javac edu/ucalgary/ensf409/Desk.java
REM javac edu/ucalgary/ensf409/Desk.java
ECHO javac edu/ucalgary/ensf409/Filing.java
REM javac edu/ucalgary/ensf409/Filing.java
ECHO javac edu/ucalgary/ensf409/Chair.java
REM javac edu/ucalgary/ensf409/Chair.java
ECHO javac edu/ucalgary/ensf409/Lamp.java
REM javac edu/ucalgary/ensf409/Lamp.java
ECHO javac edu/ucalgary/ensf409/Manufacturer.java
REM javac edu/ucalgary/ensf409/Manufacturer.java
ECHO javac edu/ucalgary/ensf409/FurnitureOrder.java
REM javac edu/ucalgary/ensf409/FurnitureOrder.java
ECHO javac edu/ucalgary/ensf409/FurnitureOrderForm.java
REM javac edu/ucalgary/ensf409/FurnitureOrderForm.java
ECHO javac edu/ucalgary/ensf409/FurnitureOrderFormFile.java
REM javac edu/ucalgary/ensf409/FurnitureOrderFormFile.java
ECHO javac edu/ucalgary/ensf409/FurnitureSelector.java
REM javac edu/ucalgary/ensf409/FurnitureSelector.java

REM #############  Build the Database Interaction units
ECHO javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/DataBaseManager.java
REM javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/DataBaseManager.java

REM ##############   BUILD SCM Control unit
ECHO javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManager.java
REM javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManager.java

REM ############# BUILD The Test Suild 
ECHO javac -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.2.jar:lib/system-rules-1.19.0.jar edu/ucalgary/ensf409/SupplyChainManagerTest.java
REM javac -cp .;lib/mysql-connector-java-8.0.23.jar;lib/junit-4.13.2.jar;lib/hamcrest-core-1.2.jar:lib/system-rules-1.19.0.jar edu/ucalgary/ensf409/SupplyChainManagerTest.java

REM ##############

ECHO Build Completed at: 
DATE /T
TIME /T
REM ############################## END OF FILE ###############################################
