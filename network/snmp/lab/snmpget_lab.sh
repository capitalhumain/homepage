#!/bin/sh

if [ $# -ne 4 ]; then
    echo "Usage: $0 snmp_version password host oid"
    exit 1
fi

# snmpwalk -v 2c -c public 10.120.137.52 1.3.6.1.4.1.77.1.2.3.1.1
#echo $1
#echo $2
#echo $3
#echo $4

status=`snmpget -O v -v $1 -c $2 $3 $4 | sed 's/INTEGER: //g'`
if [ $status -eq "1" ] ; then
    rrdtool update /root/lab/test.rrd N:1
else
    rrdtool update /root/lab/test.rrd N:0
fi

exit 0
