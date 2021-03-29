#! /bin/sh
############### Build Instructions ##########################

echo Build Started at `date`
###### BUILD SIMPLE CLASSES
#echo "javac edu/ucalgary/ensf409/Furniture.java"
#javac edu/ucalgary/ensf409/Furniture.java
#echo "javac edu/ucalgary/ensf409/Desk.java"
#javac edu/ucalgary/ensf409/Desk.java
#echo "javac edu/ucalgary/ensf409/Filing.java"
#javac edu/ucalgary/ensf409/Filing.java
#echo "javac edu/ucalgary/ensf409/Chair.java"
#javac edu/ucalgary/ensf409/Chair.java
#echo "javac edu/ucalgary/ensf409/Lamp.java"
#javac edu/ucalgary/ensf409/Lamp.java
#echo "javac edu/ucalgary/ensf409/Manufacturer.java"
#javac edu/ucalgary/ensf409/Manufacturer.java
#echo "javac edu/ucalgary/ensf409/FurnitureOrder.java"
#javac edu/ucalgary/ensf409/FurnitureOrder.java
#echo "javac edu/ucalgary/ensf409/FurnitureOrderForm.java"
#javac edu/ucalgary/ensf409/FurnitureOrderForm.java
#echo "javac edu/ucalgary/ensf409/FurnitureOrderFormFile.java"
#javac edu/ucalgary/ensf409/FurnitureOrderFormFile.java

#############  Build the Database Interaction units
#echo "javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/CheckInventory.java"
#javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/CheckInventory.java
#echo "javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/DataBaseManager.java"
#javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/DataBaseManager.java

##############   BUILD SCM Control unit
echo "javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManager.java"
javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/SupplyChainManager.java


##############

echo Build Completed at `date`
############################## END OF FILE ###############################################