Feature: Create Weekly Mandatory Task List

As a registered TodoPal Teacher
I would like to create an empty weekly mandatory task list
So that I can add new tasks to it and publish it later for students to see

Scenario Outline: Teacher creating task list with a list name (Normal Flow)

Given teacher "John Bob" with teacher email "john.bob@gmail.com" is logged in
When teacher "John Bob" creates a weekly mandatory task list with list name "math_work"， start date "2020-01-01" and end date "2020-02-01"
Then the weekly mandatory task list with list name "math_work", start date "2020-01-01 and end date "2020-02-01" is created under teacher "john.bob@gmail.com"

Scenario Outline: Teacher creating task list with an existing list name (Error Flow)

Given teacher "John Bob" with teacher email "john.bob@gmail.com" is logged in
And a weekly mandatory task list with list name "math_work" exists
When teacher "John Bob" creates a weekly mandatory task list with list name "math_work"， start date "2020-01-01" and end date "2020-02-01"
Then a "Task list already exists" message is issued

Scenario Outline: Teacher creating task list without a list name (Error Flow)

Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
When teacher "Michael Scott" creates a weekly mandatory task list with list name ""， start date "2020-01-10" and end date "2020-02-10"
Then a "List name is not provided" message is issued

Scenario Outline: Teacher creating task list without a start date (Error Flow)

Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
When teacher "Michael Scott" creates a weekly mandatory task list with list name "new_list"， start date "" and end date "2020-02-10"
Then a "Start date is not provided" message is issued

Scenario Outline: Teacher creating task list without an end date (Error Flow)

Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
When teacher "Michael Scott" creates a weekly mandatory task list with list name "new_list"， start date "2020-02-10" and end date ""
Then a "End date is not provided" message is issued