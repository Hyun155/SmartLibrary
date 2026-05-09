import Book;

public class BookBST {
    private Book root;

    public BookBST() {
        root = null;
    }

    /*
     * Inserts a new book into the BST.
     * Smaller ISBN values go to the left.
     * Larger ISBN values go to the right.
     */
    public boolean insert(long isbn, String title, String author) {
        if (search(isbn) != null) {
            return false;
        }

        root = insertRecursive(root, isbn, title, author);
        return true;
    }

    private Book insertRecursive(Book current, long isbn, String title, String author) {
        if (current == null) {
            return new Book(isbn, title, author);
        }

        if (isbn < current.getIsbn()) {
            current.setLeft(insertRecursive(current.getLeft(), isbn, title, author));
        } else if (isbn > current.getIsbn()) {
            current.setRight(insertRecursive(current.getRight(), isbn, title, author));
        }

        return current;
    }

    /*
     * SEARCH LOGIC EXPERT SECTION
     *
     * This method starts the recursive search from the root node.
     * It returns the Book object if the ISBN exists.
     * It returns null if the ISBN is not found.
     */
    public Book search(long isbn) {
        return searchRecursive(root, isbn);
    }

    /*
     * Recursive search method for BST.
     *
     * If current is null, the book does not exist.
     * If ISBN matches, return the current book.
     * If target ISBN is smaller, search the left subtree.
     * If target ISBN is larger, search the right subtree.
     */
    private Book searchRecursive(Book current, long isbn) {
        if (current == null) {
            return null;
        }

        if (current.getIsbn() == isbn) {
            return current;
        }

        if (isbn < current.getIsbn()) {
            return searchRecursive(current.getLeft(), isbn);
        } else {
            return searchRecursive(current.getRight(), isbn);
        }
    }

    /*
     * Deletes a book from the BST after borrowing.
     * This supports the borrowBook logic in SmartLibrary.
     */
    public boolean delete(long isbn) {
        if (search(isbn) == null) {
            return false;
        }

        root = deleteRecursive(root, isbn);
        return true;
    }

    private Book deleteRecursive(Book current, long isbn) {
        if (current == null) {
            return null;
        }

        if (isbn < current.getIsbn()) {
            current.setLeft(deleteRecursive(current.getLeft(), isbn));
        } else if (isbn > current.getIsbn()) {
            current.setRight(deleteRecursive(current.getRight(), isbn));
        } else {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            Book smallestBook = findSmallestBook(current.getRight());

            Book replacement = new Book(
                    smallestBook.getIsbn(),
                    smallestBook.getTitle(),
                    smallestBook.getAuthor()
            );

            replacement.setLeft(current.getLeft());
            replacement.setRight(deleteRecursive(current.getRight(), smallestBook.getIsbn()));

            return replacement;
        }

        return current;
    }

    private Book findSmallestBook(Book current) {
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }
}