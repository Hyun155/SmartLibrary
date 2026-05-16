public class Book {
    private long isbn;
    private String title;
    private String author;
    private Book left;
    private Book right;

    public Book(long isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
    public long getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public Book getLeft() { return left; }
    public void setLeft(Book left) { this.left = left; }

    public Book getRight() { return right; }
    public void setRight(Book right) { this.right = right; }

    @Override
    public String toString() {
        return "[ISBN: " + isbn + "] " + title + " by " + author;
    }
}
