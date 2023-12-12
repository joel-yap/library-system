package org.example;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class LibraryItem {
    protected String type;
    protected int ID;
    protected String title;
    protected int year;
    protected float averageRating;
    protected int reviewNumber;
    protected int maxBorrowTime;
    protected String borrowStatus;
    protected LocalDate dueDate;
    protected String formattedDate;

    public LibraryItem(String type, int ID, String title, int year, float averageRating, int reviewNumber, int maxBorrowTime, String borrowStatus) {
        this.type = type;
        this.ID = ID;
        this.title = title;
        this.year = year;
        this.averageRating = averageRating;
        this.reviewNumber = reviewNumber;
        this.maxBorrowTime = maxBorrowTime;
        this.borrowStatus = borrowStatus;
    }

    ArrayList<Integer> ratingList = new ArrayList<>();

    public int getID() {
        return ID;
    }
    public String getType() {
        return type;
    }
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    public float getRating() {
        return averageRating;
    }
    public int getReviewNumber() {
        return reviewNumber;
    }
    public String getBorrowStatus() {
        return borrowStatus;
    }
    public abstract void setDueDate();

    public String getDueDate() {
        return formattedDate;
    }
    public void setBorrowStatus() {
        borrowStatus = "on loan";
    }
    public void returnBorrowed() {
        borrowStatus = "available";
    }
    public void addRating(int rating) {
        ratingList.add(rating);
        reviewNumber++;
        int total = 0;
        for (int item : ratingList) {
            total = total + item;
        }
        averageRating = (float) total/reviewNumber;
    }
    public abstract ArrayList<String> getExtraDetails();
}
