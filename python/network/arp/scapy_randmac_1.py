#!/usr/bin/python

from scapy.all import *

r = RandMAC("*:*:*:*:*:*")

print(r)
print(RandIP("*.*.*.*"))

for i in range(1, 10):
    print(RandIP("192.168.1.*"))

