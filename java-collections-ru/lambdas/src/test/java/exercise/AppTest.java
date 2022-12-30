package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
public class AppTest {
    @Test
    public void enlargeSymmetricalArrayImage() {
        String[][] imageSource = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };
        assertThat(App.enlargeArrayImageStream(imageSource)).isDeepEqualTo(expected);
        assertThat(App.enlargeArrayImageCircle(imageSource)).isDeepEqualTo(expected);
    }

    @Test
    public void enlargeOneElementArrayImage() {
        String[][] imageSource = {
                {"*"},
        };
        String[][] expected = {
                {"*", "*"},
                {"*", "*"},
        };
        assertThat(App.enlargeArrayImageStream(imageSource)).isDeepEqualTo(expected);
        assertThat(App.enlargeArrayImageCircle(imageSource)).isDeepEqualTo(expected);
    }

    @Test
    public void enlargeAsymmetricalArrayImage3() {
        String[][] imageSource = {
                {"*", " ", " "},
        };
        String[][] expected = {
                {"*", "*", " ", " ", " ", " "},
                {"*", "*", " ", " ", " ", " "},

        };
        assertThat(App.enlargeArrayImageStream(imageSource)).isDeepEqualTo(expected);
        assertThat(App.enlargeArrayImageCircle(imageSource)).isDeepEqualTo(expected);
    }

    @Test
    public void checkEmptyArrayAsArgument() {
        String[][] imageSource = {};
        String[][] expected = {};
        assertThat(App.enlargeArrayImageStream(imageSource)).isDeepEqualTo(expected);
        assertThat(App.enlargeArrayImageCircle(imageSource)).isDeepEqualTo(expected);
    }
}
// END
