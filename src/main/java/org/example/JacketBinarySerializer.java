package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JacketBinarySerializer {
    public static void serializeJacketBinary(List<Jackets> jacketsList, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(jacketsList);
            System.out.println("Jacket Info serialized to " + filename + " (Binary)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Jackets> deserializeJacketBinary(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            List<Jackets> jacketsList = (List<Jackets>) inputStream.readObject();
            System.out.println("Jacket is deserialized from " + filename + " (Binary)");
            return jacketsList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
