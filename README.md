# SchoolManagementSystem

This is a relatively simple (and terribly bad) JavaFx school-management system. It provides functionality for both students and administrators/teachers.

Students can:
* Log in to their account with an ID and password
* View their information, such as their ID, name, password, GPA, and school
* View their average grade for each of their classes, as well as individual grades for every assignment in each class
* View To Dos (not working atm)
* View the names and emails of their teachers
* Contact help
* Log out

Teachers can:
* Add assignments, which will be assigned to every student in the class
* Grade indiviual assignments
* Enroll students into classes
* View a list of schools
* View a list of teachers
* View a list of classes, and sort by a teacher
* View a list of students, and sort by a teacher
* View a list of assignments, and sort by a class
* Add a school to the database
* Add a class to the database
* Add a teacher to the database
* Add a student to the database

Currently, there is no way to add an administrator without being logged in as an administrator. This means that in order to add administrators/teachers and students, there must be a prexisting administrator in the SQL database.

## Student View
![student-view]

## Administrator View
![administrator-view]

[student-view]:resources/images/school-management-system_student-view.PNG
[administrator-view]:resources/images/school-management-system_teacher_view.PNG
