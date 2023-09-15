package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacketBinaryTest {
    private final String binaryTestFileName = "test_jackets.ser";

    @Test
    void serializeJacketBinaryAndDeserialize() {
        List<Jackets> originalJacketsList = new ArrayList<>();
        originalJacketsList.add(new Jackets("Brand1", "Black", 100));
        originalJacketsList.add(new Jackets("Brand2", "Red", 200));

        JacketBinarySerializer.serializeJacketBinary(originalJacketsList, binaryTestFileName);
        List<Jackets> deserializedJacketsList = JacketBinarySerializer.deserializeJacketBinary(binaryTestFileName);

        assertEquals(originalJacketsList, deserializedJacketsList, "Lists should be equal after binary serialization and deserialization");
    }
}
