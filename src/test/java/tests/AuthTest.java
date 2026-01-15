package tests;

import org.junit.jupiter.api.Test;
import utilies.Auth;

import static org.junit.jupiter.api.Assertions.*;

class AuthTest {

    @Test
    void main() {
        Auth auth = new Auth();
        auth.authHttpORHttps("urlframework");
        System.out.println("urlframework  auth.cookiesString):=======" + auth.cookiesString);

    }
}