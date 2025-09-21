package lk.ijse.backend.DTO;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;


public class ShopDTO {
    private long shopId;
    @NotBlank(message = "Shop name is required")
    private String shopName;
    @NotBlank(message = "Shop address is required")
    @Size(min = 5, max = 100, message = "Shop address must contain 5-100 characters")
    private String shopAddress;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String shopContact;
    @Email(message = "Invalid email")
    private String shopEmail;
    @NotBlank
    private String shopOwner;
//    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String shopOwnerContact;
    @Email
    private String shopOwnerEmail;
    //@Pattern(regexp = "^[0-9]{9}[vVxX]$", message = "Please provide a valid NIC")
    private String shopOwnerNIC;
    @NotNull(message = "Date of birth is required")
    private Date shopOwnerDOB;
    private UserDTO userDTO;

    public ShopDTO() {
    }

    public ShopDTO(long shopId, String shopName, String shopAddress, String shopContact, String shopEmail, String shopOwner, String shopOwnerContact, String shopOwnerEmail, String shopOwnerNIC, Date shopOwnerDOB, UserDTO userDTO) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopContact = shopContact;
        this.shopEmail = shopEmail;
        this.shopOwner = shopOwner;
        this.shopOwnerContact = shopOwnerContact;
        this.shopOwnerEmail = shopOwnerEmail;
        this.shopOwnerNIC = shopOwnerNIC;
        this.shopOwnerDOB = shopOwnerDOB;
        this.userDTO = userDTO;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }


    public String getShopContact() {
        return shopContact;
    }

    public void setShopContact(String shopContact) {
        this.shopContact = shopContact;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public String getShopOwnerContact() {
        return shopOwnerContact;
    }

    public void setShopOwnerContact(String shopOwnerContact) {
        this.shopOwnerContact = shopOwnerContact;
    }

    public String getShopOwnerEmail() {
        return shopOwnerEmail;
    }

    public void setShopOwnerEmail(String shopOwnerEmail) {
        this.shopOwnerEmail = shopOwnerEmail;
    }

    public String getShopOwnerNIC() {
        return shopOwnerNIC;
    }

    public void setShopOwnerNIC(String shopOwnerNIC) {
        this.shopOwnerNIC = shopOwnerNIC;
    }

    public Date getShopOwnerDOB() {
        return shopOwnerDOB;
    }

    public void setShopOwnerDOB(Date shopOwnerDOB) {
        this.shopOwnerDOB = shopOwnerDOB;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
