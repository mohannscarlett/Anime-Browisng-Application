# Anime-Browisng-Application


**Project Description:** Java based desktop application used for browsing an array of different anime by different measures of quality.

**Technologies used to create this application;**

Java, ORACLE database

**Installation & Requirements**

To run this project, Java 8+ in addition to Oracle Database 11g+. Download the project from the repository or clone it using the following command:

_git clone https://github.com/mohannscarlett/Anime-Browisng-Application.git_

**How to Use?** Firstly, the needed database can be instanciated by the Oracle sql commands found in the repository createDatabase.sql

Next you must change the value of the connection object in LoginPage.java to reflect the value that your Oracle user must use to connect to the your.
![image](https://user-images.githubusercontent.com/123710621/215301206-94bb7f6b-eb64-4491-ba22-bb29c2cd74a5.png)

Then LoginPageOne instance of the StartApp.java (main)[and its related directory] should be run to instanciate a login page. 

Next the user may signup if they have not already and input a valid username as password.
![image](https://user-images.githubusercontent.com/123710621/215301426-12abf4b2-fb0c-4ea5-97cb-c8755f8e60d2.png)

If successful, user will be brought into main application where you can browse anime by different measures of quality. You can also change your user profile picture; user discription; or search for a user that has made an account.
 ![landingpage](https://user-images.githubusercontent.com/123710621/215301446-7688abce-f629-4713-b0c3-a5c6e78b9cab.png)

**Build Status:**

This version of the application is not yet ready to be packaged into a .jar file.
