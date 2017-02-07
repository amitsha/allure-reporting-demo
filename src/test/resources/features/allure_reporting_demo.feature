@amit
Feature: Add numbers

  In order to calculate,
  As a user I want to add two or more numbers

  Rules:
  1. Addition of two or more numbers should result in the total
  2. If less than 2 numbers are provided, an error message should be displayed

  @complete
  Scenario: Add two numbers: 1 and 2
    When I add the following numbers:
      | 1 |
      | 2 |
    Then total should be 3

  @complete
  Scenario: Add four numbers: 1,2,5,10
    When I add the following numbers:
      | 1  |
      | 2  |
      | 5  |
      | 10 |
    Then total should be 18

  @complete
  Scenario: Only 1 number is provided
    When I add the following numbers:
      | 1 |
    Then the error message "Please provide at least two numbers." should be displayed

