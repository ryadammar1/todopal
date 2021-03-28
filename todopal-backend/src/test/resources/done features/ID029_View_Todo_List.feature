Feature: View Todo List

    As a registered TodoPal student
    I would like to get a list of all tasks I am assigned to
    So that I can view them and be able to mark them as completed

    Scenario: Student attempts to view all his tasks and is part of a classroom (Normal Flow)

        Given student "John" is logged in with email "John@gmail.com" and password "1234" and total points "0"
        And the following tasks exist for "John@gmail.com" in class "ECSE428"
            | TaskName          | Status       |
            | Read book         | To-do        |
            | Complete homework | In Progress  |
            | Watch tutorial    | Done         |
            | Read tutorial     | To-do        |
        When user "John@gmail.com" views his todolist
        Then the following todo list is returned "To-do Read book,In Progress Complete homework,Done Watch tutorial,To-do Read tutorial"

    Scenario: Student attempts to view all of his tasks but is not part of a classroom (Error Flow)

        Given student "John" is logged in with email "John@gmail.com" and password "1234" and total points "0"
        But user "John@gmail.com" is not part of a classroom
        When user "John@gmail.com" views his todolist
        Then an "Student is not part of a classroom" message is issued