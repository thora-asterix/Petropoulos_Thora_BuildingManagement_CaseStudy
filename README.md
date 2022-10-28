# Petropoulos_Thora_BuildingManagement_CaseStudy

Petropoulos_Thora_BuildingManagement_CaseStudy

What is the Building Management System?

This application aims to help a building superintendent to keep track of everyday tasks and issues 
of apartment rentals It maintains a list of various service companies that serve the building and the
individual apartments The superintendent of the building can create tasks related to the building maintenance 
and assign service companies to complete the tasks The superintendent can also create issues that are related to the 
individual apartments The apartments can be occupied by tenants or be vacant Vacant apartments have a public API that 
serves JSON that can be consumed by the other applications

Technologies Used:

For the back-end of the application the following technologies were used:

MariaDB as the Database
Spring Boot, with Spring JPA Data, and Spring Web as the back-end framework, along with Spring Security
For the front-end of the application the following technologies were used:

Thymeleaf templates were used, along with Bootstrap, CSS3, HTML5 and JavaScript

How to run the application: Run the application as a spring boot application once. Once tables have been created in MariaDB, insert the following user in the users table:

INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin',
'$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu',
'ROLE_USER', 1);
 
 To login to the application the credentials will be: 
 username: admin
 password: admin


A small React app was also created as front-end technology that consumes the public API of vacant apartments. The name of the repository is apartments-api.
(This is a small side project to practice using other front-end technologies besides Thymeleaf templates. 
It was completed with React, Bootstrap and the AXIOS library
