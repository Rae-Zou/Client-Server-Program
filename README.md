# Client-Server program in Java

Host receives IP datagrams, which contains source IP and destination IP address of the datagram and a transport-layer segment, which has the source port number and destination port number.

Host uses these IP address & port numbers to direct segment to the corresponding socket. The segment’s data then passes through the socket into the attached process.

## Server: 
With a `TCP` server socket at port `5000`
- It loops forever until the server receives a connection request from a client.
- Use threads to handle TCP server sockets

## Client: 
- It contacts the server with the associated public IPv4 address and port number.
- Output the response which is sent by the server with it's associated private IPv4 address.

![image](https://user-images.githubusercontent.com/76859781/135778224-04513c52-58c8-4aab-a3d9-7e0d9f8a5e60.png)
