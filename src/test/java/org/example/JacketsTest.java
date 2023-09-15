package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacketsTest {
    private final String testFileName = "test_jackets.csv";
    private final String xmlTestFileName = "test_jackets.xml";


    @BeforeEach
    void setUp() {
        List<Jackets> jacketsList = new ArrayList<>();
        jacketsList.add(new Jackets("Brand2", "Red", 200));
        jacketsList.add(new Jackets("Brand5", "White", 500));
        jacketsList.add(new Jackets("Brand1", "Black", 100));
        jacketsList.add(new Jackets("Brand4", "Orange", 400));
        jacketsList.add(new Jackets("Brand3", "Yellow", 300));

        Jackets.serializeJacketCSV((ArrayList<Jackets>) jacketsList, testFileName);
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Path.of(testFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deserializedAndCompareList() {
        List<Jackets> originalJacketsList = new ArrayList<>();
        originalJacketsList.add(new Jackets("Brand1", "Black", 100));
        originalJacketsList.add(new Jackets("Brand2", "Red", 200));
        originalJacketsList.add(new Jackets("Brand3", "Yellow", 300));
        originalJacketsList.add(new Jackets("Brand4", "Orange", 400));
        originalJacketsList.add(new Jackets("Brand5", "White", 500));

        List<Jackets> deserializedJacketsList = Jackets.deserialize(testFileName);


        // Sorting both lists by price
        originalJacketsList.sort(Comparator.comparingInt(Jackets::getPrice));
        deserializedJacketsList.sort(Comparator.comparingInt(Jackets::getPrice));

        assertEquals(originalJacketsList, deserializedJacketsList, "Prices of both list of Jackets should be equal");
    }

    @Test
    void serializeJacketXMLAndDeserialize() {
        List<Jackets> originalJacketsList = new ArrayList<>();
        originalJacketsList.add(new Jackets("Brand1", "Black", 100));
        originalJacketsList.add(new Jackets("Brand2", "Red", 200));

        Jackets.serializeJacketXML((ArrayList<Jackets>) originalJacketsList, xmlTestFileName);
        List<Jackets> deserializedJacketsList = Jackets.deserializedJacketXML(xmlTestFileName);

        assertEquals(originalJacketsList, deserializedJacketsList, "Lists should be equal after XML serialization and deserialization");

//        // Cleaning up by deleting the test XML file
//        File xmlFile = new File(xmlTestFileName);
//        assertTrue(xmlFile.delete(), "Test XML file should be deleted");
    }
}
