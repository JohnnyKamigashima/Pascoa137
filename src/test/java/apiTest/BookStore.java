package apiTest;

public class BookStore {

    public String userId;
    public ISBN[] collectionOfIsbns;

    public String isbn;

    public static class ISBN {

        public String isbn;

        ISBN (String isbn) {
            this.isbn = isbn;
        }
    }
}
