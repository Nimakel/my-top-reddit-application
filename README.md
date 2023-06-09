﻿# Top Reddit Publication Application
This tech task project for VRGSoft is an imitation of a Top Reddit Publication Application, that shows top Reddit publications. The project works on Android SDK and Java.
# Features
* Support changes of orientation
* Shows based publication info, such as: `author`, `publish time`, `image`, `count of comments`
* Opening image in fullscreen by clicking on them
* Saving image by clicking on Button `Save`
* Pagination
* Saving activity
# How to run project
* Clone this repository
* Setup Android Studio
* `File` -> `New` -> `Project from Version Control` -> Past cloned URL from this repository
* Setup emulator
* Start project and wait until it will run
# Structure
* `adapter`: adapter for supporting pagination by using RecycleView
* `data`: data download classes, that works with Reddit API
* `model`: class, that represent structure of data publication in Reddit
* `service`: classes, that make some business logic, such as parse data from JSON object, or load image from URL
* `activities`: represent activities of layout
# Presentation of project
[YouTube video](https://youtu.be/N4tpnu4Opf8)
# Used Technologies
* Java
* Gradle
* Android SDK
* Android Studio
# Authors
[Maksym Yashyn](https://www.linkedin.com/in/maksym-yashyn-dnipro/)
