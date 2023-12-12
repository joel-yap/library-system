package org.example;

import java.util.Comparator;

public class LibraryComparator implements Comparator<LibraryItem> {
    @Override
    public int compare (LibraryItem l1, LibraryItem l2) {
        int libComparison = Float.compare(l2.getRating(), l1.getRating());
        if (libComparison != 0) {
            return libComparison;
        }
        return Float.compare(l1.getID(), l2.getID());
    }

}
