package lk.ijse.backend.DTO;

import java.sql.Date;

public class UpdateUserDTO {
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String nic;
    private Date dob;
    private String oldPassword;
    private String newPassword;

    public UpdateUserDTO(String name, String email, String mobile, String address, String nic, Date dob, String oldPassword, String newPassword) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.nic = nic;
        this.dob = dob;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
