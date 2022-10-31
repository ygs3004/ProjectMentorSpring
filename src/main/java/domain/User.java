package domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private int user_idx;
    @NotBlank
    private int user_role;


    @NotBlank
    @Size(min=2, max=4)
    @Pattern(regexp = "[가-힣]*")
    private String user_name;
    @NotBlank
    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_id;
    @NotBlank
    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_pw;
    @NotBlank
    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_pw2;

    @NotBlank
    @Email
    private String user_email;

    private String user_phone;
    private String user_gender;
    private String user_school;

    private boolean userIdExist;

    private boolean userLogin;

    private boolean userEmailExist;

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_pw2() {
        return user_pw2;
    }

    public void setUser_pw2(String user_pw2) {
        this.user_pw2 = user_pw2;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }


    public User() {
        this.userIdExist = false;
        this.userEmailExist = false;
        this.userLogin = false;
    }

    public int getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public boolean isUserIdExist() {
        return userIdExist;
    }

    public boolean isUserEmailExist() {
        return userEmailExist;
    }

    public void setUserIdExist(boolean userIdExist) {
        this.userIdExist = userIdExist;
    }


    public void setUserEmailExist(boolean userEmailExist) {
        this.userEmailExist = userEmailExist;
    }

    public boolean isUserLogin() {
        return userLogin;
    }

    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }

}
