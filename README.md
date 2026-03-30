# SauceDemo Automation Framework (Final Task)


## 🚀 Technical Stack
* **Language:** Java 25
* **Framework:** TestNG & Cucumber (BDD)
* **Design Pattern:** Page Object Model (POM)
* **Build Tool:** Maven
* **Reporting:** Allure Report

---

## 🛠️ Project Structure
The project follows a standard Maven/Cucumber directory structure:
* `src/test/resources/features`: Gherkin feature files (UC-1, UC-2, UC-3).
* `src/test/java/stepdefs`: Glue code connecting Gherkin steps to Java methods.
* `src/main/java/pages`: Page Object classes containing locators and actions.
* `src/test/java/hooks`: Global setup/teardown (browser initialization & ThreadLocal).
* `testng.xml`: Configuration for parallel cross-browser execution.

---

## ⚙️ How to Run the Tests

### 1. Running via TestNG 
To run tests, right-click the `testng.xml` file in the root directory and select **Run**.

### 2. Running via Maven Command Line
You can also run specific browsers using Maven properties:
* **Chrome:** `mvn test -Dbrowser="chrome"`
* **Firefox:** `mvn test -Dbrowser="firefox"`

---

## 📊 Generating the Allure Report
After the tests have finished executing, follow these steps to view the graphical report:

1. Open your terminal in the project root.
2. Run the following command to generate and open the report:
   ```bash
   mvn allure:serve