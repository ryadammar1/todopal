Feature: Create Task

    As a registered TodoPal teacher
    I would like to create a task
    To add to my class's To-Do list

    Scenario: Create task with existing tasks in the list (Normal flow)
        Given teacher "Michael Scott" is logged in
        And "Michael Scott" has a mandatory list "Bonus"
        And the following tasks exist
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        When a task with the following information is created
            | name  | description               | points | startDate  | dueDate    | tag   | List  |
            | new   | this is a new task        | 2      | 2021-01-24 | 2021-01-24 | math  | Bonus |
        Then the following tasks exist now
            | name  | description               | points | startDate  | dueDate    | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
            | new   | this is a new task        | 2      | 2021-01-24 | 2021-01-24 | math  | Bonus |

    Scenario: Create task without existing tasks in the list (Alternate flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        Then the following tasks exist now
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |

    Scenario: Create a task with start date not specified (Alternate flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      |            | 9999-12-12 | math  | Bonus |
        Then the following tasks exist now
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      |            | 9999-12-12 | math  | Bonus |

    Scenario: Create a task with due date not specified (Alternate flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 |            | math  | Bonus |
        Then the following tasks exist now
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 |            | math  | Bonus |

    Scenario: Create a task with tag not specified (Alternate flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 |       | Bonus |
        Then the following tasks exist now
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | Other | Bonus |

    Scenario: Attempt to create an existing task (Error flow)
        Given teacher "Michael Scott" is logged in
        And "Michael Scott" has a mandatory list "Bonus"
        And the following tasks exist
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        Then a "The task was already created" message is issued

    Scenario: Attempt to create a task with invalid date (Error flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | 1      | 2021-01-23 | 2021-01-22 | math  | Bonus |
        Then a "Tasks cannot have due date before the starting date" message is issued


    Scenario: Attempt to create a task with invalid name (Error flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            |       | this is an existing task  | 1      | 2021-01-23 | 2021-01-23 | math  | Bonus |
        Then an "Tasks cannot have an empty name" message is issued

    Scenario: Attempt to create a task with invalid points (Error flow)
        Given teacher "Michael Scott" is logged in
        But There are no tasks for teacher "Michael Scott" in the mandatory list "Bonus"
        When a task with the following information is created
            | name  | description               | points | start date | due date   | tag   | list  |
            | exist | this is an existing task  | -1     | 2021-01-23 | 2021-01-23 |       | Bonus |
        Then an "Tasks cannot have negative point values" message is issued