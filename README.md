# BritishTimeConverter
Spring Boot application that converts conventional 12-hour format time into verbal form of time used by Britishers.

Examples:<br />
"1:00" - "one o'clock"<br />
"2:05" - "five past two"<br />
"3:10" - "ten past three"<br />
"4:12" - "four twelve"<br />
"4:15" - "quarter past four"<br />
"5:20" - "twenty past five"<br />
"6:25" - "twenty five past six"<br />
"6:32" - "six thirty two"<br />
"7:30" - "half past seven"<br />
"7:35" - "twenty five to eight"<br />
"8:40" - "twenty to nine"<br />
"9:45" - "quarter to ten"<br />
"10:50" - "ten to eleven"<br />
"11:55" - "five to twelve"<br />
"12:00" - "noon"<br />
"00:00" - "midnight"<br />


## Setup
1) This is a gradle project. Load the project into an IDE(Preferably IntelliJ).
2) Gradle version is available in "gradle/wrapper/gradle-wrapper.properties" file. Install gradle with that version.
3) Build the project using "gradle clean build" command.
4) Find the jar file created in build/libs/ folder with name "assessment-0.0.1-SNAPSHOT.jar"
5) Run the jar file using "java -jar build/libs/assessment-0.0.1-SNAPSHOT.jar". Please make sure to use Java 17.
6) A server will start in the localhost on the port 8080.


## Testing
1) Please go to this URL in your browser - http://localhost:8080
2) The URL will redirect to the Swagger documentation. Please go to the "GET /time/convert" API in "time-controller".
3) Please click on "Try it out" button to start testing.
4) Please enter the time in "rawTime" input box. Response will be available once you click "Execute" button.
5) Alternatively, you can also try the API in Postman. Postman collection is available in the Git repository.