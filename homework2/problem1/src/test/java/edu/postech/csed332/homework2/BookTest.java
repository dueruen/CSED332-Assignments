package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BookTest {

    @Test
    public void testBookCreateIsTheSame() {
        Book book1 = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        String json = "{\"title\":\"Unit Testing\",\"authors\":[\"Name 1\",\"Name 2\"]}";

        Book book2 = new Book(json);
        Assertions.assertEquals(book1.getTitle(), book2.getTitle());
        Assertions.assertEquals(book1.getAuthors(), book2.getAuthors());
    }

    @Test
    public void testGetStringRepresentation() {
        Book book1 = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        String json = "{\"title\":\"Unit Testing\",\"authors\":[\"Name 1\",\"Name 2\"]}";
        Assertions.assertEquals(json, book1.getStringRepresentation());
    }

    @Test
    public void testGetContainingCollections() {
        Library l = new Library();
        Collection col1 = new Collection("Super1");
        Collection col1_1 = new Collection("Sub1.1");
        Collection col1_1_1 = new Collection("SubSub1.1.1");
        Book book1_1_1 = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        col1_1_1.addElement(book1_1_1);
        col1_1.addElement(col1_1_1);
        col1.addElement(col1_1);
        l.getCollections().add(col1);

        Assertions.assertEquals(book1_1_1.getContainingCollections(), Arrays.asList(col1_1_1, col1_1, col1));
    }

    @Test
    public void testGetStringRepresentationFail() {
        Book book1 = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        Assertions.assertNotEquals(book1.getStringRepresentation(), null);
    }

    @Test
    public void testGetTitle() {
        Book book = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        Assertions.assertEquals(book.getTitle(), "Unit Testing");
    }

    @Test
    public void testGetTitleShouldFail() {
        Book book = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        Assertions.assertNotEquals(book.getTitle(), "Unit");
    }

    @Test
    public void testGetAuthor() {
        Book book = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        Assertions.assertEquals(book.getAuthors(), Arrays.asList("Name 1", "Name 2"));
    }

    @Test
    public void testGetAuthorFail() {
        Book book = new Book("Unit Testing", Arrays.asList("Name 1", "Name 2"));
        Assertions.assertNotEquals(book.getAuthors(), Arrays.asList("Name 1"));
    }
}
