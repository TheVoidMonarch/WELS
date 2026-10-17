# Workshop Equipment Loan System

An interactive Java application for managing workshop or laboratory equipment loans. The system uses `JOptionPane` dialog boxes as its initial graphical user interface and is designed with separate modules so that it can later be upgraded to a full Java GUI.

This project was developed for the **SPG0453 Secured Object Oriented Programming PBL Assignment** at the German-Malaysian Institute.

## Table of Contents

- [Project Overview](#project-overview)
- [Problem Statement](#problem-statement)
- [Objectives](#objectives)
- [Main Features](#main-features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [How the System Works](#how-the-system-works)
- [Fine Calculation](#fine-calculation)
- [How to Run the Project](#how-to-run-the-project)
- [Example Test Data](#example-test-data)
- [Input Validation](#input-validation)
- [Current Limitations](#current-limitations)
- [Future Improvements](#future-improvements)
- [Group Members](#group-members)

## Project Overview

The Workshop Equipment Loan System helps a workshop administrator manage equipment records and track equipment loans. It allows users to add equipment, check availability, record loans, process returns, search for equipment, and view reports.

The application demonstrates Java selection statements, looping statements, object-oriented programming, input validation, date processing, calculations, and dialog-box output using `JOptionPane`.

## Problem Statement

A manual equipment-loan process can make it difficult to identify available equipment, record who borrowed an item, monitor expected return dates, and determine whether returned equipment is late. These problems may result in missing records, double bookings, unavailable equipment, and delays in returning items.

This system provides a simple digital solution for managing workshop equipment and monitoring equipment loans during program execution.

## Objectives

The system aims to:

1. Provide a clear main menu and submenu structure.
2. Allow users to add, view, update, search, and remove equipment records.
3. Allow users to record equipment loans and returns.
4. Prevent users from borrowing more equipment than is available.
5. Calculate late-return fines based on overdue days and quantity borrowed.
6. Display available, borrowed, and overdue equipment reports.
7. Validate text, numeric, menu, and date inputs.
8. Provide an exit confirmation function.
9. Use a modular structure so that the system can be upgraded to a full GUI later.

## Main Features

### Equipment Management

- Add new equipment records.
- View all equipment.
- Update equipment name, category, and condition.
- Remove equipment that has no active loan.
- Track total quantity and available quantity.
- Display the current equipment status.

### Loan Equipment

Users can enter:

- Borrower name.
- Student or staff ID.
- Equipment ID.
- Quantity borrowed.
- Loan date.
- Expected return date.

The system checks whether the equipment exists and whether the requested quantity is available before creating the loan record.

### Return Equipment

Users can return equipment using the loan ID. The system:

- Updates the loan status to `Returned`.
- Increases the available equipment quantity.
- Accepts the actual return date.
- Calculates the late-return fine when applicable.

### Search Equipment

Equipment can be searched by:

- Equipment ID.
- Equipment name.
- Equipment category.

### Reports

The reports module provides:

1. All equipment.
2. Available equipment.
3. Borrowed equipment.
4. Overdue loans.
5. Total collected fines.

### Exit Function

The exit module asks the user to confirm before closing the application. Selecting **No** returns the user to the main menu.

## Technologies Used

- Java
- Maven
- Apache NetBeans
- `JOptionPane` for dialog-box input and output
- `ArrayList` for temporary in-memory storage
- `LocalDate` for date handling
- `ChronoUnit` for calculating overdue days

No external Maven dependencies are required. The application uses standard Java libraries.

## Project Structure

```text
src/main/java/com/eleventhhour/workshopequipmentloansystem/
│
├── Main.java
├── Equipment.java
├── EquipmentManager.java
├── Loan.java
├── LoanManager.java
├── ReportManager.java
├── InputValidator.java
└── ExitModule.java
```

### Module Responsibilities

| Class | Responsibility |
|---|---|
| `Main` | Starts the program and controls the main menu loop. |
| `Equipment` | Stores one equipment record. |
| `EquipmentManager` | Manages equipment creation, viewing, updating, removal, and searching. |
| `Loan` | Stores one loan record and calculates the current or final fine. |
| `LoanManager` | Processes equipment loans and returns. |
| `ReportManager` | Displays equipment, loan, overdue, and fine reports. |
| `InputValidator` | Validates text, positive numbers, and dates. |
| `ExitModule` | Handles exit confirmation independently from the main system. |

## How the System Works

1. The program displays a welcome message.
2. The main menu is displayed using `JOptionPane`.
3. The user selects a system operation.
4. The relevant module receives the request.
5. The system validates the input.
6. The system performs the requested operation.
7. A result or error message is displayed.
8. The program returns to the main menu until the user confirms exit.

The system uses a loop to keep displaying the main menu and selection statements such as `switch` to process the user's choice.

## Fine Calculation

The late-return fine is calculated using this formula:

```text
Fine = overdue days × RM2.00 × quantity borrowed
```

For an active loan, the system compares the expected return date with the computer's current date. For a returned loan, it compares the expected return date with the actual return date.

Example:

```text
Expected return date: 2026-10-12
Current or actual return date: 2026-10-17
Quantity borrowed: 1

Overdue days: 5
Fine: 5 × RM2.00 × 1 = RM10.00
```

The **Total Collected Fines** report counts completed returns. An active overdue loan may display a current estimated fine, but it is not included as a collected fine until the equipment is returned.

## How to Run the Project

### Requirements

- JDK 17 or newer.
- Apache NetBeans with Maven support.

### Run in NetBeans

1. Clone or download this repository.
2. Open the project in NetBeans.
3. Confirm that the source package matches the package declaration in every Java file:

```java
package com.eleventhhour.workshopequipmentloansystem;
```

4. Right-click the project.
5. Select **Clean and Build**.
6. Right-click the project again.
7. Select **Run**.

The main class is:

```text
com.eleventhhour.workshopequipmentloansystem.Main
```

### Run with Maven from a terminal

From the project folder, run:

```bash
mvn clean package
```

The project can then be run from NetBeans using the configured main class.

## Example Test Data

The system can be tested with the following values:

```text
Equipment ID: E001
Quantity: 1
Loan date: 2026-09-21
Expected return date: 2026-09-28
Actual return date: 2026-10-17
```

For one item, the fine should be:

```text
19 overdue days × RM2.00 × 1 = RM38.00
```

Another active-loan test is:

```text
Expected return date: 2026-10-12
Current date: 2026-10-17
Quantity: 1
Estimated fine: RM10.00
```

Dates must use the following format:

```text
YYYY-MM-DD
```

Example:

```text
2026-10-17
```

## Input Validation

The system handles several invalid-input cases:

- Empty text input.
- Invalid menu selections.
- Non-numeric quantities.
- Zero or negative quantities.
- Invalid date formats.
- Return dates before the loan date.
- Expected return dates before the loan date.
- Loans for equipment that does not exist.
- Loans for quantities greater than the available quantity.
- Removal of equipment with active loans.

## Current Limitations

- Records are stored only in memory using `ArrayList`.
- Data is lost when the application closes.
- The user interface currently uses `JOptionPane` dialog boxes instead of a full window-based GUI.
- The system is intended for a single user during one program session.
- Authentication and user roles are not included.

## Future Improvements

Possible future upgrades include:

- Replace `JOptionPane` with a Swing GUI containing frames, panels, tables, forms, and buttons.
- Save records to text files, CSV files, or a database.
- Add administrator and borrower login accounts.
- Add equipment images.
- Add automatic report exporting.
- Add stronger date and quantity validation.
- Add a dashboard showing equipment availability.
- Add editing of equipment quantity while protecting active loans.

## Group Members

- Shahzuan Shafri Bin Jefarmi — `CBS26010050`
- Akiff Zharfan Bin Bahaizil Nazim — `CBS25070655`

**Group:** 11th Hour

## Academic Context

This project was prepared for the PBL assignment for:

- **Course Code:** SPG0453
- **Course:** Secured Object Oriented Programming
- **Program:** DCBS
- **Institution:** German-Malaysian Institute
