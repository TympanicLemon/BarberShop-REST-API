# 🪒 Barbershop Management System

A RESTful API built with **Spring Boot** that models a barbershop database system. The system encapsulates the details of barbershop chains, their respective employees, and the customers visiting them.

## 📋 Tables:

### 1. **Barbershop**
   - 🔑 Barbershop ID (Primary Key)
   - 🏢 Name
   - 📍 Address
   - 🌐 Location

### 2. **Barber**
   - 🔑 Barber ID (Primary Key)
   - 👤 First Name
   - 👤 Last Name
   - 📞 Phone Number
   - 📧 Email
   - 🏢 Barbershop ID (Foreign Key)

### 3. **Customer**
   - 🔑 Customer ID (Primary Key)
   - 👤 First Name
   - 👤 Last Name
   - 📧 Email
   - 📞 Phone Number

### 4. **Barbershop_Customer** 
   (Join table for Barbershop <-> Customers relationship)
   - 🏢 Barbershop ID (Foreign Key)
   - 👤 Customer ID (Foreign Key)

## 💼 Relationships:

### 1. **Barbershop**
   - 🔗 **One-to-Many** with Barber
   - 🔗 **Many-to-Many** with Customers (via `Barbershop_Customer`)

### 2. **Barber**
   - 🔗 **Many-to-One** with Barbershop

### 3. **Customer**
   - 🔗 **Many-to-Many** with Barbershop (via `Barbershop_Customer`)

## ✨ Overview

The **Barbershop Management System** provides a robust solution for managing multiple facets of a barbershop chain. This includes individual shop details, the skilled barbers working at each location, and the diverse range of customers frequenting them.

## 🚀 Features:

- Retrieve the entire list of barbershops.
- Access detailed information of a specific store.
- List all barbers associated with a particular store.
- View detailed profile information for customers and employees.
- Fetch all customers linked to a specific store.
- List all registered customers.
- Display all employees working at a particular store.
- Delete a barbershop record, consequently removing all its associated employees.
- Remove a customer or employee record.

## 🎯 Stretch Goals:

- **Service Catalog**: Integrate a service management system where each barber can offer specific services.
- **Appointment System**: Introduce a robust appointment system.
- **Granular Control**: Detailed analytics on customer visits, revenue, etc.
- **Feedback System**: Allow customers to leave feedback.
- **Loyalty Program Integration**: Implement a customer loyalty program.
- **Digital Payment Integration**: Provide a digital payment experience.

