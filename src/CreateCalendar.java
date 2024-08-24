import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateCalendar {

    private String[] months = {"Harnen", "Chay", "Anderik", "Miretorn", "Elarin", "Trakerule",
            "Mareasis", "Ulntar", "Krythrin", "Niilhin", "Eas", "Faldient"};

    private ArrayList<String> date = new ArrayList<>();

    private final int endOfMonth = 30;
    private int currentDay = 0;
    private int currentMonth = 0;
    private int currentYear = 0;

    public CreateCalendar() {
        readFile();
        printDate();
        holidays();
    }
    public CreateCalendar(int setDay, int setMonth) throws IOException {
        currentDay =  setDay;
        currentMonth = setMonth;
    }

    //Read file and set current date in ArrayList<>
    public void readFile() {
        try {
            File currentDate = new File("currentdate.txt");
            Scanner fileReader = new Scanner(currentDate);
            while(fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                System.out.println(data);
                date.add(data);
            }
        } catch (FileNotFoundException fe) {
            System.out.println("An error occurred while reading the file: ");
            fe.printStackTrace();
        }
    }

    // Show the current date before changes
    public void printDate() {
        currentMonth = Integer.parseInt(date.get(0));
        currentDay = Integer.parseInt(date.get(1));
        currentYear = Integer.parseInt(date.get(2));

        System.out.println("Current date: " + getMonthName(currentMonth) + "/" + currentDay + "/" + currentYear);
    }

    // Add amount of time that passed
    public void addTime(int timePassed){
        int originalDay = currentDay;
        int monthsPassed = 0;
        int newDaysPassed = 0;
        currentDay += timePassed;

        //test to see if month/year ended
        if (currentDay > endOfMonth) {
            newDaysPassed = currentDay % endOfMonth;
            currentDay = newDaysPassed;
            monthsPassed = (originalDay + timePassed) / endOfMonth;
            //test to see if year ended
            if (monthsPassed == 12) {
                currentYear++;
            } else if (monthsPassed > 12) {
                currentYear = currentYear + (monthsPassed / 12);
                currentMonth = currentMonth + (monthsPassed % 12);
            } else if (currentMonth == 11){
                //Go back to start if year ends
                currentMonth = monthsPassed - 1;
                currentYear++;
            } else {
                currentMonth += monthsPassed;
            }
        }

        //Add month to date
        date.add(0, String.valueOf(currentMonth));
        //Add day to date
        date.add(1, String.valueOf(currentDay));
        //Add year to date
        date.add(2, String.valueOf(currentYear));

        System.out.println("New date: " + getMonthName(currentMonth) + "/"  + currentDay + "/" + currentYear);
        holidays();
    }

    // Write the date to a file
    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter("currentdate.txt");
            System.out.println("Month: " + getMonthName(currentMonth) + "(" + currentMonth + ")");
            System.out.println("Day: " + currentDay);
            System.out.println("Year: " + currentYear);
            fileWriter.write(currentMonth + "\n");
            fileWriter.write(currentDay + "\n");
            fileWriter.write(currentYear + "\n");
            fileWriter.close();
        } catch (IOException ioEx) {
            System.out.println("Error writing to file: ");
            ioEx.printStackTrace();
        }
    }

    public void holidays(){
        CheckHolidays checkHolidays = new CheckHolidays(currentMonth, currentDay);
        ArrayList<String> holidays = new ArrayList<>();
        holidays.add(checkHolidays.checkMinorHoliday());
        holidays.add(checkHolidays.checkMajorHoliday());
        System.out.println(holidays.get(0) + "\n" + holidays.get(1));
    }

    public void printCalendar(){
        System.out.println("Print Calendar");
    }


    public String[] getMonths(){
        return months;
    }

    public int getEndOfMonth(){
        return endOfMonth;
    }

    public int getCurrentDay(){
        return this.currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentMonth(){
        return this.currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public String getMonthName(int current){
        return months[currentMonth];
    }
    
    public int getCurrentYear(){
        return this.currentYear;
    }

    public void setCurrentYear(int newYear){
        this.currentYear = newYear;
    }

}
