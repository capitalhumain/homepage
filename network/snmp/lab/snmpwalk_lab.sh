#!/bin/sh

if [ $# -ne 4 ]; then
    echo "Usage: $0 snmp_version password host oid"
    exit 1
fi

#echo $1
#echo $2
#echo $3
#echo $4

snmpwalk -O n -v $1 -c $2 $3 $4

exit 0
