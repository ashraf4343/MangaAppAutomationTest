﻿# MangaAppAutomationTest
MangaAppAutomationTest
This project contains automated test cases for the Manga Application using Selenium WebDriver and JUnit 5. The tests cover various functionalities of the Manga Application, including login, search, and detailed modal display.

Table of Contents
Installation
Test Scenarios
Usage
Test Screenshots
Contributing
License
Installation
Prerequisites
Java JDK 8 or above
Maven or Gradle
Chrome WebDriver (Ensure it's added to your system PATH)
JUnit 5
Steps to Set Up
Clone the repository:

bash
Copy code
git clone https://github.com/ashraf4343/MangaAppAutomationTest.git
Navigate to the project directory:

bash
Copy code
cd MangaAppAutomationTest
Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

Run the tests using Gradle:

bash
Copy code
gradle clean test
Test Scenarios
1. Login Functionality
Verifies that the user can log in with valid credentials.
Checks the visibility of the search bar post-login.
2. Manga Search and Display
Validates that search results are correctly displayed for various manga titles.
Ensures that a "No manga found" message is displayed when no results match the search term.
3. Manga Details Modal
Ensures that clicking the "Details" link displays a modal with the correct manga information (image, name, summary).
Verifies that the modal can be closed properly.
Usage
To execute the tests, run the following command:

bash
Copy code
gradle clean test
Test results will be available in the build/reports/tests/test/index.html file.
