# Parallel Test Execution with Docker Compose and TestNG

This project demonstrates how to execute TestNG tests in parallel using Docker Compose, significantly reducing test execution time. This approach is perfect for showcasing distributed testing skills in SDET interviews.

## Project Overview

The project contains three test modules (A, B, and C) that are executed in parallel using Docker Compose. Each module runs in its own container, demonstrating how to split a test suite into smaller groups for faster execution.

### Key Features

- **Modular Test Structure**: Tests organized by module/feature
- **Parallel Execution**: Using Docker Compose to run tests concurrently
- **Container Isolation**: Each test module runs in its own environment
- **TestNG Parallel Methods**: Additional parallelization within each module using TestNG

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker and Docker Compose

## Project Structure

```
.
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           ├── tests/
│       │           │   ├── ModuleATests.java
│       │           │   ├── ModuleBTests.java 
│       │           │   └── ModuleCTests.java
│       │           └── utils/
│       │               └── TestListener.java
│       └── resources/
│           ├── testng.xml
│           └── testng-xmls/
│               ├── moduleA.xml
│               ├── moduleB.xml
│               └── moduleC.xml
├── docker/
│   └── entrypoint.sh
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## How It Works

1. Each module (A, B, C) has its own TestNG XML configuration
2. Docker Compose spins up three containers, each running a different module
3. Tests within each module also run in parallel using TestNG's parallel execution
4. Results from all containers are saved to the target directory

## Running the Tests

### First Time Setup

```bash
# Build the Maven project without running tests
mvn clean package -DskipTests

# Build Docker images and start containers
docker-compose up --build
```

### Subsequent Runs

```bash
# Just run the tests with existing images
docker-compose up
```

### Cleaning Up

```bash
# Stop all containers and remove networks
docker-compose down
```

## Troubleshooting

### Common Issues

#### TestNG XML File Not Found Error
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.1.2:test (default-test) on project parallel-test-docker: Execution default-test of goal org.apache.maven.plugins:maven-surefire-plugin:3.1.2:test failed: testSuiteXmlFiles0 has null value
```

**Solution:** This occurs when Maven can't find the TestNG XML file. Use one of these approaches:
1. Skip tests during build: `mvn clean package -DskipTests`
2. Specify a TestNG XML file: `mvn clean package -DtestNG.xml=src/test/resources/testng.xml`

#### Docker Image Naming Error
```
unable to get image 'parallel-test-docker-moduleB': Error response from daemon: invalid reference format: repository name must be lowercase
```

**Solution:**
1. Explicitly specify lowercase image names in the docker-compose.yml file
2. Ensure all container and image names follow Docker naming conventions (lowercase only)

#### Tests Not Running in Parallel
**Solution:** Verify that:
1. Your Docker Compose file correctly defines separate services
2. Each TestNG XML file includes parallel execution settings (`parallel="methods"` attribute)
3. Your system has enough resources to run multiple containers

| Approach | Execution Time | Notes |
|----------|---------------|-------|
| Sequential | ~23 seconds | Running all tests in one container |
| Parallel (Docker Compose) | ~8 seconds | 3 containers running in parallel |
| Combined (Docker + TestNG) | ~5 seconds | Both container and thread-level parallelism |

*Note: Actual performance will vary based on hardware resources and test complexity.*