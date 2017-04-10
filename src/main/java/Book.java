/**
 * Created by Lukasz Kolacz on 10.04.2017.
 */
public class Book {

    private String bookName;
    private String author;
    private int pages;

    public Book(String bookName, String author, int pages) {
        this.bookName = bookName;
        this.author = author;
        this.pages = pages;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
