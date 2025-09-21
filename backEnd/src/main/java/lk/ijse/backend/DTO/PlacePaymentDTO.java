package lk.ijse.backend.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class PlacePaymentDTO {
    private UserDTO userDTO;
    private String customerName;
    private String customerAddress;
    private String apartment;
    private String customerContact;
    private String customerEmail;
    private String postCode;
    private String city;
    private String country;
    private String note;
    private List<ItemDTO> itemDTOS;
    private List<ServiceDTO> serviceDTOS;
    private String paymentMethod;
    private String orderStatus;

    public PlacePaymentDTO() {
    }
    public PlacePaymentDTO(UserDTO userDTO, String customerName, String customerAddress, String apartment, String customerContact, String customerEmail, String postCode, String city, String country, String note, List<ItemDTO> itemDTOS, List<ServiceDTO> serviceDTOS, String paymentMethod, String orderStatus) {
        this.userDTO = userDTO;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.apartment = apartment;
        this.customerContact = customerContact;
        this.customerEmail = customerEmail;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.note = note;
        this.itemDTOS = itemDTOS;
        this.serviceDTOS = serviceDTOS;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<ItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public void setItemDTOS(List<ItemDTO> itemDTOS) {
        this.itemDTOS = itemDTOS;
    }

    public List<ServiceDTO> getServiceDTOS() {
        return serviceDTOS;
    }

    public void setServiceDTOS(List<ServiceDTO> serviceDTOS) {
        this.serviceDTOS = serviceDTOS;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "PlacePaymentDTO{" +
                "userDTO=" + userDTO +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", apartment='" + apartment + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", note='" + note + '\'' +
                ", itemDTOS=" + itemDTOS +
                ", serviceDTOS=" + serviceDTOS +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
