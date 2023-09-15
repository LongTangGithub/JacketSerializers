package org.example;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {

        ArrayList<Jackets> jacketsList = new ArrayList<>();
        Jackets myJacket = new Jackets("North Face", "Black", 299);
        jacketsList.add(myJacket);

        String filename = "jackets.csv";

        Jackets.serializeJacketCSV(jacketsList, filename);
        ArrayList<Jackets> deserializedJacketsList = Jackets.deserialize(filename);

        for (Jackets deserializedJacket : deserializedJacketsList) {
            deserializedJacket.checkEquality(myJacket);
            deserializedJacket.prettyPrint("Deserialized Jacket");
        }
    }
}