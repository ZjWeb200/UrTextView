# UrTextView: An Android Full Stack App Using Google Cloud Vision API to Assist Text Reading Tasks
## Overview
One of the challenges that people who are blind or vision impaired face daily is to read tags with tiny font size. List of ingredients on a ketchup bottle or a tag showing how to wash a sweater, reading these texts could be very hard for blind people. 
UrTextView is an Android application aims to help these people read texts easier.

**Front end**: Android app with camera enabled or gallery accessible. User may take a picture of a piece of text or choose an already existed picture with text in it from gallery, the picture is then sent to the backend
automatically. After the backend send back the text content, display it on the screen for user to read easily.

**Back end**: Java servlet with RESTful API based on Apache Tomcat Server and lives on Google Cloud Platform using Google Cloud Vision OCR (optical character recognition) API. OCR interprets the text content of our picture. Then the content is sent back as reponse to the client.

**The logic diagram of the project**:  
![logic](https://github.com/ZjWeb200/UrTextView/blob/master/logic.png)

**Front end Android App at launch**: <br />
<img src="https://github.com/ZjWeb200/UrTextView/blob/master/frontend.jpg" width="300" height="500">

**Demo of recognizing a tiny wash tag**: <br />
<img src="https://github.com/ZjWeb200/UrTextView/blob/master/demo.jpg" width="300" height="500">
