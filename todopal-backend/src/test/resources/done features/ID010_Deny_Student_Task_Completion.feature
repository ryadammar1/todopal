Feature: Deny Student Task Completion

As a registered TodoPal teacher
I would like to deny the student's task as incomplete
So that the student can complete or redo the task.

Scenario Outline: Declining the student's task, marking it as incomplete (Normal Flow)

    Given a task "lab 1" is marked as "Done" by "Taha", with email "taha@pattomail.com"
    And task "lab 1" is worth "5" points
    And teacher "Michael Scott" is logged in with email "michael.scott@gmail.com"
    When "Michael Scott" marks "lab 1" as incomplete for student "taha@pattomail.com" with message "this work is not sufficient"
    Then "lab 1" will be marked as "In Progress" for student "taha@pattomail.com"
    And the message "this work is not sufficient" will be displayed in the task details