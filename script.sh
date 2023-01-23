#!/bin/bash

STEP=$1

echo -e "Start $STEP microservices\n"

echo -e "Compile api source\n\n"
cd ./api
chmod 777 mvnw
./mvnw $STEP
echo -e "\n"

echo -e "Compile main service source\n\n"
cd ../microservices/main-service
chmod 777 mvnw
./mvnw $STEP
echo -e "\n"

echo -e "Compile todo service source\n\n"
cd ../todo-service
chmod 777 mvnw
./mvnw $STEP
echo -e "\n"

echo -e "Compile user service source\n\n"
cd ../user-service
chmod 777 mvnw
./mvnw $STEP