package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;

public class LibraryTest {

    @Test
    public void testfindBooksNull() {
        Library lib = new Library();
        Assertions.assertNull(lib.findBooks("any"));
    }

    @Test
    public void testAddCollection() {
        Library library = new Library();
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        Book book1_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col1_1.addElement(book1_1_1);
        col.addElement(colDesign);
        col.addElement(col1_1);
        library.addCollection(col);
        Assertions.assertEquals(library.getCollections().size(), 1);
    }

    @Test
    public void testSaveLibraryToFile() {
        Library library = new Library();
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        Book book1_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col1_1.addElement(book1_1_1);
        col.addElement(colDesign);
        col.addElement(col1_1);
        library.addCollection(col);
        Collection col2 = new Collection("Music");
        Collection col2Design = new Collection("MusicDesign");
        Collection col2_1 = new Collection("Sub2.1");
        Book book2_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col2_1.addElement(book2_1_1);
        col2.addElement(col2Design);
        col2.addElement(col2_1);
        library.addCollection(col);
        Assertions.assertDoesNotThrow(() -> {
            library.saveLibraryToFile("testOut.txt");
        });
    }

    @Test
    public void testCreate() {
        Assertions.assertDoesNotThrow(() -> {
            Library library = new Library("testInput.txt");
        });
    }

    @Test
    public void testFindBook() {
        Library library = new Library();
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        Book book1_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        Collection col1_1_1 = new Collection("Sub1.1.1");
        Book book1_1_1_1 = new Book("Book1.1.1.1", Arrays.asList("Bo"));

        col1_1.addElement(book1_1_1);
        col1_1.addElement(col1_1_1);
        col1_1_1.addElement(book1_1_1_1);
        col.addElement(colDesign);
        col.addElement(col1_1);
        library.addCollection(col);

        Collection col2 = new Collection("Music");
        Collection col2Design = new Collection("MusicDesign");
        Collection col2_1 = new Collection("Sub1.1");
        Book book2_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col2.addElement(col2Design);
        col2_1.addElement(book2_1_1);
        col2.addElement(col2_1);

        Collection col2_2 = new Collection("Sub2.2");
        Book book2_1_2 = new Book("Book2.1.2", Arrays.asList("Billy", "Hans"));
        Book book2_1_3 = new Book("Book2.1.3", Arrays.asList("Lars"));
        col2_2.addElement(book2_1_2);
        col2_2.addElement(book2_1_3);
        col2.addElement(col2_2);
        library.addCollection(col2);

        Assertions.assertEquals(library.findBooks("Sub1.1"), new HashSet<>(Arrays.asList(book1_1_1, book2_1_1, book1_1_1_1)));
    }

    @Test
    public void testFindBooksByAuthor() {
        Library library = new Library();
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        Book book1_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col1_1.addElement(book1_1_1);
        col.addElement(colDesign);
        col.addElement(col1_1);
        library.addCollection(col);
        Collection col2 = new Collection("Music");
        Collection col2Design = new Collection("MusicDesign");
        Collection col2_1 = new Collection("Sub1.1");
        Book book2_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col2.addElement(col2Design);
        col2_1.addElement(book2_1_1);
        col2.addElement(col2_1);

        Collection col2_2 = new Collection("Sub2.2");
        Book book2_1_2 = new Book("Book2.1.2", Arrays.asList("Billy", "Hans"));
        Book book2_1_3 = new Book("Book2.1.3", Arrays.asList("Lars"));
        col2_2.addElement(book2_1_2);
        col2_2.addElement(book2_1_3);
        col2.addElement(col2_2);
        library.addCollection(col2);

        Assertions.assertEquals(library.findBooksByAuthor("Billy"), new HashSet<>(Arrays.asList(book1_1_1, book2_1_1, book2_1_2)));
    }

    @Test
    public void testFindBooksByAuthorNull() {
        Library lib = new Library();
        Assertions.assertEquals(lib.findBooksByAuthor("Billy"), new HashSet<>());
    }

    @Test
    public void testGetCollection() {
        Library library = new Library();
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        Book book1_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col1_1.addElement(book1_1_1);
        col.addElement(colDesign);
        col.addElement(col1_1);
        library.addCollection(col);
        Collection col2 = new Collection("Music");
        Collection col2Design = new Collection("MusicDesign");
        Collection col2_1 = new Collection("Sub1.1");
        Book book2_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col2.addElement(col2Design);
        col2_1.addElement(book2_1_1);
        col2.addElement(col2_1);

        Collection col2_2 = new Collection("Sub2.2");
        Book book2_1_2 = new Book("Book2.1.2", Arrays.asList("Billy", "Hans"));
        Book book2_1_3 = new Book("Book2.1.3", Arrays.asList("Lars"));
        col2_2.addElement(book2_1_2);
        col2_2.addElement(book2_1_3);
        col2.addElement(col2_2);
        library.addCollection(col2);

        Assertions.assertEquals(library.getCollections().size(), 2);
    }

    /*
     * TODO: add  test methods to achieve at least 80% statement coverage of Library.
     * Each test method should have appropriate JUnit assertions to test a single behavior
     * of the class. You should not add arbitrary code to test methods to just increase coverage.
     */
}
