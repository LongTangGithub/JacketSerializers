package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Personal Note:
 * These two methods below allow us to keep a list of the Jacket's objects to a binary file and later to be retrieved
 * for deserializing the binary data.Binary serialization is a way to store object data in a compact and binary format,
 * making it convenient for saving and loading complex object structures.
 *
 */

public class JacketBinarySerializer {


    /**
     * This method serializes the jacketList which contains a list of Jackets objects into a binary format and writes
     * it to a file. The ObjectInputStream allows you to perform the serialization and once it completes, a message will be
     * printed out indicating the jacket information
     *
     * @param jacketsList
     * @param filename
     */
    public static void serializeJacketBinary(List<Jackets> jacketsList, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(jacketsList);
            System.out.println("Jacket Info serialized to " + filename + " (Binary)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The deserializeJacketBinary method reads and deserializes the Jackets objects from a binary file.
     * The ObjectInputStream allows you to perform the deserialization, and the deserialized Jacket objects are returned as a list.
     * If there's an error, it will catch an exception, print t the error message and returns an empty ArrayList.
     *
     * @param filename
     * @return
     */
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
