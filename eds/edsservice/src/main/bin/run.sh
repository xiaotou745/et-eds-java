#!/bin/bash

DIR=`pwd`


ulimit -n 65535

export PATH=$DIR:$PATH

java -Xmx4096m -jar ../lib/edsservice.jar $1 > /dev/null &

