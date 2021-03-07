Feature: Create Optional Task List

  As a registered TodoPal Teacher
  I would like to create an empty term-long optional task list
  So that I can add new tasks to it and publish it later for students to see

  Scenario Outline: Teacher creating task list with a list name (Normal Flow)

    Given teacher 'John Bob' with teacher email 'john.bob@gmail.com' is logged in
    When teacher 'john.bob@gmail.com' creates a term-long optional task list with list name 'math_work'
    Then the term long optional task list with list name 'math_work' is created under teacher 'john.bob@gmail.com'

  Scenario Outline: Teacher creating task list with an existing list name (Error Flow)

    Given teacher 'John Bob' with teacher email 'john.bob@gmail.com' is logged in
    And weekly optional task list with list name 'math_work' exists for teacher 'john.bob@gmail.com'
    When teacher 'john.bob@gmail.com' creates a term-long optional task list with list name 'math_work'
    Then a "Task list already exists" message is issued

  Scenario Outline: Teacher creating task list without a list name (Error Flow)

    Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
    When teacher "michael.scott@gmail.com" creates a term-long optional task list with list name ""
    Then a "List name is not provided" message is issued