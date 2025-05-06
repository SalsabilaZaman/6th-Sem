package org.example;

public class UserRepository {

    public User getUserByEmail(String email) {

        if ("notfound@example.com".equals(email)) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
        return new User();
    }
}
