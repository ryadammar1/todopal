Feature: Delete Task

    As a registered TodoPal teacher
    I would like to delete one or more tasks from different lists
    So that my students do not do a task that was added erroneously

    Scenario: Delete one task (Normal Flow)
        Given teacher "Michael Scott" is logged in with email "Michael Scott@gmail.com"
        And "Michael Scott" has a class "ECSE449"
        And the following tasks exist for teacher "Michael Scott"
            | name  | description              | points | start date | due date   | tag  | list  |
            | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | Bonus |
        When task "exist" is deleted from class "ECSE449" for teacher "Michael Scott"
        Then there are no more tasks in the class

    Scenario: Delete more than one task (Alternate Flow)
        Given teacher "Michael Scott" is logged in with email "Michael Scott@gmail.com"
        And "Michael Scott" has a class "ECSE449"
        And the following tasks exist for teacher "Michael Scott"
            | name  | description              | points | startDate  | dueDate    | tag  | list  |
            | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | Bonus |
            | new   | this is a new task       | 2      | 2021-01-24 | 2021-01-24 | math | Bonus |
        When task "exist" and "new" is deleted from class "ECSE449" for teacher "Michael Scott"
        Then there are no more tasks in the class

    Scenario: Attempt to delete a task that does not exist (Error flow)
        Given teacher "Michael Scott" is logged in with email "Michael Scott@gmail.com"
        And "Michael Scott" has a class "ECSE449"
        And the following tasks exist for teacher "Michael Scott"
            | name  | description              | points | start date | due date   | tag  | list  |
            | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | Bonus |
        When task "nonexist" is deleted from class "ECSE449" for teacher "Michael Scott"
        Then a "Task cannot be deleted because it does not exist" message is issued