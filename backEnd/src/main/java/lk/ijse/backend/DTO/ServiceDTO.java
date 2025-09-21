package lk.ijse.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

/*
@AllArgsConstructor
@NoArgsConstructor
@Data
*/


public class ServiceDTO {
    private long serviceId;
    @NotBlank
    @Size(min = 3, max = 50 , message = "Service name must contain 3-50 characters")
    private String serviceName;
    @NotBlank
    private String serviceDescription;
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Invalid price")
    private double servicePrice;
    @NotBlank
    private String serviceDuration;
    @NotBlank
    private String serviceType;
    @NotBlank
    private String serviceStatus;
    @NotBlank
    private String location;

    public ServiceDTO() {
    }

    public ServiceDTO(long serviceId, String serviceName, String serviceDescription, double servicePrice, String serviceDuration, String serviceType, String serviceStatus, String location) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.serviceDuration = serviceDuration;
        this.serviceType = serviceType;
        this.serviceStatus = serviceStatus;
        this.location = location;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
