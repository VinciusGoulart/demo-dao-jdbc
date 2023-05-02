# Demo DAO JDBC
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/VinciusGoulart/demo-dao-jdbc/blob/main/LICENSE)

This is a project developed during a JDBC course. The aim is to provide a basic project structure for those learning JDBC.

## Prerequisites
Before running the project, you must have the following installed on your machine:

- Java 11 or higher
- Maven 3 or higher
- MySQL database

## Database Configuration
The db.properties file contains the database configuration. You need to configure the db.url, db.user, and db.password properties according to your MySQL database configuration.

## Running the Project
To run the project, run the following command in the project root directory:

```bash
# clone repository
git clone https://github.com/VinciusGoulart/demo-dao-jdbc.git

# Run project
mvn exec:java -Dexec.mainClass="application.Program"
```

# Usage
The project is a console-based application that demonstrates the usage of JDBC to interact with a MySQL database. The application allows you to create, read, update, and delete records from the "Department" and "Seller" tables of the database.

# Contribution
This project was developed only as an example for learning purposes. Contributions are welcome, but are not guaranteed to be accepted. To contribute, open an issue or a pull request.
