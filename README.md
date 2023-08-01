This is a management system for a blood test lab
The main components are:
1)DBconnection.java: this must be compiled first, using a my-SQL-connector Jar file in the classpath. 
2)Patient.java: this must be compiled before compiling patientFrontend.java 
3) PatientFrontend.java: this file contains a command line version of the management system
4) AdminFrontend. java: this must be compiled after DBconnection.java. It provides administrative tools to an   operator of the test lab
The components that are responsible for the servlet functionality are:
1) all HTML pages: they provide the user interface for the dynamic website
2) styles.css: provides style format for the website 
3) web.xml: maps the servlet class to a URL to be used by the HTML forms
4) HelloForm.java: this is a servlet class that must be compiled after compiling DBconnection and Patient.java. It provides a similar functionality to PatientFrontend, in a website format. It must be compiled with the servlet jar file in its class path. 

There is a planned phase 2 for this project which shall be in the works soon. 
So far the challenges have been hosting the website onto the tomcat-9 server so suggestions regarding that are welcome.

