#!/bin/bash

DIR=`pwd`


ulimit -n 65536

export PATH=$DIR:$PATH

java -Xmx4096m -jar ../lib/renrenservice.jar $1 > /dev/null &

