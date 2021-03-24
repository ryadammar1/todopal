Feature: Mark Task Completion

    As a registered TodoPal student
    I would like to mark a task as completed
    So that I can show that I have finished a task, and advance in the game

    Scenario Outline: Marking an incomplete school task as complete (Normal Flow)

        Given an incomplete school task "bake cookies" has been completed by student "Taha" with email "taha@pattomail.com"
        When student "Taha" marks the task as complete
        Then the "bake cookies" is marked as "Done"
    
    Scenario Outline: Marking an incomplete personal task as complete (Alternate Flow)

        Given an incomplete personal task "bake cookies" has been completed by student "Taha" with email "taha@pattomail.com"
        When student "Taha" marks the task as complete
        Then the "bake cookies" is marked as "Closed"
