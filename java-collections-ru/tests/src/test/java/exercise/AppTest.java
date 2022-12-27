package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AppTest {
    // BEGIN
    @Test
    void checkWithCorrectParameters() {
        List<Integer> result = App.take(Arrays.asList(1, 2, 3, 4), 2);
        assertThat(result).containsExactly(1, 2);
    }

    @Test
    void checkWithEmptyListAsArgument() {
        List<Integer> result = App.take(new ArrayList<>(), 2);
        assertThat(result).isEmpty();
    }

    @Test
    void checkWhenCountMoreThenListLength() {
        List<Integer> result = App.take(Arrays.asList(1, 2), 3);
        assertThat(result).containsExactly(1, 2);
    }

    @Test
    void checkWhenCountZero() {
        List<Integer> result = App.take(Arrays.asList(1, 2), 0);
        assertThat(result).isEmpty();
    }
    // END
}
