export MAC_ADDRESS=`/usr/lib/busybox/sbin/ifconfig | grep "^eth" | cut -c 39-55`