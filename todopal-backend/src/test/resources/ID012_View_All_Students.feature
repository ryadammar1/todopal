Feature: View All Students

    As a registered TodoPal teacher
    I would like to get a list of all students in a classroom
    So that I can view all students and select one to view their profile

    Scenario: Attempt to view all students of a non-empty classroom (Normal Flow)

        Given teacher "Michael Scott" is logged in
        And the following students exist
            | Name | ClassroomId |
            | Bob  | 1           |
            | Dave | 1           |
        And the following classroom exists
            | ClassroomId | ClassroomName | Teacher         | Students   |
            | 1           | ECSE428       | Michael Scott   | Bob,Dave   |
        When teacher "Michael Scott" views classroom "ECSE428"
        Then the following students list <Students> is returned

    Scenario: Attempt to view all students of a empty classroom (Alternate Flow)

        Given user "Michael Scott" is logged on as a teacher
        And the following classroom exists
            | ClassroomId | ClassroomName | Teacher         | Students   |
            | 1           | ECSE428       | Michael Scott   |            |
        When teacher "Michael Scott" views classroom "ECSE428"
        Then an "Empty Classroom" message is issued

    Scenario: Attempt to view all students of a non-existing classroom (Error Flow)

        Given user "Michael Scott" is logged on as a teacher
        But no classrooms exist
        When teacher "Michael Scott" views classroom "ECSE428"
        Then an "Inexistent Classroom" message is issued