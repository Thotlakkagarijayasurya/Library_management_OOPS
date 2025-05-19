import Services.BookManager;
import Services.OrderManager;
import Services.UserManager;
import entities.Role;
import entities.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        BookManager bookManager = new BookManager();
        OrderManager orderManager = new OrderManager();

        while (true) {
            if (userManager.getCurrentUser() == null) {
                System.out.println("\n========= Library System =========");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt(); scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Role (STUDENT / LIBRARIAN): ");
                        Role role = Role.valueOf(scanner.nextLine().toUpperCase());
                        userManager.register(name, role);
                    }
                    case 2 -> {
                        System.out.print("User ID: ");
                        int userId = scanner.nextInt(); scanner.nextLine();
                        boolean success = userManager.login(userId);
                        if (!success) {
                            System.out.println("Login failed. Try again.");
                        }
                    }
                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } else {
                User currentUser = userManager.getCurrentUser();
                System.out.println("\nWelcome, " + currentUser.getName() + " (" + currentUser.getRole() + ")");

                if (currentUser.getRole() == Role.STUDENT) {
                    System.out.println("""
                            1. View All Books
                            2. Search Book by Title
                            3. Search Book by Author
                            4. Borrow Book
                            5. Return Book
                            6. View My Orders
                            7. Logout
                            """);
                } else {
                    System.out.println("""
                            1. Add Book
                            2. Search Book by Title
                            3. Search Book by Author
                            4. View All Books
                            5. Logout
                            """);
                }

                System.out.print("Choose an option: ");
                int option = scanner.nextInt(); scanner.nextLine();

                // ========== STUDENT OPTIONS ==========
                if (currentUser.getRole() == Role.STUDENT) {
                    switch (option) {
                        case 1 -> bookManager.fetchAllBooks().forEach(System.out::println);
                        case 2 -> {
                            System.out.print("Enter title keyword: ");
                            String title = scanner.nextLine();
                            bookManager.searchBooksByTitle(title).forEach(System.out::println);
                        }
                        case 3 -> {
                            System.out.print("Enter author keyword: ");
                            String author = scanner.nextLine();
                            bookManager.searchBooksByAuthor(author).forEach(System.out::println);
                        }
                        case 4 -> {
                            System.out.print("Enter Book ID to borrow: ");
                            int bookId = scanner.nextInt(); scanner.nextLine();
                            boolean success = bookManager.borrowBook(bookId);
                            if (success) orderManager.createOrder(currentUser.getUserId(), bookId);
                            else System.out.println("Borrow failed. Book not available.");
                        }
                        case 5 -> {
                            System.out.print("Enter Book ID to return: ");
                            int bookId = scanner.nextInt(); scanner.nextLine();

                            // Find user's open order for this book
                            orderManager.fetchUserSpecificOrders(currentUser.getUserId())
                                    .stream()
                                    .filter(o -> o.getBookId() == bookId && o.getUserId() == currentUser.getUserId())
                                    .findFirst()
                                    .ifPresent(o -> {
                                        orderManager.closeOrder(o.getOrderId());
                                        boolean success = bookManager.returnBook(bookId);
                                        if (!success){
                                            System.out.println("Return failed.");
                                        }
                                    });

                        }
                        case 6 -> orderManager.fetchUserSpecificOrders(currentUser.getUserId()).forEach(System.out::println);
                        case 7 -> userManager.logout();
                        default -> System.out.println("Invalid option.");
                    }
                }

                // ========== LIBRARIAN OPTIONS ==========
                else {
                    switch (option) {
                        case 1 -> {
                            System.out.print("Title: ");
                            String title = scanner.nextLine();
                            System.out.print("Description: ");
                            String desc = scanner.nextLine();
                            System.out.print("Author: ");
                            String author = scanner.nextLine();
                            System.out.print("Copies: ");
                            int copies = scanner.nextInt(); scanner.nextLine();
                            bookManager.addBook(title, desc, author, copies);
                        }
                        case 2 -> {
                            System.out.print("Enter title keyword: ");
                            String title = scanner.nextLine();
                            bookManager.searchBooksByTitle(title).forEach(System.out::println);
                        }
                        case 3 -> {
                            System.out.print("Enter author keyword: ");
                            String author = scanner.nextLine();
                            bookManager.searchBooksByAuthor(author).forEach(System.out::println);
                        }
                        case 4 -> bookManager.fetchAllBooks().forEach(System.out::println);
                        case 5 -> userManager.logout();
                        default -> System.out.println("Invalid option.");
                    }
                }
            }
        }
    }
}
