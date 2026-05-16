public interface LibraryADT {
    void addBook(long isbn, String title, String author);
    void searchBook(long isbn);
    void borrowBook(long isbn);
    void viewLatestHistory();
}