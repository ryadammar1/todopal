Feature: View details of a task

    As a student of a TodoPal classroom
    I would like to view the details of a task
    To better understand the completion requirements for that task

    Scenario: Student views the details of a task with details (Normal Flow)
        Given student "Ricky" is logged in
        When "Ricky" decides to view the details of assigned task <task>
        Then description <details> is returned

            | task         | details                                                |
            | bake cookies | follow your favorite recipe for chocolate chip cookies |
            | do math      | do problems 5-15 starting on page 5 of math book       |

    Scenario: Student views the details of a task without details (Alternate Flow)
        Given student "Ricky" is logged in
        When "Ricky" decides to view the details of assigned task <task>
        Then an "No details available" message is issued

            | task                       | details |
            | read a book                |         |
            | do multiplication table 12 |         |