# Property Management System

A web-based **Property Management System** built using **Jakarta EE**, **JSF**, **EJB**, and **JPA**, enabling property management, property manager allocations, and support for both sale and rent properties. This project demonstrates a complete CRUD application with database integration using **MySQL** and deployment on **GlassFish Server**.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Project Setup](#project-setup)
- [Known Issues](#known-issues)
- [Future Enhancements](#future-enhancements)

## Overview
The Property Management System allows users to:
1. Manage property managers.
2. Manage sale and rent properties.
3. Allocate properties to property managers.
4. View allocations and manage them.

## Features
- **Property Manager Module**: Create, view, update, and delete property managers.
- **Property Allocation**: Allocate both sale and rent properties to property managers.
- **Property Management**: Manage sale and rent properties separately.
- **CRUD Operations**: Full support for create, read, update, and delete operations.
- **Database Integration**: MySQL database for storing property and manager information.

## Technologies
- **Jakarta EE**: Backend framework.
- **JSF (JavaServer Faces)**: UI and frontend framework.
- **EJB (Enterprise Jakarta Beans)**: For business logic.
- **JPA (Jakarta Persistence API)**: For persistence and ORM.
- **MySQL**: Database for storing property and manager information.
- **GlassFish Server**: Application server for deployment.
- **Maven**: For dependency management.

## Project Setup

### Prerequisites
- **JDK 11+**
- **Maven**
- **GlassFish Server 5.x**
- **MySQL Server**

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-github-username/property-management-system.git



### Database Setup:

1. **Create a MySQL database** for the project.
2. **Update the `persistence.xml`** file with your database credentials:
   
   ```xml
   <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/property_db"/>
   <property name="jakarta.persistence.jdbc.user" value="your-username"/>
   <property name="jakarta.persistence.jdbc.password" value="your-password"/>
**Build and Deploy:**
## Build the project with Maven:

    
    mvn clean install
## Deploy the generated WAR file to GlassFish Server.

Access the application at: http://localhost:8080/property-management


