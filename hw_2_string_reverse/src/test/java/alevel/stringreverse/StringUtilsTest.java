package alevel.stringreverse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class StringUtilsTest {

    @Test
    void reverse() {
        assumeTrue(StringUtils.reverse(null) == null);
        assertEquals("olleh dlrow", StringUtils.reverse("hello world"));
        assertEquals("olleh dlrow ", StringUtils.reverse("hello world "));
    }

    @Test
    void testReverseWithDest() {
        assertEquals("hello lrowd", StringUtils.reverse("hello world", "worl"));
        assertEquals("hello rowld rowld", StringUtils.reverse("hello world world","wor"));
    }

    @Test
    void testReverseWithIndexes() {
        assertEquals("helol owrld", StringUtils.reverse("hello world", 3, 7));
    }
}