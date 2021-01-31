
Feature: Join classroom by classroom ID

	As a registered TodoPal student
	I would like to join a To-Do Pal classroom with a classroom ID for the first time
	So that i can participate in classroom activities and view my tasks

	Scenario: Student enters the correct classroom ID to enter the classroom (Normal Flow)

		Given a student is logged in with email <student_email> and password <password>
		When the student enters the classroom id <classroomID>
		Then the student has the access to the mandatory and optional tasks of the classroom <classroomID>

			| student_email            | password | classroomID |
			| jake.peralta@gmail.com   | aa001    | "3607680"   |
			| amy.santiago@gmail.com   | cb002    | "3707681"   |
			| terry.jeffords@gmail.com | jj003    | "3807682"   |
			| rosa.diaz@gmail.com      | lv004    | "3907683"   |

	Scenario Outline: Existing Student attempts to enter an invalid classroomID (Error Flow)

		Given a student is logged in with email "charles.boyle@gmail.com" and password "cb1234"
		But no classroom exists with id "H2374hk0"
		When the student enters a classroomID "H2374hk0"
		Then a "No classrooms exist with that ID. Please try again" message is issued


	Scenario Outline: Existing student attempts to enter a classroom without entering a classroomID (Error Flow)

		Given a student is logged in with email "ray.holt@gmail.com" and password "rH32145"
		When the student tries to enter a classroom without entering a classroomID
		Then a "Please enter a classroomID" message is issued