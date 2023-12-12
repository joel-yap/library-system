package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JournalItem extends LibraryItem {
    private int volume;
    private int journalNumber;
    private LocalDate dueDate;

    public JournalItem(String type, int ID, String title, int year, int volume, int number, float averageRating, int reviewNumber, int maxBorrowTime, String borrowStatus) {
        super(type, ID, title, year, averageRating, reviewNumber, maxBorrowTime, borrowStatus);
        this.volume = volume;
        this.journalNumber = number;
    }
    public void setDueDate() {
        LocalDate myObj = LocalDate.now();
        dueDate = myObj.plusDays(14);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        formattedDate = dueDate.format(myFormatObj);
    }
    public int getVolume() {
        return volume;
    }
    public int getJournalNumber() {
        return journalNumber;
    }
    public ArrayList<String> getExtraDetails() {
        ArrayList<String> extraDetails = new ArrayList<>();
        extraDetails.add(String.valueOf(volume));
        extraDetails.add(String.valueOf(journalNumber));
        return extraDetails;
    }
}
