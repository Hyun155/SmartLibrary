import java.util.Scanner;
import java.util.InputMismatchException;

public class SmartLibrary implements LibraryADT {
    private final BookBST catalogue;
    private final BorrowStack history;

    public SmartLibrary() {
        this.catalogue = new BookBST();
        this.history = new BorrowStack();
    }

    @Override
    public void addBook(long isbn, String title, String author) {
        boolean success = catalogue.insert(isbn, title, author);
        if (success) {
            System.out.println("Success: Book '" + title + "' added to the library catalogue.");
        } else {
            System.out.println("Error: A book with ISBN " + isbn + " already exists.");
        }
    }

    @Override
    public void searchBook(long isbn) {
        Book book = catalogue.search(isbn);
        if (book != null) {
            System.out.println("Found: " + book);
        } else {
            System.out.println("Not Found: Book with ISBN " + isbn + " does not exist.");
        }
    }

    @Override
    public void borrowBook(long isbn) {
        // Step 1: Search the BST for the book record
        Book bookToBorrow = catalogue.search(isbn);
        
        if (bookToBorrow != null) {
            // Create a clean detached copy of the book metadata for history tracking
            Book historyRecord = new Book(bookToBorrow.getIsbn(), bookToBorrow.getTitle(), bookToBorrow.getAuthor());
            
            // Step 2: Add it to the LIFO borrowing history stack
            history.borrow(historyRecord);
            
            // Step 3: Remove it from the available catalogue
            catalogue.delete(isbn);
            
            System.out.println("Success: You have borrowed '" + historyRecord.getTitle() + "'.");
        } else {
            System.out.println("Error: Book not found in available catalogue.");
        }
    }

    @Override
    public void viewLatestHistory() {
        history.show();
    }

    public void listAllBooks() {
        catalogue.printAllBooks();
    }

    public void returnBook(long isbn) {
        Book returnedBook = history.returnBook(isbn);

        if (returnedBook == null) {
            System.out.println("Error: This book was not found in borrowing history.");
            return;
        }

        boolean restored = catalogue.insert(returnedBook.getIsbn(), returnedBook.getTitle(), returnedBook.getAuthor());

        if (restored) {
            System.out.println("Success: You have returned '" + returnedBook.getTitle() + "'.");
        } else {
            System.out.println("Error: The returned book could not be restored to the catalogue.");
        }
    }

    // --- CONSOLE INTERFACE ENGINE ---
    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Choice: ");
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Clear the buffer newline

                if (choice == 7) {
                    System.out.println("Thank you for using Smart Library System. Goodbye!");
                    break;
                }
                handleChoice(choice, sc);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                sc.nextLine(); // Clear invalid input token
            }
        }
        sc.close();
    }

    private void printMenu() {
        System.out.println("\n--- SmartLibrary Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search (BST)");
        System.out.println("3. Borrow (Stack)");
        System.out.println("4. History");
        System.out.println("5. List All Books");
        System.out.println("6. Return Book");
        System.out.println("7. Exit");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                try {
                    System.out.print("Enter ISBN: ");
                    long isbn = sc.nextLong();
                    sc.nextLine(); // Clear buffer
                    
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    
                    addBook(isbn, title, author);
                } catch (InputMismatchException e) {
                    System.out.println("Validation Error: ISBN must be a numeric value.");
                    sc.nextLine(); // Reset buffer
                }
                break;

            case 2:
                try {
                    System.out.print("Enter ISBN to search: ");
                    long searchIsbn = sc.nextLong();
                    searchBook(searchIsbn);
                } catch (InputMismatchException e) {
                    System.out.println("Validation Error: ISBN must be a numeric value.");
                    sc.nextLine();
                }
                break;

            case 3:
                try {
                    System.out.print("Enter ISBN to borrow: ");
                    long borrowIsbn = sc.nextLong();
                    borrowBook(borrowIsbn);
                } catch (InputMismatchException e) {
                    System.out.println("Validation Error: ISBN must be a numeric value.");
                    sc.nextLine();
                }
                break;

            case 4:
                viewLatestHistory();
                break;

            case 5:
                listAllBooks();
                break;

            case 6:
                try {
                    System.out.print("Enter ISBN to return: ");
                    long returnIsbn = sc.nextLong();
                    returnBook(returnIsbn);
                } catch (InputMismatchException e) {
                    System.out.println("Validation Error: ISBN must be a numeric value.");
                    sc.nextLine();
                }
                break;

            default:
                System.out.println("Invalid option. Please choose a valid menu item (1-7).");
        }
    }

    public static void main(String[] args) {
        // Initializes and spins up the console engine
        new SmartLibrary().runMenu();
    }
}