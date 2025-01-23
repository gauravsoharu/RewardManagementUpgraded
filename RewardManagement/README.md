Customer Rewards Service - Spring Boot Application

Overview
This Spring Boot application provides a Customer Rewards service that calculates reward points for customers based on their transaction history. The service and controller handle both valid and invalid scenarios to ensure robustness, including the case where a customer does not exist.

Features
Reward Calculation: The RewardService calculates reward points based on transaction history.
Controller Endpoint: The RewardController exposes an API endpoint to get reward points for a given customer ID.
Error Handling: Handles invalid customer IDs with proper error responses.


Technologies Used
Spring Boot 2.x
JUnit 5
Mockito

The application will start on port 8080 by default.

Service Layer - RewardService
Description
The RewardService class handles the business logic related to reward points calculation. It interacts with the database (simulated here using a RewardRepository) to fetch customer transactions and calculates rewards based on certain business rules.

Methods:
calculateRewards(Long customerId):
Takes a customer ID as input.
Fetches transactions for that customer from the RewardRepository.
Calculates and returns the total reward points.
If the customer ID is invalid or no transactions are found, throws an IllegalArgumentException.
Controller Layer - RewardController
Description
The RewardController class exposes a REST API endpoint to retrieve reward points for a customer by their ID. It uses the RewardService to process the request and respond accordingly.

API Endpoints:
GET /rewards/{customerId}
Fetches reward points for a customer based on their customerId.
MockMvc

Testing
Service Layer - RewardServiceTest
Test Description
The RewardServiceTest class contains JUnit tests to verify the business logic of the RewardService. Specifically, the test for invalid customer IDs checks that the service throws the appropriate exception when an invalid ID is passed.


