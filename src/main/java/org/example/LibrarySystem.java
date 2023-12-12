package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

public class LibrarySystem {
    ArrayList<LibraryItem> librarySystem = new ArrayList<>();

    public LibrarySystem() {
        try {
            //loads library text data into library system entries, then adds them to an ArrayList for use
            File myObj = new File("library.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                if (data.startsWith("Book")) {
                    String[] listLine = data.split(",");
                    LibraryItem newBook = new BookItem(listLine[0], parseInt(listLine[1]), listLine[2], parseInt(listLine[3]), listLine[4], parseInt(listLine[5]), 0.0F, 0, 28, "available");
                    librarySystem.add(newBook);
                }
                else if (data.startsWith("Movie")) {
                    String[] listLine = data.split(",");
                    LibraryItem newMovie = new MovieItem(listLine[0], parseInt(listLine[1]), listLine[2], parseInt(listLine[3]), listLine[4], 0.0F, 0, 7, "available");
                    librarySystem.add(newMovie);
                }
                else if (data.startsWith("Journal")) {
                    String[] listLine = data.split(",");
                    LibraryItem newJournal = new JournalItem(listLine[0], parseInt(listLine[1]), listLine[2], parseInt(listLine[3]), parseInt(listLine[4]), parseInt(listLine[5]), 0.0F, 0, 14, "available");
                    librarySystem.add(newJournal);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void displayInfo() {
        System.out.println("------------------------------------");
        System.out.println("Assignment 2 Semester 1 2022");
        System.out.println("Submitted by: Yap, Joel 21007112");
        System.out.println("------------------------------------");
        System.out.println("List of all items in the library:");
        for(LibraryItem item : librarySystem) {
            System.out.print("ID: " + item.getID() + "     " + "Type: " + item.getType());
            if (Objects.equals(item.getType(), "Book")) {
                System.out.print("      ");
            }
            else if (Objects.equals(item.getType(), "Movie")) {
                System.out.print("     ");
            }
            else if (Objects.equals(item.getType(), "Journal")) {
                System.out.print("   ");
            }
            System.out.print("Title: " + item.getTitle() + "\n");
        }
        System.out.println();
        userInput();
    }

    public void printContentsFull() {
        //prints all data including average rating and reviewer number
        for(LibraryItem item : librarySystem) {
            System.out.print("Average rating: " + item.getRating() + "        " + "Number of Reviewers: " + item.getReviewNumber() + "      ");
            System.out.print("ID: " + item.getID() + "     " + "Type: " + item.getType());
            if (Objects.equals(item.getType(), "Book")) {
                System.out.print("      ");
            }
            else if (Objects.equals(item.getType(), "Movie")) {
                System.out.print("     ");
            }
            else if (Objects.equals(item.getType(), "Journal")) {
                System.out.print("   ");
            }
            System.out.print("Title: " + item.getTitle() + "\n");
        }
        System.out.println();
        userInput();
    }

    public void userInput() {
        //receives user input and performs actions
        System.out.println("Enter 'q' to quit,");
        System.out.println("or enter 's' to sort (first by average rating and then by id) and display all items,");
        System.out.println("or enter 'i' to search by ID,");
        System.out.println("or enter any other key to search by phrase in title");
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.startsWith("q")) {
                    exit(0);
                } else if (line.startsWith("s")) {
                    this.sort();
                    printContentsFull();
                } else if (line.startsWith("i")) {
                    searchByID();
                } else {
                    searchByPhrase();
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public void searchByID() {
        System.out.println("Enter ID to start search, or enter 'b' to go back to choose search method");
        Scanner myObj = new Scanner(System.in);
        String line = myObj.nextLine();
        //checks for 'b' first
        if (Objects.equals(line, "b")) {
            userInput();
            return;
        }
        try {
            int ID = parseInt(line);
            //goes through list, finds a single matching item by ID and provides options, or restarts if no matches found or input was invalid
            for(LibraryItem item : librarySystem) {
                if (item.getID() == ID) {
                    System.out.print("ID: " + item.getID() + "     " + "Type: " + item.getType());
                    if (Objects.equals(item.getType(), "Book")) {
                        System.out.print("      ");
                    }
                    else if (Objects.equals(item.getType(), "Movie")) {
                        System.out.print("     ");
                    }
                    else if (Objects.equals(item.getType(), "Journal")) {
                        System.out.print("   ");
                    }
                    System.out.print("Title: " + item.getTitle() + "\n");
                    System.out.println();
                    System.out.println("Enter 'i' to search other item by ID, or enter any other key to select this item");
                    String c = myObj.next();
                    if (Objects.equals(c, "i")) {
                        searchByID();
                    } else {
                        selectItem(item);
                    }
                    break;
                }
            }
            System.out.println("No matching ID, enter 'i' to restart or enter any other key to choose search method");
            String check = myObj.next();
            if (Objects.equals(check, "i")) {
                searchByID();
            } else {
                userInput();
            }
            } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric ID or 'b'.");
            searchByID();
            }
    }

    private void searchByPhrase() {
        //checks the titles of all entries for a matching phrase
        ArrayList<LibraryItem> phraseList = new ArrayList<>();
        System.out.println("Enter phrase in title to start search, or enter 'b' to go back to choose search method");
        Scanner myObj = new Scanner(System.in);
        String phrase = myObj.next();
        int i = 1;
        boolean phraseFound = false;
        if (Objects.equals(phrase, "b")) {
            userInput();
        }
        for(LibraryItem item : librarySystem) {
            String temp = item.getTitle();
            Pattern pattern = Pattern.compile(phrase, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(temp);
            boolean matchFound = matcher.find();
            if (matchFound) {
                phraseFound = true;
                phraseList.add(item);
                System.out.println("* " + i + ":");
                i++;
                System.out.print("ID: " + item.getID() + "     " + "Type: " + item.getType());
                if (Objects.equals(item.getType(), "Book")) {
                    System.out.print("      ");
                }
                else if (Objects.equals(item.getType(), "Movie")) {
                    System.out.print("     ");
                }
                else if (Objects.equals(item.getType(), "Journal")) {
                    System.out.print("   ");
                }
                System.out.print("Title: " + item.getTitle() + "\n");
                System.out.println();
            }
        }
        if (!phraseFound) {
            System.out.println("Phrase " + phrase + " not found");
            searchByPhrase();
        }
        System.out.println("Enter item number to select item, or enter any other key to continue searching");
        String c = myObj.next();
        try {
            if (parseInt(c) >= 1 && parseInt(c) <= (i-1)) {
                int foundID = phraseList.get(parseInt(c)-1).getID();
                for(LibraryItem item2 : librarySystem) {
                    if (item2.getID() == foundID) {
                        selectItem(item2);
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            //if any other key
            searchByPhrase();
        }
    }

    private void selectItem(LibraryItem item) {
        //displays specific item details, borrows or returns and restarts
        System.out.println("Selected item is: ");
        System.out.println("Type: " + item.getType());
        System.out.println("Title: " + item.getTitle());
        System.out.println("ID: " + item.getID());
        System.out.println("Year: " + item.getYear());
        System.out.println("Average rating: " + item.getRating());
        System.out.println("Number of reviewers: " + item.getReviewNumber());
        System.out.println("Status: " + item.getBorrowStatus());
        ArrayList<String> extraDetails = item.getExtraDetails();
        if (Objects.equals(item.getBorrowStatus(), "on loan")) {
            System.out.println("Due date: " + item.getDueDate());
        }
        if (Objects.equals(item.getType(), "Book")) {
            System.out.println("Author: " + extraDetails.get(0));
            System.out.println("Number of pages: " + extraDetails.get(1));
            System.out.println("Max number of days for borrowing: 28");
        } else if (Objects.equals(item.getType(), "Movie")) {
            System.out.println("Director: " + extraDetails.get(0));
            System.out.println("Max number of days for borrowing: 7");
        } else if (Objects.equals(item.getType(), "Journal")) {
            System.out.println("Volume: " + extraDetails.get(0));
            System.out.println("Number: " + extraDetails.get(1));
            System.out.println("Max number of days for borrowing: 14");
        }
        System.out.println();
        if (Objects.equals(item.getBorrowStatus(), "available")) {
            System.out.println("Enter 'b' to borrow the item, enter 'a' to rate the item, or enter any other key to restart");
            Scanner scanner = null;
            try {
                scanner = new Scanner(System.in);

                String line;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.startsWith("b")) {
                        item.setBorrowStatus();
                        item.setDueDate();
                        System.out.println("This item's due date is " + item.getDueDate());
                        System.out.println();
                        selectItem(item);
                    } else if (line.startsWith("a")) {
                        System.out.println("Please enter your rating (0-10)");
                        int rating = 0;
                        while (rating <= 0 || rating > 10) {
                            try {
                                rating = Integer.parseInt(scanner.nextLine());
                                if (rating < 0 || rating > 10) {
                                    System.out.println("Error: Please enter a rating between 0-10");
                                }
                            } 
                            catch (NumberFormatException e) {
                                System.out.println("Error: Please enter a numeric rating between 0-10");
                            }
                        }
                        item.addRating(rating);
                        System.out.println("This item's new rating is " + item.getRating());
                        System.out.println();
                        selectItem(item);
                    } else {
                        System.out.println();
                        userInput();
                    }
                }
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        } else if (Objects.equals(item.getBorrowStatus(), "on loan")) {
            System.out.println("Enter 'r' to return the item, enter 'a' to rate the item, or enter any other key to restart");
            Scanner scanner = null;
            try {
                scanner = new Scanner(System.in);

                String line;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.startsWith("r")) {
                        item.returnBorrowed();
                        System.out.println("This item is returned");
                        System.out.println();
                        selectItem(item);
                    } else if (line.startsWith("a")) {
                        System.out.println("Please enter your rating (0-10)");
                        int rating = 0;
                        while (rating <= 0 || rating > 10) {
                            try {
                                rating = Integer.parseInt(scanner.nextLine());
                                if (rating < 0 || rating > 10) {
                                    System.out.println("Error: Please enter a rating between 0-10");
                                }
                            } 
                            catch (NumberFormatException e) {
                                System.out.println("Error: Please enter a numeric rating between 0-10");
                            }
                        }
                        item.addRating(rating);
                        System.out.println("This item's new average rating is " + item.getRating());
                        System.out.println();
                        selectItem(item);
                    } else {
                        System.out.println();
                        userInput();
                    }
                }
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }
    }

    private ArrayList<LibraryItem> getLibraryList() {
        return librarySystem;
    }
    public void sort() {
        librarySystem.sort(new LibraryComparator());
    }
}
