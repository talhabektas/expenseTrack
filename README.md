# Credit Card Expense Tracking System

This project implements a real-time credit card expense tracking system using modern big data technologies. The system continuously generates random credit card expenses for employees, processes them through a data pipeline, and provides a web interface for visualization.
#
## DataFlow
Data Generator: Generates random credit card expenses every second

Kafka: Message queue for expense data streaming

Spark: Real-time data processing and analysis

Cassandra: NoSQL database for storing processed data

MySQL: Stores employee and department information

AWS S3: Stores employee profile images

Spring Boot: Web application for data visualization
#
## Pre requirements/Technologies Used

JDK 17

Maven

Docker & Docker Compose

AWS Account with S3 access

MySQL Server

Git


## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
git clone <https://github.com/talhabektas/expenseTrack.git>
cd demo4
```

## Configure AWS Credentials

```python
aws:
  access:
    key:
      id: access_key_id
  secret:
    access:
      key: secret_access_key
  s3:
    region: region
    bucket: bucket_name
```

## Start Docker Containers
```
docker-compose up -d
```
#
## Create MySQL Database Schema
```
mysql -u root -p
CREATE DATABASE x;
USE x;
-- Import emp.csv and dept.csv data
```
#
## Cassandra Setup
```
CREATE KEYSPACE expense_tracking WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
USE expense_tracking;
CREATE TABLE expenses (
    user_id text,
    date_time timestamp,
    description text,
    type text,
    payment decimal,
    PRIMARY KEY ((user_id), date_time)
);
```
#
## Build and Run
```
mvn clean install -DskipTests
mvn spring-boot:run
```
#
# Application Structure
## Data Models
- Employee: Employee information

- Department: Department information

- Expense: Credit card expense records

- ExpenseKey: Composite key for expense records

## Services

- DataGeneratorService: Generates random expense data

- ExpenseProducer: Publishes expenses to Kafka

- ExpenseConsumer: Consumes expenses from Kafka

- SparkAnalysisService: Processes data using Spark

- S3Service: Handles AWS S3 operations

#
## Web Interface
Access the web interface at: http://localhost:8080

- View employee details

- Monitor real-time expenses

- View expense reports by employee

- Manage employee profile images

#
## API Endpoints

- GET /api/employees: List all employees

- GET /api/employees/{id}: Get employee details

- GET /api/expenses/latest: Get latest expenses

- POST /api/employees/{id}/image: Update employee image

## TroubleShooting
If Kafka fails to start, check:

- Port availability (9092)
- Zookeeper connection


If Spark fails:

- Check memory allocation

- Verify Cassandra connection


AWS S3 issues:

- Verify credentials

- Check bucket permissions

## To Communicate:
- github:talhabektas
- linkedn: https://www.linkedin.com/in/mehmet-talha-bekta%C5%9F-4a56aa271/
- instagram:talha.bekts

## License
[MIT](https://choosealicense.com/licenses/mit/)
