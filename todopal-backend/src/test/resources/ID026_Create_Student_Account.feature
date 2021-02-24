Feature: Create Student User

    As a student
    I would like to become a user of the TodoPal System
    So that I can take part in activities from my teacher's TodoPal lists

    Scenario Outline: Student attempts to become user (Normal Flow)

        Given an unregistered student wants to register for a new account
        When user "Jake Peralta" with email "jake.peralta@gmail.com" registers for a student account with name "Jake_P" and password "aa001"
        Then an account for "jake.peralta@gmail.com" is created under the email "jake.peralta@gmail.com" with name "Jake_P" and password "aa001"

    Scenario Outline: Student attempts to become user with an invalid email (Error Flow)

        Given an unregistered student wants to register for a new account
        When user "Charles Boyle" with email "INVALID_EMAIL" registers for a student account with name "GobleGoble" and password "1234"
        Then an "Invalid email is used" message is issued

    Scenario Outline: Existing student attempts to become a user (Error Flow)

        Given a registered student "Raymond Holt" with email "ray.holt@gmail.com" wants to register for a new account
        When user "Raymond Holt" with email "ray.holt@gmail.com" registers for a student account with name "Kevin" and password "1234"
        Then an "Already registered" message is issued