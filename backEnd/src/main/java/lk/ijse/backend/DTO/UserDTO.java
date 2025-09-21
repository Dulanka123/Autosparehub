package lk.ijse.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.UUID;

public class  UserDTO {
    private UUID userId;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Please provide a valid email address")
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Please provide a valid mobile number")
    private String mobile;
    @NotBlank(message = "address is required")
    @Size(min = 3, max = 50, message = "Name must contain 3-50 characters")
    private String address;
    //@Pattern(regexp = "^[0-9]{10}$", message = "Please provide a valid NIC")
    private String nic;
   // @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Please provide a valid date of birth")
    private Date dob;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number and at least 8 characters")
    private String password;
    private String role;

    public UserDTO() {
    }

    public UserDTO(UUID userId, String name, String email, String mobile, String address, String nic, Date dob, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.nic = nic;
        this.dob = dob;
        this.password = password;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
