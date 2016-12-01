#!/bin/sh

# Need 
#    tdbloader2
# path required apache jena bin
# 

export FUSEKI_HOME="${FUSEKI_HOME:-$PWD}"
export FUSEKI_BASE="${FUSEKI_BASE:-$PWD/run}"

List="`ls $FUSEKI_BASE/job`"

#echo $List
for f in $List
do
    echo "File: " $f
    Dataset=${f//\.ttl/}
    echo "Dataset: " $Dataset

    # shutdown dataset via curl
    curl -v -L -G -d "dbName=$Dataset" http://localhost:3030/shutdownDatasetServlet

    # import 
    echo $FUSEKI_BASE/databases/$Dataset
    /Users/tzuyichao/local/apache-jena-3.0.1/bin/tdbloader2 -l "$FUSEKI_BASE/databases/$Dataset" $FUSEKI_BASE/job/$f 

    # remount dataset via curl
    curl -v -L -G -d "dbName=$Dataset" http://localhost:3030/remountDatasetServlet

    # remove file
    rm $FUSEKI_BASE/job/$f
done
