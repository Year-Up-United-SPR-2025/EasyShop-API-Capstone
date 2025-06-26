# EasyShop-API-Capstone 🛍️🛒
This final Capstone marks the culmination of everything I’ve learned throughout the program. Unlike previous projects where I developed applications from the ground up, this time I’m working with a pre-existing codebase for an e-commerce platform built for a company called EasyShop. The project also involves connecting to a provided website to verify that all functional requirements are being met.


# Technology Used 🛒🛍️👨🏾‍💻
For this capstone project, I used `MySQL` to create and manage the database, `IntelliJ IDEA` to develop the Java code, and `Postman` to test the API endpoints and ensure the application runs as expected.

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

# Issues 🙃
I ran into multiple issues when trying to run this program 

# Parts Completed 🧩