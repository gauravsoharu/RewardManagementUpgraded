# RewardManagementUpgraded
## Reward Management System 

# Description
The Reward Management System is a Spring Boot-based application designed to manage and calculate customer rewards based on their transaction history. The application consists of multiple layers including controllers, services, repositories, and exception handling to offer a seamless user experience. It allows clients to query monthly reward points for a specific customer.

# Key Features:
Fetch customer rewards for a given month.
Transactions linked with reward points earned by the customer.
Custom error handling with specific exceptions for cases like customer not found and processing errors.
RESTful API for fetching reward data.

### Logic
# Controller Layer:

Handles incoming HTTP requests and delegates the business logic to the RewardService.
The /api/rewards/{customerId}/{month} endpoint is exposed for fetching monthly rewards based on the customer ID and month.

# Service Layer:

The RewardService retrieves the customer data and their corresponding transaction history.
It groups transactions by month and calculates the total reward points earned for that month.

# Repository Layer:

The CustomerRepository provides access to customer data stored in the database.
The TransactionRepository retrieves transaction details within the specified date range for each month.

# Exception Handling:

Custom exceptions (CustomerNotFoundException, RewardProcessingException) are thrown for specific error scenarios.
The GlobalExceptionHandler handles exceptions globally and returns appropriate HTTP status codes and error messages.
# Screenshots
## API Response Example (JSON):


#### Sample Request and Response
### Sample Request:
### URL:http://localhost:8080/api/rewards/C12345/January
### Method: GET

## Sample Response (Success):
json
Copy
Edit
{
    "customerId": "12345",
    "customerName": "John Doe",
    "rewardPointsSummary": [
        {
            "month": "January",
            "year": 2025,
            "totalPoints": 250,
            "transactions": [
                {
                    "transactionId": 1,
                    "transactionDate": "2025-01-05",
                    "transactionAmount": 500.0,
                    "pointsEarned": 100,
                    "transactionDescription": "Purchase 1"
                },
                {
                    "transactionId": 2,
                    "transactionDate": "2025-01-15",
                    "transactionAmount": 800.0,
                    "pointsEarned": 150,
                    "transactionDescription": "Purchase 2"
                }
            ]
        }
    ]
}
## Sample Response (Customer Not Found):
json
Copy
Edit
{
    "timestamp": "2025-01-24T15:30:00",
    "status": 404,
    "error": "Not Found",
    "message": "Customer not found: 12345"
}
### Layers Utilized

## Controller Layer:
 Exposes REST APIs to the clients.
 Utilizes @RestController to manage HTTP requests and responses.

## Service Layer :
 Handles the business logic and calls the repository layer to fetch data.
 Exception handling and transaction grouping are done here.
 ## Repository Layer:
  Interfaces with the database using Spring Data JPA repositories (CustomerRepository, TransactionRepository).

## Exception Handling Layer:
Custom exceptions (CustomerNotFoundException, RewardProcessingException) handle specific error cases.
@ControllerAdvice is used for global exception handling.

## Technologies Used
Java 22: The primary language for backend logic.
Spring Boot: The framework used to create the RESTful application.
Spring Data JPA: To interact with the relational database.
JUnit: For unit testing and mocking dependencies.
Mockito: For mocking the service and repository layers during testing.
Lombok: For reducing boilerplate code like getters and setters.

## The application will run on localhost:8080.
Test the API:

Use Postman or any API testing tool to make GET requests to the endpoint:
/api/rewards/{customerId}/{month}
Replace {customerId} with the actual customer ID and {month} with the required month.

## Unit Testing
The application includes unit tests to validate the functionality of the service layer and controller endpoints. It uses Mockito for mocking dependencies and JUnit for assertions.















