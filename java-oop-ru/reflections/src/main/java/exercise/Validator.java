package exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object obj) {
        var notValidFields = new ArrayList<String>();
        for (var field : obj.getClass().getDeclaredFields()) {
            if (field.getAnnotation(NotNull.class) != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(obj) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        var notValidFields = new HashMap<String, List<String>>();
        for (var field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                var value = field.get(obj);
                if (value == null && field.getAnnotation(NotNull.class) != null) {
                    var errorMessage = "can not be null";
                    notValidFields.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add(errorMessage);
                }
                var minLengthAnnotation = field.getAnnotation(MinLength.class);
                if (minLengthAnnotation != null) {
                    var minLengthValue = minLengthAnnotation.minLength();
                    if (String.valueOf(value).length() < minLengthValue) {
                        var errorMessage = String.format("length less than %d", minLengthValue);
                        notValidFields.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add(errorMessage);
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notValidFields;
    }
}
// END
