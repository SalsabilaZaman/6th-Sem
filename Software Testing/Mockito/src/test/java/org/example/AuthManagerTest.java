package org.example;


import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class AuthManagerTest {
    @Mock
    UserRepository userRepository;

    @Mock
    Hashing hashing;

    @Mock
    User user;

    @InjectMocks
    AuthManager authManager;

    String realEmail;
    String notRealEmail;
    String realPassword;
    String fakePassword;

    @Before
    public void setUp() {
        realEmail = "test@example.com";
        realPassword = "test";
        notRealEmail = "notfound@example.com";
        fakePassword = "test1";

        MockitoAnnotations.initMocks(this);
        when(user.getPass()).thenReturn(1234);
        when(hashing.hashPassword(realPassword)).thenReturn(1234);
        when(hashing.hashPassword(fakePassword)).thenReturn(5678);
        when(userRepository.getUserByEmail(realEmail)).thenReturn(user);
        when(userRepository.getUserByEmail(notRealEmail)).thenThrow(UserNotFoundException.class);

    }


    @Test
    public void test_loginSuccess(){
        try{
            assertEquals(authManager.login(realEmail,realPassword),user);
        }
        catch(UserNotFoundException e){
            assertNull(e);
        }
    }
    @Test
    public void test_loginUnSuccessWrongPass() throws Exception {
        assertNull(authManager.login(realEmail,fakePassword));
    }
    @Test(expected = UserNotFoundException.class)
    public void test_loginUnSuccessFakeMail() throws Exception {
        authManager.login(notRealEmail,fakePassword);

    }
}