package softuni.exam.instagraphlite.models.entity;

import com.sun.xml.bind.v2.TODO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pictures")
public class Picture extends BaseEntity{
    private String path;
    private double size;

    public Picture() {
    }


    @Column(nullable = false, unique = true)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
//TODO: validation 500-6000include
    @Column(nullable = false)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
