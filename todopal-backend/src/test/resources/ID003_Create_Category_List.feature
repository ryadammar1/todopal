Feature: Create Task Category

As a registered TodoPal Teacher
I would like to create a new task category
So that I can add new tasks to it and organize my student's work better

Scenario Outline: Teacher creating task list with a list name (Normal Flow)
  Given teacher "John Bob" with teacher email "john.bob@gmail.com" is logged in
  When teacher "john.bob@gmail.com" creates a task list with list name "math_work" for classroom "ECSE428"
Then the task list with list name "math_work" is created under teacher "john.bob@gmail.com" for classroom "ECSE428"

Scenario Outline: Teacher creating task list with an existing list name (Error Flow)

Given teacher "John Bob" with teacher email "john.bob@gmail.com" is logged in
And a task list with list name "math_work" exists under teacher "john.bob@gmail.com" for classroom "ECSE428"
When teacher "john.bob@gmail.com" creates a task list with list name "math_work" for classroom "ECSE428"
Then a "Task category already exists" message is issued

Scenario Outline: Teacher creating task list without a list name (Error Flow)

Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
When teacher "michael.scott@gmail.com" creates a task list with list name "" for classroom "ECSE428"
Then a "Category name is not provided" message is issued