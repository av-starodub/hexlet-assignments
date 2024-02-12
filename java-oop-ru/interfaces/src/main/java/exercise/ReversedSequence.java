package exercise;

import lombok.NonNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public final class ReversedSequence implements CharSequence {
    private final Character[] sequence;

    public ReversedSequence(String text) {
        Objects.requireNonNull(text, "text must not be null ");
        sequence = reverse(text);
    }

    private static Character[] reverse(String text) {
        var chars = text.toCharArray();
        var reversed = new Character[chars.length];
        var reversedIdx = 0;
        for (var idx = text.length() - 1; idx >= 0; idx--) {
            reversed[reversedIdx] = chars[idx];
            reversedIdx++;
        }
        return reversed;
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int index) {
        return sequence[index];
    }

    @Override
    @NonNull
    public CharSequence subSequence(int start, int end) {
        return join(Arrays.copyOfRange(sequence, start, end));
    }

    @Override
    @NonNull
    public String toString() {
        return (String) join(sequence);
    }

    private CharSequence join(Character[] characters) {
        return Arrays.stream(characters)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
// END
