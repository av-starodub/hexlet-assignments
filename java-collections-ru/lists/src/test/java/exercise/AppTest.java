package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    @Test
    void testScrabble1() throws Exception {
        boolean result = App.scrabble("rkqodlw", "woRld");
        assertThat(result).isTrue();
    }

    @Test
    void testScrabble2() throws Exception {
        boolean result = App.scrabble("begsdhhtsexoult", "Hexlet");
        assertThat(result).isTrue();
    }

    @Test
    void testScrabble3() throws Exception {
        boolean result = App.scrabble("thlxertwq", "hexlet");
        assertThat(result).isFalse();
    }

    @Test
    void testScrabble4() throws Exception {
        boolean result = App.scrabble("jvayu", "java");
        assertThat(result).isFalse();
    }

    @Test
    void testScrabble5() throws Exception {
        boolean result = App.scrabble("", "java");
        assertThat(result).isFalse();
    }
}
