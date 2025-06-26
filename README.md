# EasyShop-API-Capstone 🛍️🛒
This final Capstone marks the culmination of everything I’ve learned throughout the program. Unlike previous projects where I developed applications from the ground up, this time I’m working with a pre-existing codebase for an e-commerce platform built for a company called EasyShop. The project also involves connecting to a provided website to verify that all functional requirements are being met.


# Technology Used 🛒🛍️👨🏾‍💻
For this capstone project, I used `MySQL` for database management, worked within the provided `Spring Boot` framework, developed the Java code using `IntelliJ IDEA`, and used `Postman` to test API endpoints and verify functionality.

# RoadMap 🛣️
We had multiple Documentation for this one the biggest one was Dealing
with the API witch is split into multiple packages and multiple sub packages which are:
* `Configurations` which contains`DatabaseConfig` this holds a BasicDataSource bean using values injected from external properties for the database URL, username, and password.
* `Controllers` this package contains the controllers needed to handel the request and process them and returns the appropriate responses to the client, typically acting as the bridge between the user interface and the application's business logic.
* `Data` this package contains multiple Implement classes and a sub package named `MySQl` which helps the database understand what set of queries are needed at the time when testing with `Postman` and Using the Website at the same time.
* `Models` package contains classes representing core entities of the application—such as Category, Product, User, and ShoppingCart—while its `authentication` subpackage includes DTOs and authority-related classes that handle user login, registration, and role management.
* `Security` package's `Jwt` subpackage contains classes and configurations responsible for implementing JWT-based authentication and authorization, including token generation, filtering, exception handling, and user security details.
* And Finally the `EasyshopApplication` class witch runs the program.

# Screenshots 📸

# Issues/Bugs 🙃🪰
I encountered multiple issues when trying to run the program. Since I didn’t build it from scratch, I needed to take a full day to carefully read through all the packages and classes to understand the structure and how everything works together.

Issue/Bug 1💢
---
This project used `Java 17` instead of my local `Java 24` setup to avoid compatibility issues. Matching the environment helped everything run smoothly and highlighted the importance of version control.

Issue/Bug 2 🗯️
---
`Spring Boot’s` dependency management and bean configuration can cause conflicts when components aren’t wired properly, and I ran into several issues with missing or clashing beans due to unresolved dependencies in the `pom.xml`, which cost more time than expected to track down and fix.

Issue/Bug 3 🫠
---
`Postman` occasionally required multiple sends to get a response, likely due to API compatibility issues between ``Java 17`` and my local ``Java 24`` setup. Subtle version differences or outdated `dependencies` may have caused glitches. Minor behavioral changes and module configuration in newer Java versions can also affect API responses.

Issue/Bug 4 🤬
---
Due to repeated `Postman` requests, the data was processed on the `SQL` end but failed to post correctly, resulting in multiple identical entries being saved under different category IDs.


# Future for this project 💬