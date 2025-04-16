Absolutely, Tshifhiwa! Here's a polished and comprehensive `README.md` file that documents everything you've built — the encryption flow, the test runner, the Maven profile for UAT Sanity execution, and the Allure reporting.

---

# 🧪 OrangeHRM Automation Framework

This repository contains test automation tools for the OrangeHRM application, including encrypted credential management and Cucumber-based BDD testing with parallel execution and reporting.

---

## 📂 Project Structure Overview

```
├── src
│   ├── main
│   │   └── java/com/orangehrm/crypto/run/EncryptionFlowTests.java
│   ├── test
│   │   └── java/com/orangehrm/runners/TestRunner.java
│   └── resources/features/
├── pom.xml
├── target/
│   ├── cucumber-reports/
│   ├── surefire-reports-uat/
│   └── allure-report/
```

---

## 🔐 Credential Encryption Utility

### 📄 Class: `EncryptionFlowTests.java`

Manual runner for encrypting and saving sensitive environment variables like `PORTAL_USERNAME` and `PORTAL_PASSWORD`.

### 📌 Usage

1. Open the class:
   ```
   com.orangehrm.crypto.run.EncryptionFlowTests
   ```

2. Set credentials for encryption:
   ```java
   private static final String USERNAME = "PORTAL_USERNAME";
   private static final String PASSWORD = "PORTAL_PASSWORD";
   ```

3. Run the `main()` method:
   ```bash
   java com.orangehrm.crypto.run.EncryptionFlowTests
   ```

4. This will:
    - Generate a secret key
    - Encrypt credentials
    - Write to the appropriate environment configuration

---

## 🧪 Test Execution – JUnit 5 + Cucumber

### 📄 Test Runner

```java
com.orangehrm.runners.TestRunner
```

### ✅ Features

- Executes Cucumber tests using the JUnit 5 engine
- Tags filtering: `@Sanity and not @Ignore`
- Step definitions from:  
  `com.orangehrm.stepDefinitions`, `com.orangehrm.hooks`
- Parallel execution: Enabled with **dynamic strategy**
- Report types: **Pretty**, **HTML**, **JSON**, **Allure**

---

## 🚀 How to Run UAT Sanity Tests

### 📁 Maven Profile: `uat-sanity`

```xml
<profile>
    <id>uat-sanity</id>
    <activation>
        <property>
            <name>env</name>
            <value>uat</value>
        </property>
    </activation>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.5.2</version>
                <configuration>
                    <includes>
                        <include>**/TestRunner.java</include>
                    </includes>
                    <systemPropertyVariables>
                        <cucumber.parallel.count>4</cucumber.parallel.count>
                    </systemPropertyVariables>
                    <reportsDirectory>${project.build.directory}/surefire-reports-uat</reportsDirectory>
                    <forkCount>1C</forkCount>
                    <useSystemClassLoader>false</useSystemClassLoader>
                </configuration>
            </plugin>
        </plugins>
    </build>
</profile>
```

### ▶️ Run from CLI

```bash
mvn clean test -Denv=uat -Puat-sanity
```

This command:
- Runs only scenarios tagged with `@Sanity`
- Outputs HTML and JSON reports in `target/cucumber-reports`
- Stores Surefire reports in `target/surefire-reports-uat`
- Enables multi-core test execution

---

## 📊 Allure Report

After test execution, generate the Allure report using:

```bash
allure generate allure-results --clean -o target/allure-report
```

Or view live using:

```bash
allure serve allure-results
```

> Allure output is located under `target/allure-report/`.

---

## 🛠 Prerequisites

Ensure the following are set up in your project:

- Java 11 or higher
- Maven
- Allure CLI installed (`npm install -g allure-commandline`)
- Required dependencies in `pom.xml`:
    - `bcprov-jdk18on`, `dotenv-java`, `log4j`, `selenium-java`, `cucumber-java`, `junit-jupiter`, `maven-cucumber-reporting`, etc.

---