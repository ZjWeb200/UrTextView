# UrTextView: An Android App Using Google Cloud OCR (optical character recognition) to Assist Text Reading Tasks
## Overview
One of the challenges that people who are blind or vision impaired face daily is to read small font tags. A tag on a Ketchup bottle or a tag showing the size of a sweater, reading them could be very hard for blind people. 
UrTextView is an Android full stack application aims to help these people read texts easier.

**Front end**: Android app with camera enabled or gallery accessible. User may take a picture of a piece of text or choose an already existed picture with text in it from gallery, the picture is then sent to the backend
automatically. After the backend send back the text content, display it on the screen for user.

**Back end**: Java servlet with RESTful API based on Apache Tomcat Server and lives on Google Cloud Platform using Google Cloud Vision OCR API. OCR interprets the text content of our picture and send the content back as reponse to the client.


**The Logic diagram of the project**:  
