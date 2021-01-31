Feature: Create Optional Mandatory Task List

As a registered TodoPal Teacher
I would like to create an empty term-long optional task list
So that I can add new tasks to it and publish it later for students to see

Scenario Outline: Teacher creating task list with a list name (Normal Flow)

Given teacher <name> with teacher email <email> is logged in
When teacher <name> creates a term-long optional task list with list name <list_name>
Then the term long optional task list with list name <list_name> is created under teacher <name>

| name	       | email                  | list_name		|
| John Bob     | john.bob@gmail.com 	| math_work	    |
| Peter Parker | peter.parker@gmail.com | french_work 	|

Scenario Outline: Teacher creating task list with an existing list name (Error Flow)

Given teacher <name> with teacher email <email> is logged in
And weekly optional task list with list name <list_name> exists
When teacher <name> creates a term long optional task list with list name <list_name>
Then a "Task list already exists" message is issued

| name	       | email                  | list_name		|
| John Bob     | john.bob@gmail.com 	| math_work	    |
| Peter Parker | peter.parker@gmail.com | french_work 	|

Scenario Outline: Teacher creating task list without a list name (Error Flow)

Given teacher "Michael Scott" with teacher email "michael.scott@gmail.com" is logged in
When teacher "Michael Scott" creates a weekly optional task list with list name ""
Then a "List name is not provided" message is issued