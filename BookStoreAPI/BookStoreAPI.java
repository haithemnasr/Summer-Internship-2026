import java.util.ArrayList;
import java.util.Arrays;

public class BookStoreAPI {

    ArrayList<Book> books = new ArrayList<>();
    public void addBook(Book book){
        books.add(book);
    }
    public void listBooks(){
        for (Book i : books){
            System.out.println("Book : " +i.title + ", "+i.author);
        }
    }
    public Book searchByTitle(String Title){
        for (Book i : books){
            if (i.title.equals(Title)){
                return i;
            }
        }
        return null;
    }
}