Feature: Create Student User

    As a student
    I would like to become a user of the TodoPal System
    So that I can take part in activities from my teacher's TodoPal lists

    Scenario Outline: Different Students (Normal Flow)

        Given an unregistered student wants to register for a new account
        When user <student_name> with email <student_email> registers for a student account with name <name> and password <password>
        Then an account for <student_name> is created under the email <student_email> with name <name> and password <password>

            | student_name   | student_email            | name    | password |
            | Jake Peralta   | jake.peralta@gmail.com   | Jake_P  | aa001    |
            | Amy Santiago   | amy.santiago@gmail.com   | Amy_S   | cb002    |
            | Terry Jeffords | terry.jeffords@gmail.com | Terry_J | jj003    |
            | Rosa Diaz      | rosa.diaz@gmail.com      | Rosa_D  | lv004    |



    Scenario Outline: Student attempts to become user with an invalid email (Error Flow)

        Given an unregistered student wants to register for a new account
        When user "Charles Boyle" with email "INVALID_EMAIL" registers for a student account with name "GobleGoble" and password "1234"
        Then an "Invalid email is used" message is issued

    Scenario Outline: Existing student attempts to become a user (Error Flow)

        Given a registered student "Raymond Holt" with email "ray.holt@gmail.com" wants to register for a new account
        When user "Raymond Holt" with email "ray.holt@gmail.com" registers for a student account with name "Kevin" and password "1234"
        Then an "Already registered" message is issued