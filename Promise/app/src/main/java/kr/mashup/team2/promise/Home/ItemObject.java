package kr.mashup.team2.promise.Home;

public class ItemObject {
    private int id;
    private String date;
    private String location;
    private String latitude;
    private String longitude;
    private String user;

    public ItemObject(int id, String date, String location, String latitude, String longitude, String user) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
