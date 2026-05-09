# Smart Library System

## Project Overview

Smart Library System is a Java console-based application designed for a university library. The system allows a librarian or student to add books, search books by ISBN, borrow books, and view borrowing history.

The project demonstrates how different data structures are used for different access patterns:
- A Binary Search Tree (BST) is used to store and search books efficiently by ISBN.
- A Stack is used to store borrowing history, where the most recently borrowed book is shown first.

## Main Features

1. Add Book  
   Allows the user to enter a book's ISBN, title, and author.

2. Search Book  
   Searches for a book by ISBN using recursive Binary Search Tree logic.

3. Borrow Book  
   Searches for the book in the catalogue, moves it to borrowing history, and removes it from the available catalogue.

4. View Borrowing History  
   Displays borrowed books in Last-In-First-Out (LIFO) order.

5. Exit  
   Terminates the program.

## Data Structures Used

### Binary Search Tree

The book catalogue is implemented using a Binary Search Tree. Each book is stored as a node, and the ISBN is used as the search key.

Books with smaller ISBN values are stored on the left subtree, while books with larger ISBN values are stored on the right subtree. This allows efficient searching with expected O(log n) time complexity when the tree is balanced.

### Stack

The borrowing history is implemented using a Stack. When a book is borrowed, it is pushed onto the stack. When the history is displayed, the most recently borrowed book appears first.

This follows the LIFO principle, which means Last-In, First-Out.

## My Contribution: Search Logic Expert

My role in this project is Search Logic Expert.

I implemented the recursive search logic in `BookBST.java`. The search starts from the root node of the BST and compares the target ISBN with the current node's ISBN.

The logic works as follows:

```text
If the current node is null:
    return null

If the current node's ISBN matches the target ISBN:
    return the current book

If the target ISBN is smaller:
    search the left subtree

If the target ISBN is larger:
    search the right subtree
