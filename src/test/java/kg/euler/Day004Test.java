package kg.euler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class Day004Test {
    @Test
    void testIsMirror() {
        assertFalse(Day0004.isMirror(941490));
        assertFalse(Day0004.isMirror(40));
    }
}
