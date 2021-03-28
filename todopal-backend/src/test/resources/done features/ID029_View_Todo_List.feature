Feature: View Todo List

    As a registered TodoPal student
    I would like to get a list of all tasks I am assigned to
    So that I can view them and be able to mark them as completed

    Scenario: Student attempts to view all his tasks and is part of a classroom (Normal Flow)

        Given user "John" is logged in as a student
        And the following tasks exist for "John"
            | TaskName          | Status       | List      |
            | Read book         | To-do        | Mandatory |
            | Complete homework | In Progress  | Personal  |
            | Watch tutorial    | Done         | Optional  |
            | Read tutorial     | To-do        | Mandatory |
        When user "John" views his todolist
        Then the following todo list is returned "To-do Read book,In Progress Complete homework,Done Watch tutorial,To-do Read tutorial"

    Scenario: Student attempts to view all of his tasks but is not part of a classroom (Error Flow)

        Given user "John" is logged on as a student
        But user "John" is not part of a classroom
        When user "John" views his todolist
        Then an "Student is not part of a classroom" message is issued