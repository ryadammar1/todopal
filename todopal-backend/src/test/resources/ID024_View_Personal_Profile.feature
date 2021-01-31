Feature: View Personal Profile

    As a registered TodoPal teacher
    I would like to view my personal profile
    So that I can confirm and visit my personal information

    Scenario Outline: Teacher views his/her personal profile (Normal Flow)

        Given teacher "Michael Scott" is logged in with name "Michael_S" email "michael.scott@gmail.com" and password "aa001"
        When teacher "Michael Scott" is accessing his personal profile
        Then the name of "Michael_S" will be displayed
        And the email "mic***@gmail.com" associated with "Michael Scott" will be displayed