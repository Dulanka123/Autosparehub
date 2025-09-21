// DTO class
package lk.ijse.backend.DTO;

public class LoginAlertDTO {
    private String username;
    private String loginTime;
    private String device;
    private String location;

    public LoginAlertDTO() {}

    public LoginAlertDTO(String username, String loginTime, String device, String location) {
        this.username = username;
        this.loginTime = loginTime;
        this.device = device;
        this.location = location;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLoginTime() { return loginTime; }
    public void setLoginTime(String loginTime) { this.loginTime = loginTime; }

    public String getDevice() { return device; }
    public void setDevice(String device) { this.device = device; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
