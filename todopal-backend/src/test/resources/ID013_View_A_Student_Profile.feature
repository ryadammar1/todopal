Feature: View a student profile

    As a registered TodoPal teacher
    I would like my view one of my students' profile
    So I can see how well my students are performing

    Scenario: View a student's profile (Normal flow)
        Given user "Michael Scott" is logged in as a teacher
        And they have selected classroom with classroom id 1
        And The following student accounts exist class 1
            | Name | ClassroomId | Points | TasksCompleted |
            | Bob  | 1           | 1      | 1,2            |
            | Dave | 1           | 3      | 1,2,4          |
        When they view the student profile of student "Bob" for classroom 1
        Then the profile information is provided
            | Name | ClassroomId | Points | TasksCompleted |
            | Bob  | 1           | 1      | 1,2            |