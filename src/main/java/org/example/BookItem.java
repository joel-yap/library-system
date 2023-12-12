package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookItem extends LibraryItem{
    private String authorName;
    private int pageNumber;
    private LocalDate dueDate;

    public BookItem(String type, int ID, String title, int year, String authorName, int pageNumber, float averageRating, int reviewNumber, int maxBorrowTime, String borrowStatus) {
        super(type, ID, title, year, averageRating, reviewNumber, maxBorrowTime, borrowStatus);
        this.authorName = authorName;
        this.pageNumber = pageNumber;
    }

    public void setDueDate() {
        LocalDate myObj = LocalDate.now();
        dueDate = myObj.plusDays(28);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        formattedDate = dueDate.format(myFormatObj);
    }
    public String getAuthor() {
        return authorName;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public ArrayList<String> getExtraDetails() {
        ArrayList<String> extraDetails = new ArrayList<>();
        extraDetails.add(authorName);
        extraDetails.add(String.valueOf(pageNumber));
        return extraDetails;
    }
}
