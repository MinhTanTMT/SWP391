package model;

/**
 * Lớp kai báo biến và thuộc tính
 * @Phiên Bản : 1.0 04/06/2023
 * @Tác giả: Nguyễn Văn Thịnh
 */
public class Account {

    private int id;
    private String username, pass, role_name;
    private int number_reset;
    private String full_name, dob, address, phone;
    private String gender, role_name2, img;

    public Account() {
    }

    public Account(int id, String username, String pass, String role_name, int number_reset, String full_name, String dob, String address, String phone, String gender, String role_name2, String img) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.role_name = role_name;
        this.number_reset = number_reset;
        this.full_name = full_name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.role_name2 = role_name2;
        this.img = img;
    }

    public Account(int id, String username, String pass, String role_name) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.role_name = role_name;
    }

    public Account(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getNumber_reset() {
        return number_reset;
    }

    public void setNumber_reset(int number_reset) {
        this.number_reset = number_reset;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole_name2() {
        return role_name2;
    }

    public void setRole_name2(String role_name2) {
        this.role_name2 = role_name2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
