Feature: View Personal Profile

    As a Student
    I would like to view my personal profile
    So that I can confirm and visit my personal information

    Scenario Outline: Student views his/her personal profile (Normal Flow)

        Given student "John Bob" is logged in with grade "4" and age "10" and total points "15" and email "john.king@art.gallery"
        When student "John Bob" is accessing his personal profile
        Then the name of "John Bob" will be displayed
        And the grade "4" of "John Bob" will be displayed
        And the age "10" of "John Bob" will be displayed
        And the number of total points "15" of "John Bob" wll be displayed
        And the email "john.king@art.gallery" associated with "John Bob" will be displayed