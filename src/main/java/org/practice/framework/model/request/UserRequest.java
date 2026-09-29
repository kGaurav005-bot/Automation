package org.practice.framework.model.request;

public class UserRequest {
    private String testCaseId;
    private String name;
    private String email;

    public UserRequest() {
    }

    private UserRequest(Builder builder) {

        this.name = builder.name;
        this.email = builder.email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }
    public String getTestCaseId() {
        return testCaseId;
    }
    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
    public static class Builder {

        private String name;
        private String email;

        public Builder name(String name) {

            this.name = name;
            return this;
        }

        public Builder email(String email) {

            this.email = email;
            return this;
        }

        public UserRequest build() {

            return new UserRequest(this);
        }
    }
}