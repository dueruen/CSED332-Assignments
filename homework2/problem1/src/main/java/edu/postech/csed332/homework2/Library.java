package edu.postech.csed332.homework2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * A container class for all collections (that eventually contain all
 * books). A library can be exported to or imported from a JSON file.
 */
public final class Library {
    private List<Collection> collections;

    /**
     * Builds a new, empty library.
     */
    public Library() {
        collections = new ArrayList<>();
    }

    /**
     * Builds a new library and restores its contents from a file.
     *
     * @param fileName the file from where to restore the library.
     */
    public Library(String fileName) {
        collections = new ArrayList<>();
        try {
            String content = Files.readString(Paths.get(fileName));
            JSONObject library = new JSONObject(content);
            JSONArray collectionArray = library.getJSONArray("collections");
            for (int i = 0; i < collectionArray.length(); i++) {
                JSONObject col = collectionArray.getJSONObject(i);
                collections.add(Collection.restoreCollection(col.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the contents of the library to the given file.
     *
     * @param fileName the file where to save the library
     */
    public void saveLibraryToFile(String fileName) {
        JSONObject library = new JSONObject();
        JSONArray colArray = new JSONArray();
        for (Collection c : collections) {
            colArray.put(new JSONObject(c.getStringRepresentation()));
        }
        library.put("collections", colArray);
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(library.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the set of all books that belong to the collections
     * of a given name. Note that different collections may have the
     * same name. Return null if there is no collection of the
     * given name, and the empty set of there are such collections but
     * all of them are empty. For example, suppose that
     * - "Computer Science" is a top collection.
     * - "Operating Systems" is a collection under "Computer Science".
     * - "Linux Kernel" is a book under "Operating System".
     * - "Software Engineering" is a collection under "Computer Science".
     * - "Software Design Methods" is a bool under "Software Engineering".
     * Then, the findBooks method for "Computer Science" returns the set
     * of two books "Linux Kernel" and "Software Design Methods".
     *
     * @param collection a collection name
     * @return a set of books
     */
    public Set<Book> findBooks(String collection) {
        Set<Book> books = new HashSet<>();
        for (Collection c : collections) {
            books.addAll(recFindBook(c, collection, new HashSet<>(), false));
        }
        if (books.size() == 0) {
            return null;
        }
        return books;
    }

    private Set<Book> recFindBook(Collection c, String collectionName, Set<Book> books, boolean found) {
        for (Element e : c.getElements()) {
            if (c.getName().equals(collectionName)
                    && e instanceof Book) {
                found = true;
                books.add((Book)e);
            } else if (found && e instanceof Book ) {
                books.add((Book)e);
            } else if (e instanceof Collection){
                books.addAll(recFindBook((Collection) e, collectionName, books, found));
            }
        }
        return books;
    }

    /**
     * Return the set of all books written by a given author in this
     * collection (including the sub-collections). Return the empty
     * set if there is no book written by the author. Note that a book
     * may involve multiple authors.
     *
     * @param author an author
     * @return Return the set of books written by the given author
     */
    public Set<Book> findBooksByAuthor(String author) {
        Set<Book> books = new HashSet<>();
        for (Collection c : collections) {
            recFindBookAuthor(c, author, books);
        }
        return books;
    }

    private Set<Book> recFindBookAuthor(Collection c, String author, Set<Book> books) {
        for (Element e : c.getElements()) {
            if ( e instanceof Book) {
                for (String a : ((Book) e).getAuthors()) {
                    if (a.equals(author)) {
                        books.add((Book)e);
                        break;
                    }
                }
            } else {
                recFindBookAuthor((Collection) e, author, books);
            }
        }
        return books;
    }

    /**
     * Returns the collections contained in the library.
     *
     * @return library contained elements
     */
    public List<Collection> getCollections() {
        return collections;
    }

    public void addCollection(Collection c) {
        collections.add(c);
    }
}
