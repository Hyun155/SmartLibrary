import java.util.Stack;

public class BorrowStack {
    private final Stack<Book> borrowedBooks = new Stack<>();

    public void borrow(Book book) {
        borrowedBooks.push(book);
    }

    public Book returnBook(long isbn) {
        Stack<Book> temporaryStack = new Stack<>();

        while (!borrowedBooks.isEmpty()) {
            Book currentBook = borrowedBooks.pop();

            if (currentBook.getIsbn() == isbn) {
                while (!temporaryStack.isEmpty()) {
                    borrowedBooks.push(temporaryStack.pop());
                }

                return currentBook;
            }

            temporaryStack.push(currentBook);
        }

        while (!temporaryStack.isEmpty()) {
            borrowedBooks.push(temporaryStack.pop());
        }

        return null;
    }

    public void show() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books have been borrowed yet.");
            return;
        }

        System.out.println("Borrowed Books (most recent first):");
        for (int index = borrowedBooks.size() - 1; index >= 0; index--) {
            System.out.println((borrowedBooks.size() - index) + ". " + borrowedBooks.get(index));
        }
    }

    public static void main(String[] args) {
        BorrowStack history = new BorrowStack();
        history.borrow(new Book(1L, "Clean Code", "Robert C. Martin"));
        history.borrow(new Book(2L, "Effective Java", "Joshua Bloch"));
        history.borrow(new Book(3L, "Design Patterns", "Erich Gamma"));

        history.show();
    }
}