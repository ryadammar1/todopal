Feature: Delete Task

    As a registered TodoPal teacher
    I would like to delete one or more tasks from different lists
    So that my students do not do a task that was added erronously

    Scenario: Delete one task (Normal Flow)
        Given teacher "Michael Scott" is logged in
        And "Michael Scott" has a mandatory list "Bonus"
        And the following tasks exist
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        When task "exist" is deleted
        Then there are no more tasks in the list "Bonus"

    Scenario: Delete more than one task (Alternate Flow)
        Given teacher "Michael Scott" is logged in
        And "Michael Scott" has a mandatory list "Bonus"
        And the following tasks exist
            | name  | description               | points | startDate  | dueDate    | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
            | new   | this is a new task        | 2      | 2021-01-24 | 2021-01-24 | math  | Bonus |
        When tasks "exist" and "new" are deleted
        Then there are no more tasks in the list "Bonus"

    Scenario: Attempt to delete a task that does not exist (Error flow)
        Given teacher "Michael Scott" is logged in
        And "Michael Scott" has a mandatory list "Bonus"
        And the following tasks exist
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        When task "notexist" is deleted
        Then a "Task cannot be deleted because it does not exist" message is issued