package ua.com.alevel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniqueNameFinderTest {
    @Test
    void findUniqueName() {
        final UniqueNameFinder uniqueNameFinder = new UniqueNameFinder();
        final List<String> strings = List.of("Alex", "Sophia", "Vlad", "Alex", "Vlad", "Kate");
        final String uniqueName = uniqueNameFinder.findUniqueName(strings);
        assertEquals("Sophia", uniqueName);
    }


    @Test
    void NotFoundUniqueName() {
        final UniqueNameFinder uniqueNameFinder = new UniqueNameFinder();
        final List<String> strings = List.of("Alex", "Sophia", "Vlad", "Alex", "Vlad", "Sophia");
        final String uniqueName = uniqueNameFinder.findUniqueName(strings);
        assertEquals("", uniqueName);
    }
}