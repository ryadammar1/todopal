Feature: Create Weekly Mandatory Task List

As a registered TodoPal Teacher
I would like to create an empty weekly mandatory task list
So that I can add new tasks to it and publish it later for students to see

Scenario Outline: Teacher creating task list with a list name (Normal Flow)

Given teacher "John Bob" with teacher email "john.bob@gmail.com" is logged in
When teacher "john.bob@gmail.com" creates a weekly mandatory task list with list name "math_work" for classroom "ECSE428"
Then the weekly mandatory task list with list name "math_work" is created under teacher "john.bob@gmail.com" for classroom "ECSE428"

Scenario Outline: Teacher creating task list with an existing list name (Error Flow)

Given teacher "John Bob" with teacher email "john.bob@gmail.com" is logged in
And a weekly mandatory task list with list name "math_work" exists under teacher "john.bob@gmail.com" for classroom "ECSE428"
When teacher "john.bob@gmail.com" creates a weekly mandatory task list with list name "math_work" for classroom "ECSE428"
Then a "Task category already exists" message is issued

Scenario Outline: Teacher creating task list without a list name (Error Flow)

Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
When teacher "michael.scott@gmail.com" creates a weekly mandatory task list with list name "" for classroom "ECSE428"
Then a "Category name is not provided" message is issued