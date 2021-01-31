Feature: Create Teacher Account

As a teacher
I would like to become a user of the TodoPal System
So that I can create and manage tasks in a TodoPal classroom

Scenario Outline: Different Teachers (Normal Flow)

    Given an unregistered teacher wants to register for a new account
    When user <teacher_name> with email <teacher_email> registers for a teacher account with name <name> and password <password>
    Then an account for <teacher_name> is created under the email <teacher_email> with name <name> and password <password>

        | teacher_name   | teacher_email            | name      | password |
        | Michael Scott  | michael.scott@gmail.com  | Michael_S | aa001    |
        | Jim Halpert    | jim.halpert@gmail.com    | Jim_H     | cb002    |
        | Pam Beasly     | pam.beasly@gmail.com     | Pam_B     | jj003    |
        | Phillis Vans   | phillis.vans@gmail.com   | Phillis_V | lv004    |
        | Dwight Schrute | dwight.schrute@gmail.com | Dwight_S  | mr005    |


Scenario Outline: Teacher attempts to become an user with an invalid email (Error Flow)

    Given an unregistered teacher wants to register for a new account
    When user "Michael Scarn" with email "INVALID_EMAIL" registers for a teacher account with name "Real_Michael" and password "1234"
    Then an "Invalid email is used" message is issued

Scenario Outline: Existing teacher attempts to become a user (Error Flow)

    Given a registered teacher "Golden Face" with email "g.face@gmail.com" wants to register for a new account
    When user "Golden Face" with email "g.face@gmail.com" registers for a teacher account with name "GFace" and password "1234"
    Then an "Already registered" message is issued