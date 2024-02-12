package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ReversedSequenceTest {
    private CharSequence text;

    @BeforeEach
    void setText() {
        text = new ReversedSequence("abcdef");
    }

    @Test
    @DisplayName("should reverse the given string")
    void reverseTheString() {
        var actualReversed = text.toString();
        assertThat(actualReversed).isEqualTo("fedcba");
        assertThat(actualReversed.length()).isEqualTo(6);
    }

    @Test
    @DisplayName("should return char by index")
    void charAt() {
        var actualChar = text.charAt(1);
        assertThat(actualChar).isEqualTo('e');
    }

    @Test
    @DisplayName("should create subsequence with start inclusive and end exclusive")
    void subsequence() {
        var actualSubsequence = text.subSequence(1, 4);
        assertThat(actualSubsequence).isEqualTo("edc");
    }
}
