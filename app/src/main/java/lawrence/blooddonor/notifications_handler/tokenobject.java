package lawrence.blooddonor.notifications_handler;

import com.google.gson.annotations.SerializedName;
public class tokenobject {
    @SerializedName("token")
    private String token;
    public tokenobject(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}