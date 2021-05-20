public class CheckHolidays {
    private String[] majorHolidays = {"Founding Day", "High-harvest", "Night of Souls", "Feast of the Moon", "Angel's Hallow"};
    private String[] minorHolidays = {"Mid-winter", "Green-grass", "Mid-Summer"};
    private String[] majorHolidayDescription = {"Celebration the founding of the Alderian Empire.", "End of summer celebration, best harvest time.",
            "Celebration the dead of family and royalty (Halloween-like).",
            "Celebration of the middle of Autumn. Large feast for the last harvests, and is told that the moon is the closest to Ethendair this night. The moon also stays visible all day and night starting this day for a full week.",
            "Celebratory day to see family, honor gods, and exchange gifts." };
    private String[] minorHolidayDescription = {"Night of small celebration of the middle of winter.",
                                                "Night of small celebration of the middle of spring.",
                                                "Night of small celebration of the middle of summer."};

    private int currentMonth = 0;
    private int currentDay = 0;
    private int daysLeft;
    private String holidayToday = " is today! What happens? ";
    private String holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);

    public CheckHolidays(int month, int day){
        currentMonth = month;
        currentDay = day;
    }

    public String checkMinorHoliday() {
        String minorHoliday = "No minor holiday today.";
        //Check for Mid-winter
        if ((currentMonth == 0 && currentDay == 30) || (currentMonth == 1 && currentDay == 1)){
            minorHoliday = minorHolidays[0] + holidayToday + minorHolidayDescription[0];
        }
        // Check for Green-grass
        else if ((currentMonth == 3 && currentDay == 30) || (currentMonth == 4 && currentDay == 1)) {
            minorHoliday = minorHolidays[1] + holidayToday + minorHolidayDescription[1];
        }
        // Check for Mid-summer
        else if ((currentMonth == 6 && currentDay == 30) || (currentMonth == 7 && currentDay == 1)) {
            minorHoliday = minorHolidays[2] + holidayToday + minorHolidayDescription[2];
        }
        return minorHoliday;
    }

    //Checks if major holiday is in this month and if it is that day
    public String checkMajorHoliday() {
        String majorHoliday = "No major holiday in sight!";
        // Check Founding Day
        if(currentMonth == 4) {
            if (currentDay == 14) {
                majorHoliday = majorHolidays[0] + holidayToday + majorHolidayDescription[0];
            } else if (currentDay < 14){
                daysLeft = checkDaysLeft(currentDay, 14);
                holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);
                majorHoliday = majorHolidays[0] + holidayNear + majorHolidayDescription[0];
            }
        }
        // Check for High-harvest
        if(currentMonth == 7 || currentMonth == 8) {
            if ((currentMonth == 7 && currentDay == 30) || (currentMonth == 8 && currentDay == 1)){
                majorHoliday = majorHolidays[1] + holidayToday + majorHolidayDescription[1];
            } else if (currentMonth == 7){
                daysLeft = checkDaysLeft(currentDay, 30);
                holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);
                majorHoliday = majorHolidays[1] + holidayNear + majorHolidayDescription[1];
            }
        }
        // Check for Night of Souls/Feast of the moon near
        if(currentMonth == 9){
            if (currentDay == 30) {
                daysLeft = checkDaysLeft(currentDay, 30);
                holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);
                majorHoliday = majorHolidays[2] + holidayToday + majorHolidayDescription[2] + "\n"
                        + majorHolidays[3] + holidayNear + majorHolidayDescription[3];
            } else {
                daysLeft = checkDaysLeft(currentDay, 30);
                holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);
                majorHoliday = majorHolidays[2] + holidayNear + majorHolidayDescription[2];
                //Feast of the Moon is right after so just increase by 1
                daysLeft++;
                holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);
                majorHoliday += "\n" + majorHolidays[3] + holidayNear + majorHolidayDescription[3];
            }
        }
        //Check for Feast of the moon
        if(currentMonth == 10 && currentDay == 1) {
            majorHoliday = majorHolidays[3] + holidayToday + majorHolidayDescription[3];
        }
        //Check for Angel's Hallow
        if(currentMonth == 11) {
            if (currentDay == 20){
                majorHoliday = majorHolidays[4] + holidayToday + majorHolidayDescription[4];
            } else if (currentDay < 20){
                daysLeft = checkDaysLeft(currentDay, 20);
                holidayNear = String.format(" is %2d days away! What is it? ", daysLeft);
                majorHoliday = majorHolidays[4] + holidayNear + majorHolidayDescription[4];
            }
        }
        return majorHoliday;
    }

    public int checkDaysLeft(int currentDay, int holidayDate){
        return (holidayDate - currentDay);
    }

}
