#!/bin/bash

bash mvnw compile
bash mvnw exec:java -Dexec.mainClass="com.example.flower_app.Main"