Feature: Mark Task Completion

    As a registered TodoPal student
    I would like to mark a task as completed
    So that I can show that I have finished a task, and advance in the game

    Scenario Outline: Marking an incomplete task as complete(Normal Flow)

        Given an incomplete task <task_name> has been completed by student "Taha" with email "taha@pattomail.com"
        When student "Taha" marks <task_name> as complete
        Then the task is marked as "Done"

            | task_name       |
            | backlog entries |
            | bake cookies    |
            | meditate        |