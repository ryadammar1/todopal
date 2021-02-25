Feature: Create Weekly Mandatory Task List

As a registered TodoPal Teacher
I would like to create an empty weekly mandatory task list
So that I can add new tasks to it and publish it later for students to see

Scenario Outline: Teacher creating task list with a list name (Normal Flow)

Given teacher <name> with teacher email <email> is logged in
When teacher <name> creates a weekly mandatory task list with list name <list_name>， start date <start_date> and end date <end_date>
Then the weekly mandatory task list with list name <list_name>, start date <start_date> and end date <end_date> is created under teacher <name>

  Examples:
    | name	       | email             	    | list_name		| start_date    	| end_date  	|
    | John Bob     | john.bob@gmail.com  	| math_work 	| 2020-01-01	    | 2020-02-01	|
    | Peter Parker | peter.parker@gmail.com	| french_work 	| 2020-01-10       	| 2020-02-10	|

Scenario Outline: Teacher creating task list with an existing list name (Error Flow)

Given teacher <name> with teacher email <email> is logged in
And a weekly mandatory task list with list name <list_name> exists
When teacher <name> creates a weekly mandatory task list with list name <list_name>， start date <start_date> and end date <end_date>
Then a "Task list already exists" message is issued

Examples:
    | name	       | email             	    | list_name		| start_date    	| end_date  	|
    | John Bob     | john.bob@gmail.com  	| math_work 	| 2020-01-01	    | 2020-02-01	|
    | Peter Parker | peter.parker@gmail.com	| french_work 	| 2020-01-10       	| 2020-02-10	|

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