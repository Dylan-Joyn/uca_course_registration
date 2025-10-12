# CHANGELOG – Clean Design Refactor

### Version 1.0 – Original
- One large `Main.java` file containing UI, logic, persistence, and data models

---

### Version 2.0 – Modular Refactor
- Extracted `Student` and `Course` into a new `model` package
- Moved file I/O into `repo.FileRepository`
- Moved registration logic into `service.RegistrationService`
- Moved print/audit helpers into `util.ConsoleUtil`
- `Main.java` now focuses solely on menu navigation and delegation

---

### Version 3.0 – Clean Design Implementation
- **SOLID Applied:** No global mutable state; single-responsibility classes.
- **Centralized Validation:** Created `ValidationUtil` for Banner ID, email, capacity, and empty-field checks.
- **Exceptions:** Added `ValidationException` and `EnrollmentException` for invalid operations.
- **Config Externalized:** Introduced `ConfigUtil` to read from `application.properties` or environment variables.
- **Logging Added:** Implemented `LoggerUtil` (using `java.util.logging`) for internal diagnostics.
- **User-Friendly CLI:** `Main.menuLoop()` handles exceptions gracefully with clear error messages.
- **Preserved Functionality:** All outputs, menus, and behavior match the original program.

### Version 4.0 - Documentation added
- **`docs`** added containing **`ARCHITETURE.md`** and **`CHANGELOG.md`**
