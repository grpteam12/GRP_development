# Install.md for Employee Rating Optimization with Z3 Solver

## Overview

This project is a Spring Boot backend application that optimizes employee ratings using the Z3 solver. The application:
1. Accepts rating data as input.
2. Utilizes the Z3 solver to compute optimized, unbiased ratings.
3. Returns the calculated ratings and any associated manager bias adjustments.

This document guides you through the steps needed to set up the project, including setting up the required dependencies, configuring the Z3 solver, and running the project.

---

## Prerequisites

### 1. Java Development Kit (JDK)
- **Version**: JDK 11 or higher
- **Installation**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) and follow the installation instructions for your OS.
- **Verify**:
  ```bash
  java -version
  ```

### 2. Apache Maven
- **Version**: Maven 3.6 or higher
- **Installation**: [Download Maven](https://maven.apache.org/download.cgi) and follow the setup instructions.
- **Verify**:
  ```bash
  mvn -version
  ```

### 3. Z3 Solver
- **Version**: Z3 4.13.3 or compatible
- **Installation**:
  - Download the Z3 solver from the [official Z3 GitHub repository](https://github.com/Z3Prover/z3/releases).
  - Extract the files to a directory, such as `/home/username/z3-4.13.3/`.
  - Ensure the directory contains the required files:
    - `libz3java.so` (Linux) or `z3.dll` (Windows)
    - `z3.jar`

### 4. Maven Local Repository Configuration for Z3
  To use Z3 as a local dependency:
  - Install the `z3.jar` file to your local Maven repository:
    ```bash
    mvn install:install-file -Dfile=/path/to/z3.jar -DgroupId=com.microsoft -DartifactId=z3 -Dversion=4.13.3 -Dpackaging=jar
    ```

---

## Project Setup

### 1. Clone the Repository
Clone the project from your source control repository:
```bash
git clone <repository-url>
cd Employee-Rating-Optimization-with-Z3-Solver
```

### 2. Configure `pom.xml`
Ensure your `pom.xml` includes the Z3 dependency:
```xml
<dependency>
    <groupId>com.microsoft</groupId>
    <artifactId>z3</artifactId>
    <version>4.13.3</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/z3.jar</systemPath>
</dependency>
```
> **Note**: Adjust the `systemPath` to match your `z3.jar` location.

### 3. Configure Library Path for Z3 Native Libraries

Since Z3 includes native libraries, configure the JVM to find them.

#### Linux
Add the directory containing `libz3java.so` to your `java.library.path`:
```bash
export LD_LIBRARY_PATH=/path/to/z3/bin:$LD_LIBRARY_PATH
```

#### Windows
Add the path containing `z3.dll` to the `PATH` environment variable:
1. Open "Environment Variables" from System Properties.
2. Edit the `PATH` variable to include the path to your `z3.dll`.

Alternatively, add the path in the `VM options`:
```bash
-Djava.library.path="/path/to/z3/bin"
```

---

## Configuration

### Application Properties
Update the `application.properties` file for your Spring Boot project as needed:
```properties
spring.application.name=Employee Rating Optimization with Z3 Solver
server.port=8081
spring.main.allow-bean-definition-overriding=true
```

### Vaadin Configuration
If using Vaadin in the project, ensure the following properties are set:
```properties
vaadin.launch-browser=true
```

---

## Running the Application

### 1. Build the Project
In the project root, execute:
```bash
mvn clean install
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

### 3. Access the Application
- Open a browser and go to: `http://localhost:8081`

---

## Troubleshooting

### Common Errors

- **UnsatisfiedLinkError**: Ensure the `java.library.path` is correctly set to the directory containing Z3’s native library files (`libz3java.so` or `z3.dll`).
- **Dependency Resolution Issues**: Double-check that the `z3.jar` is correctly installed in the local Maven repository and the path in `pom.xml` is accurate.
  
---

## Additional Notes

- **Updating Z3**: If upgrading Z3, ensure you update both the `.jar` file and the native library files, and reconfigure the paths as needed.
- **Docker Support**: If Docker is required for deployment, additional configuration may be needed. Add a `Dockerfile` as needed for your deployment environment.
