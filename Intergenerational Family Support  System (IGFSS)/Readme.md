# Intergenerational Family Support System (IGFSS)

## Table of Contents
1. [Introduction](#introduction)
2. [System Design Overview](#system-design-overview)
   - [System Architecture](#system-architecture)
3. [System Components and Design](#system-components-and-design)
   - [UML Class Diagram](#uml-class-diagram)
   - [Data Storage and File Handling](#data-storage-and-file-handling)
   - [Exception Handling](#exception-handling)
   - [Client Event Handling](#client-event-handling)
   - [Server Event Handling](#server-event-handling)
4. [Testing and Validation](#testing-and-validation)
   - [Test Plan](#test-plan)
   - [Client-Server Communication Steps](#client-server-communication-steps)
5. [Installation and Running Instructions](#installation-and-running-instructions)
6. [Conclusion](#conclusion)
   - [Summary](#summary)
   - [Future Work](#future-work)

---

## Introduction

The **Intergenerational Family Support System (IGFSS)** aims to create a platform fostering community engagement between older couples and young families. By leveraging technology, this system promotes social change, support, and the creation of strong family networks. Key features include:

- Registration of members (Older Couples and Young Families).
- Viewing lists of registered members.
- Detailed views based on Family Identification Numbers (FIDNs).

---

## System Design Overview

### System Architecture

The IGFSS follows a **client-server architecture** with TCP socket communication. The system supports concurrent client interactions using a thread-per-connection model.

- **Client**: Provides a GUI or console-based menu for user interactions.
- **Server**: Manages registration, request handling, and data persistence.

Here is a visual representation of the architecture:

![System Architecture](path/to/Picture9.png)

---

## System Components and Design

### UML Class Diagram

The system includes distinct client-side and server-side components. The UML Class Diagram illustrates these components and their interactions.

![UML Class Diagram](path/to/Picture10.png)

### Data Storage and File Handling

- Data is stored in binary files:
  - `older_couples.dat` for Older Couples.
  - `young_families.dat` for Young Families.
- File operations are managed using a dedicated FileHandler class.

---

## Testing and Validation

### Test Plan

Key test scenarios:

1. **Registration of Older Couples**:
   - Input: Names, phone number, email, address, years of marriage (≥ 20).
   - Expected: Data is saved successfully in binary files.
   
   ![Older Couple Registration](path/to/Picture1.png)

2. **Registration of Young Families**:
   - Input: Names, phone number, email, address, details of children.
   - Expected: Data is saved successfully in binary files.
   
   ![Young Family Registration](path/to/Picture3.png)

3. **View Registered Data**:
   - Expected: Successfully fetches and displays data.
   
   ![Data View](path/to/Picture6.png)

---

## Installation and Running Instructions

### Prerequisites

- Java 8+ installed.
- NetBeans IDE (or equivalent Java IDE).

### Steps to Run

1. Clone or download the project files.
2. Open the Client and Server projects in your IDE.
3. Compile and run the Server class.
4. Compile and run the Client class.
5. Use the client to interact with the server for registration and data retrieval.

---

## Conclusion

### Summary

The IGFSS successfully implements a prototype client-server system for community engagement. It supports member registration, data storage, and retrieval, with a user-friendly interface.

### Future Work

- Expanding the GUI with more intuitive features.
- Implementing advanced validation for user inputs.
- Adding functionalities for enhanced community engagement.
