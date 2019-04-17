package Model.Form;

import javax.validation.constraints.*;

public class UserSignUp {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Size(min = 1, message = "First Name Should Not Be Empty")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 1, message = "Last Name Should Not Be Empty")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Pattern(regexp = "[a-zA-Z0-9]([a-zA-Z0-9]*[._]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]+[._]?)+([a-zA-Z0-9]*[.][a-zA-Z0-9]+)$",
             message = "Email pattern is invalid")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, max = 30, message = "Password should have at least 6 characters and at most 30 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
