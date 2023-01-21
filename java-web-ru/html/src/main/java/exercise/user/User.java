package exercise.user;

public final class User {
    private String firstName;
    private String lastName;
    private String id;
    private String email;


    private User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final User user = new User();

        public Builder id(String id) {
            user.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
