package org.example;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;


public class Jackets implements Serializable {
    private final String brand;
    private final String color;
    private final int price;

    public Jackets(String brand, String color, int price) {
        this.brand = brand;
        this.color = color;
        this.price = price;

    }

    public String getBrand() {
        return brand;
    }

    public String getColor(){
        return color;
    }

    public int getPrice(){
        return price;
    }
    public static void serializeJacketCSV(ArrayList<Jackets> jacketsList, String filename) {
        try {
            Path filePath = Paths.get(filename);
            StringBuilder serialJacketInfo = new StringBuilder();
            serialJacketInfo.append("Brand,Color,Price\n");

            for (Jackets jacket : jacketsList) {
                serialJacketInfo.append(jacket.getBrand() + "," + jacket.getColor() + "," + jacket.getPrice() + "\n");
            }

            Files.writeString(filePath, serialJacketInfo.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("Jacket Info serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Jackets> deserialize(String filename) {
        ArrayList<Jackets> jacketsList = new ArrayList<>();
        try {
            Path filePath = Paths.get(filename);
            String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);

            String[] lines = fileContent.split("\\r?\\n");

            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split(",");
                if (values.length == 3) {
                    String brand = values[0].trim();
                    String color = values[1].trim();
                    int price = Integer.parseInt(values[2].trim());

                    jacketsList.add(new Jackets(brand, color, price));
                }
            }

            System.out.println("Jackets are deserialized from " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid price value in the CSV file. Check the formatting.");
            e.printStackTrace();
        }

        return jacketsList;
    }

    public static void serializeJacketXML(ArrayList<Jackets> jacketsList, String filename) {
        XStream xStream = createXStream();
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            xStream.toXML(jacketsList, fos);
            System.out.println("Jacket Info serialized to " + filename + " (XML)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Jackets> deserializedJacketXML(String filename) {
        XStream xStream = createXStream();
        try (FileInputStream fis = new FileInputStream(filename)) {
            ArrayList<Jackets> jacketsList = (ArrayList<Jackets>) xStream.fromXML(fis);
            System.out.println("Jackets are deserialized from " + filename + " (XML)");
            return jacketsList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public void prettyPrint(String jacketType){
        System.out.println("Jacket Information: (" + jacketType + "): ");
        System.out.println("Brand: " + brand);
        System.out.println("Color: " + color);
        System.out.println("Price: " + price);
        System.out.println();
    }

    public boolean checkEquality(Jackets deserializedJacket){
        boolean isEqual = this.equals(deserializedJacket);
        System.out.println("The original and deserialized jackets are " + (isEqual ? "equal." : "not equal."));
        return isEqual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jackets jackets = (Jackets) o;
        return price == jackets.price && Objects.equals(brand, jackets.brand) && Objects.equals(color, jackets.color);
    }

//    @Override
//    public int compareTo(Jackets otherJacket) {
//        return Integer.compare(this.price, otherJacket.price);
//    }


    @Override
    public int hashCode() {
        return Objects.hash(brand, color, price);
    }

    /**
     * The allowTypesByWildCard allows you to specify packages/classes that should be allowed for deserialization
     * @return
     */
    private static XStream createXStream() {
        XStream xStream = new XStream(new DomDriver());
        // Allow the Jacket class to be deserialized
        xStream.allowTypesByWildcard(new String[] {
                "org.example.**"
        });
        return xStream;
    }

}
