Feature: Delete Personal Task

    As a registered TodoPal student
    I would like to delete a personal task
    So that I can de-clutter my personal to-do list by removing erronously added items

    Scenario: Student deletes an existing personnal task (Normal Flow)

        Given student "Taha" is logged in
        And "Taha" has a personal list "Personal"
        And the following tasks exist
            | name  | description              | points | start date | due date   | tag  | list     |
            | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | Personal |
        When task "exist" is deleted
        Then there are no more tasks in the list "Personal"

    Scenario: Student attemps to delete a task that doesn't exist (Error Flow)
        Given student "Taha" is logged in
        And "Taha" has a personal list "Personal"
        And the following tasks exist
            | name  | description              | points | start date | due date   | tag  | list     |
            | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | Personal |
        When task "notexist" is deleted
        Then a "Task cannot be deleted because it does not exist" message is issued

    Scenario: Student attemps to delete a non-personal task (Error Flow)
        Given student "Taha" is logged in
        And "Taha" has a personal list "Personal"
        And the following tasks exist
            | name  | description              | points | start date | due date   | tag  | list         |
            | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | non-personal |
        When task "exist" is deleted
        Then a "Task cannot be deleted because you lack the permissions needed" message is issued