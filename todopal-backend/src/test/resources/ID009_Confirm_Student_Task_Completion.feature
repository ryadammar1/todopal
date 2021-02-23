Feature: Confirm Student Task Completion

As a registered TodoPal teacher
I would like to confirm a student's task as complete
So that the student can be awarded points for officially completing a task.

Scenario Outline: Confirming the student's completed task as closed (Normal Flow)
    Given teacher "Michael Scott" is logged in
    And there is a class task called "lab 1" that is worth "5" points
    And a task "lab 1" is marked as "Done" by "Taha", with email "taha@pattomail.com"
    When "Michael Scott" marks "lab 1" as officially complete
    Then "lab 1" will be marked as "Closed"
    And "5" points will be added to the tally of student "Taha"

Scenario: Mark a task as completed and then close it (Alternate flow)
    Given teacher "Michael Scott" is logged in
    And there is a class task called "lab 1" that is worth "5" points
    And a task "lab 1" is not marked as done by "Taha", with email "taha@pattomail.com"
    When "Michael Scott" marks "lab 1" as officially complete
    Then "lab 1" will be marked as "Closed"
    And "5" points will be added to the tally of student "Taha"