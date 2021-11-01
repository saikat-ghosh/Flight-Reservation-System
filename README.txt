************	How to Run this project from Command Prompt  *************	


1. Make sure you have JAVA & NetBeans installed on your machine;

2. Set up your machine to Run JAVA programs from anywhere (SET Environment Variables in 'My Computer');

3. Open NetBeans IDE, open the project;

4. Open FlightManager, DataManager, SearchManager classes;

5. Find out './2015.spicejet.csv', './2015.silkair.csv', '.booking_history.text' lines within BufferdReader/BufferedWriter/		PrintWriter mentions;

6. Replace with your full file path;	Ex: BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\saika\\Desktop\\Flight_Reservation_System\\booking_history.txt"));

7. Click on "Build & Run", you will get a new folder 'dist' inside your project directory;

8. Go inside 'dist' folder, you will see a .jar file inside;

9. Open Command Prompt from the dist directory, make sure you run as 'administrator';

10. Run the following command :

java -jar <full project path>/dist/<jar file name>	Ex: java -jar  C:\\Users\\saika\\Desktop\\Flight_Reservation_System\\dist\\FlightReservationSystem.jar

11. hit Enter, Here you go!!	
