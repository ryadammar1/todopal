Feature: Create Teacher Account

As a teacher
I would like to become a user of the TodoPal System
So that I can create and manage tasks in a TodoPal classroom

Scenario Outline: Different Teachers (Normal Flow)

    Given an unregistered teacher wants to register for a new account
    When user "Michael Scott" with email "michael.scott@gmail.com" registers for a teacher account with name "Michael Scott" and password "aa001"
    Then a teacher account for "michael.scott@gmail.com" is created under the email "michael.scott@gmail.com" with name "Michael Scott" and password "aa001"

Scenario Outline: Teacher attempts to become an user with an invalid email (Error Flow)

    Given an unregistered teacher wants to register for a new account
    When user "Michael Scarn" with email "INVALID_EMAIL" registers for a teacher account with name "Real_Michael" and password "1234"
    Then a "Invalid email is used" message is issued

Scenario Outline: Existing teacher attempts to become a user (Error Flow)

    Given a registered teacher "Golden Face" with email "g.face@gmail.com" wants to register for a new account
    When user "Golden Face" with email "g.face@gmail.com" registers for a teacher account with name "GFace" and password "1234"
    Then a "Already registered" message is issued