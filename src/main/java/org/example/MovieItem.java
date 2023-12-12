package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MovieItem extends LibraryItem {
    private String directorName;
    private LocalDate dueDate;

    public MovieItem(String type, int ID, String title, int year, String directorName, float averageRating, int reviewNumber, int maxBorrowTime, String borrowStatus) {
        super(type, ID, title, year, averageRating, reviewNumber, maxBorrowTime, borrowStatus);
        this.directorName = directorName;
    }
    public void setDueDate() {
        LocalDate myObj = LocalDate.now();
        dueDate = myObj.plusDays(7);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        formattedDate = dueDate.format(myFormatObj);
    }
    public String getDirector() {
        return directorName;
    }
    public ArrayList<String> getExtraDetails() {
        ArrayList<String> extraDetails = new ArrayList<>();
        extraDetails.add(directorName);
        return extraDetails;
    }
}
