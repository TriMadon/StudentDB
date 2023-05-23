# StudentDB
Attempts at making a student system, in both console and front-end formats.

StudentDB-v1 is console-based, built with multithreading manually and JDBC.

StudentDB-v2 has an HTML front-end, built with Servlets and jsp.

StudentDB-v3 also has a front-end, built with Spring and Thymeleaf.

You can test by first deploying the student database with the initial schema via docker:

```bash
docker compose-up
```

Then testing the chosen implementation in your chosed IDE (preferably IntelliJ Idea). You can test v2 and v3 with Tomcat, While v1 can be tested by first running the server class then running as many instances of client class as you like.
