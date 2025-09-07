# Changelog

All notable changes to this project will be documented in this file.

---

## [Module M01] - Initial Setup and Development

### VEHMS-M01-T001 (Configuration)
- Created a Spring Boot project with appropriate name and packaging structure.

### VEHMS-M01-T002 (Configuration)
- Configured **pgAdmin** and **Postgres** in local environment for database management.

### VEHMS-M01-T003 (Configuration)
- Configured database connection in Spring Boot with schema name and SQL properties.
- Implemented `CHANGELOG.md` file to maintain version history.

### VEHMS-M01-T004 (Test)
- Verified connection establishment with pgAdmin.
- Confirmed application runs without errors and exceptions.

### VEHMS-M01-T005 (Development)
- Implemented entities as per TR diagram requirements.
- Used **Lombok** instead of traditional setters/getters.
- Implemented enums with proper serialization.
- Primary keys set to **auto-generated**.

### VEHMS-M01-T006 (Development & Testing)
- Created database tables in Postgres based on entities.
- Implemented **One-to-Many mapping** between resident and vehicle.
- Tested table creation and mapping.

### VEHMS-M01-T007 (Development)
- Implemented API to **create a Resident with Vehicles**.
- Time fields handled programmatically (not user inputs).
- Mandatory field validations added; error messages for missing fields.
- Auto-generated IDs hidden from Swagger response.
- Enum fields integrated with `NOT NULL` constraints.

### VEHMS-M01-T008 (Development)
- Integrated **Swagger documentation** for APIs.
- Added proper descriptions and messages in Swagger UI.

### VEHMS-M01-T009 (Test)
- Tested Swagger documentation implementation.
- Verified all endpoints display correct messages and descriptions.
- Changes pushed to **main** branch.

### VEHMS-M01-T010 (Development)
- Implemented API to **fetch all Residents with their associated Vehicles**.
- JSON response structured to include resident details along with vehicle list.

# Changelog

## [VEHMS-M01-T011] - Test
- Completed testing of **M01-T010** ticket.
- Verified functionality and pushed changes to `main`.

## [VEHMS-M01-T012] - Development
- Implemented API to get resident details by **firstname**, **lastname**, or **both**.
- Added validation to restrict **numeric values** in inputs.
- Provided proper message if resident details are not found.

## [VEHMS-M01-T013] - Test
- Completed testing of **M01-T012** ticket.
- Verified functionality and pushed changes to `main`.

## [VEHMS-M01-T014] - Test phase 1.0
- Performed **end-to-end testing** of the complete application.
- Logged and resolved issues found during testing.

## [VEHMS-M01-T015] - Development
- Created API for **Vehicle creation**.
- Ensured that vehicle creation requires a valid **resident association**.
- Added validation: **registration number cannot be empty**, and all required details must be provided.

## [VEHMS-M01-T016] - Test
- Completed testing of **M01-T015** ticket.
- Verified functionality and pushed changes to `main`.

## [VEHMS-M01-T017] - Development
- Implemented API to fetch **user details using registration number**.
- Validation: registration number must be exactly **10 characters**.
- Throws **Invalid Registration Number** for wrong length/format.
- Returns user details only (no extra vehicle details).
- Added message for cases where no vehicle is found.

## [VEHMS-M01-T018] - Test
- Completed testing of **M01-T017** ticket.
- Verified functionality and pushed changes to `main`.

## [VEHMS-M01-T019] - Test phase 1.1
- Conducted **application-wide testing** after phase 1.0 changes.
- Logged and resolved additional bugs.  

