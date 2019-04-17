package Model.Form;

import javax.validation.constraints.*;

public class UserLogin {

    private String email;
    private String password;

    @Pattern(regexp = "[a-zA-Z0-9]([a-zA-Z0-9]*[._]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]+[._]?)+([a-zA-Z0-9]*[.][a-zA-Z0-9]+)$",
            message = "Email pattern is invalid")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 1, message = "Password Should Not Be Empty")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
