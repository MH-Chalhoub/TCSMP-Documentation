<p align="center">
  <img src="/src/inOutTcsmp.png" alt="alt text" width="250" height="250">
</p>

# TCSMP
TCSMP is a client-server protocol designed to efficiently transfer mail and allow time consuming transfers.
Although this might sound shocking, it is worth considering the time usage as an expense, i.e. sending a mail actually costs time. The aim is to stop spamming: sending mail to one recipient could take less than 1 sec, while sending mail to 50 could take 30 seconds!
TCSMP now depends on problem solving in order to do so. More specifically, it uses puzzles which grow exponentially in complexity. The server gives the client a puzzle which has to solve it so that the server accepts its mail.

# Objectifs :
The goal is to think about the operation principles of such a mechanism and about the necessary protocols for its
implementation. Defining a RFC for this TCSMP Protocol
     
# The code :
* The code is in this repo https://github.com/MH-Chalhoub/TCSMPProject .
* You can test the code in any java IDE.

# ScreenShots :
<p align="center">
  <img src="/screenshot/1.JPG" alt="alt text" width="450" height="350">
  <img src="/screenshot/2.JPG" alt="alt text" width="450" height="320">
  <img src="/screenshot/3.JPG" alt="alt text">
  <img src="/screenshot/4.JPG" alt="alt text">
  <img src="/screenshot/5.JPG" alt="alt text">
</p>

# How to install and use the application :
1.  Run to instance of the Server (Server.jar) one for BINIOU and the other for POUET.
2.  Run any number of clients (Client.jar) and the domain name of each client have to be (BINIOU/POUET ex: X@BINIOU.com or Y@POUET.com) and try to send a mail from one client to the other.

###### The Idea of Dr. Yasser Fadlallah
