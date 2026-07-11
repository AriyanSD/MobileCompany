# Mobile Company

A Java desktop application simulating a mobile phone plan management system for a fictional telecom company, with a Swing GUI for both admins and users. Built as the cumulative final project for a Java programming course, progressively extended across multiple assignments/labs to cover core OOP concepts.

## Concepts Covered

- **Abstraction & inheritance:** an abstract `Plan` class extended by `PersonalPlan` and `BusinessPlan`, each overriding `calcPayment()` with its own pricing logic
- **Interfaces:** `Cloneable` (deep-copy support) and `Comparable` (custom ordering) implemented across `Plan`, `MobilePhone`, and `MyDate`
- **Custom exceptions:** `PlanException` and `PlanUserNameException` for invalid IDs/usernames, with auto-generated valid replacements when validation fails
- **Functional programming:** later revisions rework the collection-processing methods (filtering, total payment calculation, copying) using Java Streams, lambdas, and `Predicate`
- **Serialization & file I/O:** plans and users can be saved/loaded both as serialized binary objects (`ObjectOutputStream`/`ObjectInputStream`) and as delimited text files, with custom parsing (`stringToPlan`)
- **Regex validation:** plan IDs and usernames are validated against required patterns before being accepted
- **GUI (Swing):** a NetBeans-generated GUI with separate flows for Admin (`AdminUI`) and User (`UserUI`), including forms for creating/editing plans and users (`addNewPlanForm`, `editPlanForm`, `creatUserForm`, `editUserForm`)

## Structure

```
src/
├── Plan.java              # Abstract base class - shared plan logic
├── PersonalPlan.java      # Individual plans (priced by city/usage)
├── BusinessPlan.java      # Corporate plans (priced by employees/ABN)
├── MobileCompany.java     # Top-level company - manages users, admin auth
├── MobilePhone.java       # Handset model
├── MyDate.java            # Custom date type with comparison logic
├── User.java              # End-user account
├── PlanException.java / PlanUserNameException.java
├── LoginFrame.java / AdminUI.java / UserUI.java   # Swing UI entry points
├── addNewPlanForm.java / editPlanForm.java / creatUserForm.java / editUserForm.java
└── Main.java              # Application entry point
```

## Running It

This is a NetBeans project (Ant-based build, see `build.xml` / `nbproject/`).

```bash
ant run
```
Or open the project folder directly in NetBeans and run `Main.java`.

## Notes

This project grew lab-by-lab over a semester — each stage (cloning/copying, exception handling, collections, streams/lambdas) is still visible in the codebase as comments mark which "Lab" or "Assignment" introduced it. It's kept here as-is to show that progression rather than as a cleaned-up final product.
