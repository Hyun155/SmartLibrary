import java.util.Stack;

class Book {
    private final String title;
    private final String author;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

public class BorrowStack {
    private final Stack<Book> borrowedBooks = new Stack<>();

    public void borrow(Book book) {
        borrowedBooks.push(book);
    }

    public void show() {
        System.out.println("Borrowed Books (most recent first):");
        for (int index = borrowedBooks.size() - 1; index >= 0; index--) {
            System.out.println((borrowedBooks.size() - index) + ". " + borrowedBooks.get(index));
        }
    }

    public static void main(String[] args) {
        BorrowStack history = new BorrowStack();
        history.borrow(new Book("Clean Code", "Robert C. Martin"));
        history.borrow(new Book("Effective Java", "Joshua Bloch"));
        history.borrow(new Book("Design Patterns", "Erich Gamma"));

        history.show();
    }
}