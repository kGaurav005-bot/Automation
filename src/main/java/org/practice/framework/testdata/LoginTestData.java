package org.practice.framework.testdata;

public class LoginTestData {

    private final String username;
    private final String password;
    private final boolean valid;

    public LoginTestData(
            String username,
            String password,
            boolean valid) {

        this.username = username;
        this.password = password;
        this.valid = valid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString() {
        return "LoginTestData{" +
                "username='" + username + '\'' +
                ", valid=" + valid +
                '}';
    }
}