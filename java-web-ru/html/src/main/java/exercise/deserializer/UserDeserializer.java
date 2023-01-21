package exercise.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import exercise.user.User;

import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {
    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        var codec = parser.getCodec();
        JsonNode jsonNode = codec.readTree(parser);

        var firstName = jsonNode.get("firstName").asText();
        var lastName = jsonNode.get("lastName").asText();
        var id = jsonNode.get("id").asText();
        var email = jsonNode.get("email").asText();

        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
