package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CollectionTest {

    @Test
    public void testGetName() {
        Collection col = new Collection("Software");
        Assertions.assertEquals(col.getName(), "Software");
    }

    @Test
    public void testGetElements() {
        Collection col = new Collection("Software");
        Collection col1_1 = new Collection("Sub1.1");
        Collection col1_2 = new Collection("Sub1.2");
        col.addElement(col1_1);
        col.addElement(col1_2);
        Assertions.assertEquals(col.getElements(), Arrays.asList(col1_1, col1_2));
    }

    @Test
    public void testDeleteElement() {
        Collection col = new Collection("Software");
        Collection col1_1 = new Collection("Sub1.1");
        Collection col1_2 = new Collection("Sub1.2");
        col.addElement(col1_1);
        col.addElement(col1_2);
        Assertions.assertTrue(col.deleteElement(col1_2));
        Assertions.assertEquals(col.getElements(), Arrays.asList(col1_1));
    }

    @Test
    public void testDeleteElementDoNotExist() {
        Collection col = new Collection("Software");
        Collection col1_1 = new Collection("Sub1.1");
        Collection col1_2 = new Collection("Sub1.2");
        col.addElement(col1_1);
        Assertions.assertFalse(col.deleteElement(col1_2));
        Assertions.assertEquals(col.getElements(), Arrays.asList(col1_1));
    }

    @Test
    public void testAddElement() {
        Collection col = new Collection("Software");
        Collection col1_1 = new Collection("Sub1.1");

        Assertions.assertTrue(col.addElement(col1_1));
        Assertions.assertEquals(col.getElements(), Arrays.asList(col1_1));
        Assertions.assertEquals(col1_1.getParentCollection(), col);
    }

    @Test
    public void testAddElementHasParent() {
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        col1_1.setParentCollection(colDesign);

        Assertions.assertFalse(col.addElement(col1_1));
        Assertions.assertEquals(col.getElements().size(), 0);
    }

    @Test
    public void testGetStringRepresentation() {
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        Book book1_1_1 = new Book("Book1.1.1", Arrays.asList("Billy"));
        col1_1.addElement(book1_1_1);
        col.addElement(colDesign);
        col.addElement(col1_1);

        String json = "{\"elements\":[{\"elements\":[],\"name\":\"Design\"},{\"elements\":[{\"title\":\"Book1.1.1\",\"authors\":[\"Billy\"]}],\"name\":\"Sub1.1\"}],\"name\":\"Software\"}";

        Assertions.assertEquals(col.getStringRepresentation(), json);
    }

    @Test
    public void testRestoreCollection() {
        Collection col = new Collection("Software");
        Collection colDesign = new Collection("Design");
        Collection col1_1 = new Collection("Sub1.1");
        colDesign.addElement(col1_1);

        Assertions.assertFalse(col.addElement(col1_1));
        Assertions.assertEquals(col.getElements().size(), 0);
    }

    /*
     * TODO: add  test methods to achieve at least 80% statement coverage of Collection.
     * Each test method should have appropriate JUnit assertions to test a single behavior
     * of the class. You should not add arbitrary code to test methods to just increase coverage.
     */
}
