package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>>  listOfRelatedBooks = new ArrayList<>();
        books.forEach(book -> {
            if (book.entrySet().containsAll(where.entrySet())) {
                listOfRelatedBooks.add(book);
            }
        });
        return listOfRelatedBooks;
    }
}
//END
