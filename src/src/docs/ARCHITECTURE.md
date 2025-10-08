




### 📁 Package Structure
| Path                                         | Description |
|----------------------------------------------|--------------|
| `src/src/app/Main.java`                      | Entry point and CLI menu loop |
| `src/src/model/Student.java`                 | Represents a student (id, name, email) |
| `src/src/model/Course.java`                  | Represents a course (code, title, capacity, roster, waitlist) |
| `src/src/repo/FileRepository.java`           | Handles all CSV file I/O (load/save) |
| `src/src/service/RegistrationService.java`   | Core business logic: add, enroll, drop, list |
| `src/src/util/ConsoleUtil.java`              | CLI printing and audit utilities |
| `src/src/util/ConfigUtil.java`               | Loads config from system props / env / properties file |
| `src/src/util/LoggerUtil.java`               | Centralized logging (diagnostics) |
| `src/src/util/ValidationUtil.java`           | Centralized input validation |
| `src/src/exception/ValidationException.java` | Thrown for invalid input data |
| `src/src/exception/EnrollmentException.java` | Thrown for enrollment-related errors |
