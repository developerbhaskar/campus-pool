# Campus Pool

Campus Pool is a Java/JSP web application for sharing campus rides.

## Technology
- Java 17
- JSP
- Jakarta Servlets 6
- JDBC
- MySQL 8+
- Maven
- Apache Tomcat 10.1+
- BCrypt password hashing

## Features
1. Student registration and login
2. Secure session-based authentication
3. Post a ride
4. Search available rides
5. Book a ride
6. View posted and booked rides
7. Cancel a booking
8. MySQL persistence through JDBC

## 1. Install prerequisites

Install:
- JDK 17
- Maven
- MySQL Server
- Apache Tomcat 10.1+

Verify:

```bash
java -version
mvn -version
mysql --version
```

## 2. Create the database

Open MySQL Workbench or MySQL command line and run:

```sql
SOURCE database/campus_pool.sql;
```

The application expects:
- database: campus_pool
- username: root
- password: root

If your MySQL password is different, edit:

`src/main/java/com/campuspool/util/DBConnection.java`

## 3. Build

From the project folder:

```bash
mvn clean package
```

The WAR file will be:

`target/campus-pool.war`

## 4. Run with Tomcat

Copy:

`target/campus-pool.war`

into Tomcat's:

`webapps/`

Start Tomcat.

Windows:

```text
apache-tomcat-10.1.xx\bin\startup.bat
```

Open:

http://localhost:8080/campus-pool/

## Demo flow

1. Register two students.
2. Login as student A.
3. Post a ride.
4. Logout.
5. Login as student B.
6. Search the ride.
7. Book it.
8. Open Dashboard to see bookings.

## Project structure

```text
campus-pool/
├── database/
│   └── campus_pool.sql
├── src/main/java/com/campuspool/
│   ├── dao/
│   ├── model/
│   ├── servlet/
│   └── util/
├── src/main/webapp/
│   ├── css/
│   ├── WEB-INF/
│   ├── dashboard.jsp
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── post-ride.jsp
│   └── search-rides.jsp
└── pom.xml
```
