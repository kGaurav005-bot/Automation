Project setup (Maven + dependencies)
Configuration management (Singleton)
Strategy Pattern (Browser Strategy)
Factory Pattern
ThreadLocal DriverManager
Page Object Model
Base Test
TestNG integration
Extent Reports
Log4j2
Retry Analyzer
Screenshot utility
REST Assured framework
API authentication strategies
Database validation
Jenkins pipeline
Docker execution
Selenium Grid
BrowserStack integration
GitHub Actions CI/CD

Category	Technology	            Why
Language	Java 17	Current LTS,    enterprise standard
Build Tool	Maven	                Dependency management and lifecycle
UI Automation	Selenium 4	        Industry standard
Test Framework	TestNG	            Parallel execution, grouping, data providers
API Automation	REST Assured	    API testing
Reporting	Extent Reports	        Rich HTML reports
Logging	Log4j2	                    Enterprise logging
Assertions	AssertJ	                Fluent assertions
JSON	Jackson	JSON                serialization/deserialization
Config	Apache Commons Configuration	Externalized configuration
Retry	TestNG Retry Analyzer	      Retry failed tests
Driver Management	WebDriverManager Automatic driver binaries
Database	JDBC	                 DB validation
CI/CD	Jenkins / GitHub Actions	 Pipeline integration

EnterpriseAutomationFramework
│
├── pom.xml
├── README.md
├── Jenkinsfile
├── testng.xml
│
├── src
│   ├── main
│   │   ├── java
│   │   │    └── com.company.framework
│   │   │
│   │   └── resources
│   │        config.properties
│   │        log4j2.xml
│   │
│   └── test
│        ├── java
│        └── resources

                         TESTNG
                           │
                 ┌─────────┴─────────┐
                 │                   │
             Thread 1            Thread 2
                 │                   │
          ExecutionContext    ExecutionContext
                 │                   │
                 ▼                   ▼
              API Test            API Test
                 │                   │
                 ▼                   ▼
             ApiClient             ApiClient
                 │                   │
                 ▼                   ▼
            REST Assured         REST Assured
                 │                   │
                 └─────────┬─────────┘
                           ▼
                           API

TestNG
↓
DataProvider
↓
UserApiTest
↓
UserApiClient
↓
AuthenticationManager
↓
ConfigManager
↓
dummy token
↓
REST Assured
↓
WireMock
↓
dynamic response
↓
POJO mapping
↓
schema/business assertions
↓
PASS