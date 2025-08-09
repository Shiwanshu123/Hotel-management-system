# Hotel Management System

## Overview
This is a Java-based Hotel Management System built using **Swing** for the graphical user interface and **JDBC** for database connectivity. It is designed to manage hotel operations efficiently, including rooms, customers, employees, and bookings.

## Features
- **Room Management**: Add new rooms, update existing room details, and manage room availability.
- **Customer Management**: Register new customers, update customer details, and manage bookings.
- **Employee Management**: Add and manage employee information.
- **Booking Management**: Track and update room status in real-time.
- **Login System**: Secure login authentication for hotel staff.

## Technologies Used
- **Programming Language**: Java
- **GUI Framework**: Swing (AWT components for UI)
- **Database**: MySQL (via JDBC)
- **IDE Recommendation**: NetBeans / IntelliJ IDEA / Eclipse

## Files Description
- `Login.java` - Handles staff login authentication.
- `AddRoom.java` - Interface for adding new rooms to the system.
- `UpdateRoom.java` - Updates room details and availability.
- `NewCustomer.java` - Registers new customers.
- `Employee.java` - Manages employee records.
- `conn.java` - Handles database connectivity.

## How to Run
1. **Setup Database**:
   - Install MySQL and create a database (e.g., `hotel_management`).
   - Create required tables (rooms, customers, employees, bookings).
   - Update database credentials in `conn.java`.

2. **Compile the Project**:
   - Open the project in your Java IDE.
   - Ensure JDBC driver is added to the project classpath.

3. **Run the Project**:
   - Start the MySQL server.
   - Run `Login.java` to access the system.

## Future Improvements
- Add payment processing.
- Generate automated bills/invoices.
- Implement reporting and analytics.
