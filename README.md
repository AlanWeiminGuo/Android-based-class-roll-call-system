# Android-based class roll-call system	
 
The front-end of the system is an Android app, the back-end uses the SSM framework in the Eclipse platform for the architecture, and the database uses MySQL to complete the relevant operations. The system has two user identities, student and teacher, with different functions for students and teachers. The system is divided into six modules: registration and login, course management, roll call, user information management, number of missed classes and class exercise results.

(1) Registration and Login: Teacher and student users can be registered. Teacher users need to register their teacher number, name and mobile phone, while student users need to register their student number, name and mobile phone. After registration is completed, the information will be stored in the corresponding user table in the database, and can be logged in.

(2) Modification of personal information: mainly for students and teachers when the student number, mobile phone number, etc. is changed to modify the operation.

(3) Class schedule and course list management: teachers and students can view their course schedules, teachers can issue sign-in tasks during class time, and students can sign-in after the tasks are issued.

(4) Roll call function: Teachers can issue student sign-in orders according to their course schedule; students can complete the sign-in tasks issued by teachers during the corresponding class time by "shaking their mobile phones" and other means according to the course schedule.

(5) Attendance information enquiry and management: students can inquire about their current attendance information and apply for modification of attendance information in time; teachers can inquire and modify the current attendance information of the course and save it.

(6) Attendance information statistics and analysis: students can enquire about the number of absences for each of their courses; teachers can use the system to enquire about the number of absences for each student in the courses they teach, the absence rate for each lesson, the average absence rate for the course, etc.

The system uses Android-related technology as its core, with the front-end being a page on the Android platform and the back-end using the SSM framework, completing the architecture on Eclipse, using Okhttp to connect the front-end and back-end, and using a MySQL database to complete the Android-based class roll-call system. At the same time, the "shake" check-in function is completed by monitoring the acceleration change event of the mobile phone.
