
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO: Input validation?
        try {
            CreateCalendar calendar = new CreateCalendar();
            Scanner kb = new Scanner(System.in);
            boolean retry = true;
            boolean endProgram = false;
            System.out.println("Would you like to add time? (y/n)");
            // If retry, go through again. Input validation
            while (retry) {
                String response = kb.next();
                if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")){
                    System.out.println("How many days passed this session? ");
                    int daysPassed = kb.nextInt();
                    retry = false;
                    calendar.addTime(daysPassed);
                } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")){
                    System.out.println("Ending...");
                    endProgram = true;
                    retry = false;
                    Thread.sleep(500);
                } else {
                    System.out.println("Invalid answer. \nAdd time? (y/n)");
                    retry = true;
                }
            }
            kb = new Scanner(System.in);
            if (!endProgram) {
                System.out.println("Would you like to write this new date to file?");
                retry = true;
                while (retry) {
                    String writeResponse = kb.next();
                    if ((writeResponse.equalsIgnoreCase("y") || writeResponse.equalsIgnoreCase("yes"))) {
                        System.out.println("Writing to file...");
                        retry = false;
                        calendar.writeFile();
                    } else if ((writeResponse.equalsIgnoreCase("n") || writeResponse.equalsIgnoreCase("no"))) {
                        System.out.println("Ending...");
                        retry = false;
                        Thread.sleep(500);
                    } else {
                        System.out.println("Invalid answer. \nWrite to file? (y/n)");
                        retry = true;
                    }
                }
                kb.close();
            }
        }
        catch (InterruptedException interruptedException){
            System.out.println("InterruptedException: ");
            interruptedException.printStackTrace();
        }
    }
}
