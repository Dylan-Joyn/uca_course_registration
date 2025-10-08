### Short Overview
**This document contains the package layout of the code. The code is seperated into their separate
packages with their own responsibilities.**



### Package Structure
| Path                       | Description |
|----------------------------|--------------|
| `src/app/Main.java`        | Entry point and CLI menu loop |
| `src/model/Student.java`   | Represents a student (id, name, email) |
| `src/model/Course.java`    | Represents a course (code, title, capacity, roster, waitlist) |
| `src/repo/FileRepository.java` | Handles all CSV file I/O (load/save) |
| `src/service/RegistrationService.java` | Core business logic: add, enroll, drop, list |
| `src/util/ConsoleUtil.java` | CLI printing and audit utilities |
| `src/util/ConfigUtil.java` | Loads config from system props / env / properties file |
| `src/util/LoggerUtil.java` | Centralized logging (diagnostics) |
| `src/util/ValidationUtil.java` | Centralized input validation |
| `src/exception/ValidationException.java` | Thrown for invalid input data |
| `src/exception/EnrollmentException.java` | Thrown for enrollment-related errors |
