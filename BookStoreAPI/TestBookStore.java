import java.util.Scanner;

public class TestBookStore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookStoreAPI api = new BookStoreAPI();
        int choice;
        do {
            System.out.println("\n=== BOOK STORE ===");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Search Book");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    api.addBook(new Book(title, author));
                    System.out.println("Book added.");
                    break;
                case 2:
                    api.listBooks();
                    break;
                case 3:
                    System.out.print("Enter title: ");
                    String searchTitle = sc.nextLine();

                    Book result = api.searchByTitle(searchTitle);

                    if(result != null) {
                        System.out.println("Found: " +result.title +" by " +result.author);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while(choice != 4);

        sc.close();
    }
}