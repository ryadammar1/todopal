Feature: User Login

    As a user
    I want to login to my account
    So that I can manage my classes and student's task progression

    Scenario: Valid Login (Normal Flow)
        Given teacher "Taha" with email "taha@pattoomail.com" and password "1234" wants to login as "teacher"
        When teacher "Taha" attemps to login with email "taha@pattoomail.com" and password "1234"
        Then the teacher will be logged in

    Scenario: Valid Login (Alternate Flow)
        Given student "Taha" with email "taha@pattoomail.com" and password "1234" wants to login as "student"
        When student "Taha" attemps to login with email "taha@pattoomail.com" and password "1234"
        Then the student will be logged in

    Scenario: Invalid role Login (Alternate Flow)
        Given student "Taha" with email "taha@pattoomail.com" and password "1234" wants to login as "teacher"
        When student "Taha" attemps to login with email "taha@pattoomail.com" and password "1234"
        Then an "Invalid login" message will be issued

    Scenario: Invalid Email (Error Flow)
        Given teacher "Taha" with email "taha@pattoomail.com" and password "1234" wants to login as "teacher"
        When teacher "Taha" attemps to login with email "taha@pattoomail.ca" and password "1234"
        Then an "Invalid email" message will be issued

    Scenario: Invalid Password (Error Flow)
        Given student "Taha" with email "taha@pattoomail.com" and password "1234" wants to login as "student"
        When student "Taha" attemps to login with email "taha@pattoomail.com" and password "12345"
        Then an "Invalid password" message will be issued