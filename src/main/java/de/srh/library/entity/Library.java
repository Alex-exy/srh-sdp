package de.srh.library.entity;

public class Library {
    private int libraryId;
    private String libraryName;

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", libraryName='" + libraryName + '\'' +
                '}';
    }
}
