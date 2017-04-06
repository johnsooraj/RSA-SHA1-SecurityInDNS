Aim of this project is, provide Security For the url while that request pass into a DNS Server.
RSA and SHA1 are the algorithms used for that security.

Step 1: Run the Server Project 
START DNSSERVER.JAVA
By running that, an Server Socket will start and it will wait for an client request.
before the Server Socket start The Database connection will establish, The server project will 
decrypt the incomeing request from any of its client (in this project only one client).

Step 2 :
START LOGINPAGE.JAVA
By running that, Database connection will establish, then a Swing Page will appear, ask for username and password.
Then the Server Socket will start and Default Web Browser will start with a pre defined url(your localhost address+portnumber Eg: 192.168.1.13:5000).
An Index Web Page will appear, Pre defined 3 URL will show, by entering that URL with Existing url ie, "192.168.1.13:5000/www.antcolony.com",
will show you a home page of that website.

Working : while entering that URL (192.168.1.13:5000/www.antcolony.com) an socket will hit on SERVER SOCKET and the Encryption will start.
after the encryption another socket will create and will try to hit our SERVER(Step 1, project'server'). 
An decryption will done at server and return a file location from that server socket.

Total 3 tables

1: User Details

CREATE TABLE `userdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

2: File Location with Ip address

CREATE TABLE `dnsindexfilelocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filelocation` varchar(45) DEFAULT NULL,
  `ipaddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

3: URL with Ip Address

CREATE TABLE `dnsindex` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ipaddress` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8
