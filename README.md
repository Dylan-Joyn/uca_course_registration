# UCA Course Registration System

### How to Run the Program

---

## Option 1 – Run in IntelliJ IDEA or VS Code
1. Open the project folder in your IDE.
2. Ensure the `src` directory is marked as a **Source Root**.
3. Right-click on `Main.java` (located in `src/src/app/`) and select **Run 'Main.main()'**.
4. The console menu will appear.

---

## Option 2 – Run from the Command Line

### Step 1: Navigate to the source directory
```bash
cd src/src
```
### Step 2: Compile Java files
```bash
javac $(find . -name "*.java")
```
### Step 3: Run application
```bash
java app.Main
```
### Step 4: Run with the demo data
```bash
java app.Main --demo
```
