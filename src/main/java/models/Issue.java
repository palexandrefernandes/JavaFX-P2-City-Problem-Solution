package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String description;
    private float latitude;
    private float longitude;
    private String image_b64;
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "solution_id")
    private Solution solution;
    private boolean denied;

    public Issue(){}

    public Issue(User user, String title, String description, float latitude, float longitude, String image_b64) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image_b64 = image_b64;
        this.createdAt = new Date();
    }

    public Issue(int id, User user, String title, String description, float latitude, float longitude, String image_b64, Date createdAt, Solution solution, boolean denied) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image_b64 = image_b64;
        this.createdAt = createdAt;
        this.solution = solution;
        this.denied = denied;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getImage_b64() {
        return image_b64;
    }

    public void setImage_b64(String image_b64) {
        this.image_b64 = image_b64;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public boolean isDenied() {
        return denied;
    }

    public void setDenied(boolean denied) {
        this.denied = denied;
    }

    public void setId(int id) {
        this.id = id;
    }


}
