# UrTextView: An Android Full Stack App Using Google Cloud Vision API to Assist Text Reading Tasks
## Overview
One of the challenges that people who are blind or visually impaired face daily is to read tags with tiny font size. List of ingredients on a ketchup bottle or a tag showing how to wash a sweater, reading these texts is very hard for people who are blind. 
UrTextView is an Android application aims to help these people read/hear texts easier.

**Frontend**: Android app with camera enabled or gallery accessible. User may take a picture of a piece of text or choose an already existing picture with text in it from gallery. The picture is then sent to the backend
automatically. After the backend sends back the text content, push button to show it in an AlertDialog. Finally, user is able to hear the content. To do so, the app takes advantage of the Android Accessibility Suite's TalkBack by Google. 

**Backend**: Java servlet with RESTful API based on Apache Tomcat Server and lives on Google Cloud Platform using Google Cloud Vision OCR (optical character recognition) API. OCR interprets the text content of our image. Then the content is sent back as HTTP reponse to the frontend.

**A little details about the backend**: the backend is deployed on Google Cloud Platform using GKE (Google Kubernetes Engine). The master of GKE controls 3 virtual-machine nodes. Each of the VM node contains a published Docker image built on our service. (The Docker image is published on DockerHub. In this way, we can deploy our servie on the VM nodes easily.) <br /> The reason I chose GKE instead of simple GCE (Google Compute Engine): during development, I found that with a single GCE VM, the app runs very slow sometimes. This latency problem is soleved by switching to GKE.

**The logic diagram of the project**:  
![logic](https://github.com/ZjWeb200/UrTextView/blob/master/logic.png)

**Frontend Android App at launch**: <br />
<img src="https://github.com/ZjWeb200/UrTextView/blob/master/launch.jpg" width="300" height="500">

**Demo of recognizing a tiny wash tag**: <br />
<img src="https://github.com/ZjWeb200/UrTextView/blob/master/upload_image.jpg" width="300" height="500">
<img src="https://github.com/ZjWeb200/UrTextView/blob/master/recognize.jpg" width="300" height="500">
