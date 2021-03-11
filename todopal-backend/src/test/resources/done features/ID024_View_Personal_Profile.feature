Feature: View Personal Profile

    As a registered TodoPal teacher
    I would like to view my personal profile
    So that I can confirm and visit my personal information

    Scenario Outline: Teacher views his/her personal profile (Normal Flow)

        Given teacher "Michael Scott" is logged in with name "Michael_S" email "michael.scott@gmail.com", password "aa001" and bio "I hate my life"
        And "Micheal_S" has classrooms: "Classroom 1" and "Classroom 2"
        When teacher "michael.scott@gmail.com" is accessing his personal profile
        Then the name of "Michael_S" will be displayed
        And the email "michael.scott@gmail.com" associated with "Michael Scott" will be displayed
        And the classrooms "Classroom 1" and "Classroom 2" are displayed
        And the bio displays: "I hate my life"

