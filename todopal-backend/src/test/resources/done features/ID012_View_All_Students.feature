Feature: View All Students

    As a registered TodoPal teacher
    I would like to get a list of all students in a classroom
    So that I can view all students and select one to view their profile

    Scenario: Attempt to view all students of a non-empty classroom (Normal Flow)

        Given user "Michael Scott" with "michael.scott@gmail.com" is logged in as a teacher
        And the following students exist
            | Name | Email       |
            | Bob  | bob@dave.ee |
        And the following classroom exists
            | ClassroomName | Teacher Email           | Student Email |
            | ECSE428       | michael.scott@gmail.com | bob@dave.ee   |
        When teacher "michael.scott@gmail.com" views classroom "ECSE428"
        Then a list containing "Bob" is returned

    Scenario: Attempt to view all students of a empty classroom (Alternate Flow)

        Given user "Michael Scott" with "michael.scott@gmail.com" is logged in as a teacher
        And the following classroom exists
            | ClassroomName | Teacher Email           | Student Email |
            | ECSE428       | michael.scott@gmail.com |               |
        When teacher "michael.scott@gmail.com" views classroom "ECSE428"
        Then an "Class is empty!" message is issued

    Scenario: Attempt to view all students of a non-existing classroom (Error Flow)

        Given user "Michael Scott" with "michael.scott@gmail.com" is logged in as a teacher
        But teacher "michael.scott@gmail.com" has no classroom
        When teacher "michael.scott@gmail.com" views classroom "ECSE428"
        Then an "Inexistent Classroom" message is issued