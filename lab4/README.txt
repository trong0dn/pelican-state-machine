Carleton University 
Department of Systems and Computer Engineering 
SYSC 3303A Real-Time Concurrent Systems Winter 2023 
Assignment 4 - State Machines 

@author Trong Nguyen
@version 1.0
@date 18/03/23
---------------------------------------------------------------------------------

# State model of a PELICAN (PEdestrian LIght CONtrolled) crossing

## Problem Description

The goal is to build a very basic state machine used to control a pedestrian 
crossing.

## Project Structure

lab2
|   .classpath
|   .gitignore
|   .project
|   A2-UML-class.drawio.pdf
|   A2-UML-class.drawio.png
|   A2-UML-sequence.drawio.pdf
|   A2-UML-sequence.drawio.png
|   A2.drawio
|   README.txt
|
+---.settings
|       org.eclipse.core.resources.prefs
|
+---bin
|       Client.class
|       Intermediate.class
|       Server.class
|
\---src
        Client.java
        Intermediate.java
        Server.java
            
## Requirements and Dependencies

This application was created on Windows 10 OS using Eclipses IDE.
Run on the latest version of jdk-17 using Java 17.

No other external dependencies required.

## Compiling and Running the Application

Download and extract the .zip file. Then import the source code directly and 
run the program in local IDE, otherwise the program can be compiled and 
executed via Command Prompt. Note that each program requires its own 
terminal. In other words, it must be able to run multiple main programs 
(projects) concurrently.

```console
> cd C:\..\..\\lab2\src\			// Navigate to the src directory	
> javac *.java					// Compile the source code
> java -cp . Server				// Set classpath to run application
> java -cp . Intermediate		// Set classpath to run application
> java -cp . Client				// Set classpath to run application
```

## Background Preamble

### Traversing the Network-Interface layer with Internet Protocol (IP)

The IP layer receives packets of data from upper layers of the protocol stack,
which is then encapsulated by a *packet* in an IP datagram, which is then 
passed through the Network-Interface layer to be transmitted over a network.
The IP datagram header contains many metadata about the IP addresses of the
source and destination host. These Network-Interface layers maps the IP
addresses to a physical network address. Once the IP layer receives the IP
datagram from the Network-Interface layer, the packets may be extracts and 
passed to other layers in the network.

### Disadvantages of IP transmission & Port Protocol

IP is known as *connectionless" meaning that each packet is treated
independently of others. Hence, a sequence of packets may travel over 
different paths between a source and destination. As it can be observed in 
this small project the ports from source and destination can differ locally,
yet, the sending and receiving packets are received correctly. Moreover, IP 
is unreliable as the delivery of packets is not guaranteed. As some packets
may be lost, duplicate, or even delivered out of order unbeknownst to the 
sender and receiver. 

With the disadvantages of IP transmission, IP addresses are able to specify
host, not processes running on those host. Hence, each computer requires to 
maintain a set of protocol ports.

### User Datagram Protocol (UDP)

UDP provides unreliable connectionless delivery services using IP to
transport messages among networks, with the added ability to distinguish
multiple destinations within a local host computer, via ports. UDP is a 
specific example of a Transport Layer using Transport Protocol Packets 
called UDP Datagrams.

## Technical Specifications

### Client

The Client is designed to send DatagramPackets using DatagramSockets 
specified to a well-known port number: 23. The message in the packet
itself is encode as byte array. The client creates a DatagramSocket to use
both send and receive repeat the following 11 times. In addition, prints out
receiving and sending packet details. The Client also receives packets from
the Server as well.

### Intermediate

The Intermediate Host is a relay node within the network. The Intermediate 
receives packets from the Client on port 23. And creates another 
DatagramPacket with the same information to be sent to the Server on port 
69. It also prints out receiving and sending packet details. Moreover, 
the Intermediate also receive packets from the Server which it also creates 
another DatagramPacket to send back to the Client.

### Server

The Server receives packets from the Intermediate on port 69, then validates 
the encoded message. Once validated, the Intermediate creates another 
DatagramPacket with a different message to be sent back to the Intermediate
on the same port. It also prints out receiving and sending packet details.

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for fair use for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.