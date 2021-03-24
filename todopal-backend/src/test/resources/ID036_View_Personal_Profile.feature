Feature: View Personal Profile

    As a Student
    I would like to view my personal profile
    So that I can confirm and visit my personal information

    Scenario Outline: Student views his/her personal profile (Normal Flow)

        Given student "John Bob" is logged in with email "john.king@art.gallery" and password "Bob1234" and total points "15"
        When student "john.king@art.gallery" is accessing his personal profile
        Then the name of "John Bob" will be displayed
        And the number of total points "15" of "John Bob" wll be displayed
        And the email "john.king@art.gallery" associated with "John Bob" will be displayed