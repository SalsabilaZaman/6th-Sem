package org.example;

public class AuthManager {
    private final UserRepository userRepository;
    private final Hashing hashing;

    public AuthManager(UserRepository userRepository, Hashing hashing) {
        this.userRepository = userRepository;
        this.hashing = hashing;
    }

    public User login(String email, String password) throws UserNotFoundException {

        try{
            User user = userRepository.getUserByEmail(email);
            if (user != null) {
                int hashedPass = hashing.hashPassword(password);
                if (hashedPass == user.getPass()) {
                    //System.out.println("Login Successful");
                    return user;
                }
                else {
                    System.out.println("Login Failed");
                    return null;
                }
            }
        } catch (UserNotFoundException e) {
            throw e;
        }
        return null;
    }
}
