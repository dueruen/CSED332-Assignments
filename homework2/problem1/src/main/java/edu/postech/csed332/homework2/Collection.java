package edu.postech.csed332.homework2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Collection class represents a single collection, which contains
 * a name of the collection and also has a list of references to every
 * book and every subcollection in the collection. A collection can
 * also be exported to and imported from a JSON string representation.
 */
public final class Collection extends Element {
    private List<Element> elements;
    private String name;

    /**
     * Creates a new collection with the given name.
     *
     * @param name the name of the collection
     */
    public Collection(String name) {
        this.name = name;
        this.elements = new ArrayList<>();
    }

    /**
     * Restores a collection from its string representation in JSON
     *
     * @param stringRepr the string representation
     */
    public static Collection restoreCollection(String stringRepr) {
        JSONObject jsonCollection = new JSONObject(stringRepr);
        Collection newCollection = new Collection(jsonCollection.getString("name"));
        JSONArray elementsArray = jsonCollection.getJSONArray("elements");
        for (int i = 0; i < elementsArray.length() ; i++) {
            JSONObject e = elementsArray.getJSONObject(i);
            if (e.has("name")) {
                Collection c = restoreCollection(e.toString());
                newCollection.addElement(c);
            } else {
                Book b = new Book(e.toString());
                newCollection.addElement(b);
            }
        }
        return newCollection;
    }

    /**
     * Returns the JSON string representation of this collection, which
     * contains the name of this collection and all elements (books and
     * subcollections) contained in the collection.
     *
     * @return string representation of this collection
     */
    public String getStringRepresentation() {
        JSONObject jsonCollection = new JSONObject();
        jsonCollection.put("name", getName());

        JSONArray elementsArray = new JSONArray();
        for (Element e : elements) {
            if (e instanceof Collection) {
                JSONObject col = new JSONObject(((Collection) e).getStringRepresentation());
                elementsArray.put(col);
            } else {
                JSONObject book = new JSONObject(((Book) e).getStringRepresentation());
                elementsArray.put(book);
            }
        }
        jsonCollection.put("elements", elementsArray);
        return jsonCollection.toString();
    }

    /**
     * Adds an element to this collection, if the element has no parent
     * collection yet. Otherwise, this method returns false, without
     * actually adding the element to this collection.
     *
     * @param element the element to add
     * @return true on success, false on fail
     */
    public boolean addElement(Element element) {
        if (element.getParentCollection() != null) {
            return false;
        }
        element.setParentCollection(this);
        elements.add(element);
        return true;
    }

    /**
     * Deletes an element from the collection. Returns false if the
     * collection does not have this element. Hint: do not forget to
     * clear parentCollection of given element.
     *
     * @param element the element to remove
     * @return true on success, false on fail
     */
    public boolean deleteElement(Element element) {
        element.setParentCollection(null);
        if (!elements.remove(element)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the name of the collection.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of elements.
     *
     * @return the list of elements
     */
    public List<Element> getElements() {
        return elements;
    }
}
