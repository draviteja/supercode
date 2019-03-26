#!/bin/bash
java -jar target/*.jar & echo $! > ./pid.file &