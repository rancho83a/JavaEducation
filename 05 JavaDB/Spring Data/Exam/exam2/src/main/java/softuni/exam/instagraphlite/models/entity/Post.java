package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    private String caption;
    private User user;
    private Picture picture;


    public Post() {
    }

    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    @ManyToOne(optional = false)
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}
