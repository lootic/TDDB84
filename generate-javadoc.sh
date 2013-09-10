#!/bin/sh

for i in 1 2 3 4 5
do
    javadoc -private -d Lab$i/doc Lab$i/lab${i}Source/*.java
done
