package models;

import lombok.Getter;
import lombok.Setter;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
@Getter
@Setter
public class User extends BaseModel {

    @Constraints.Required()
    @Column(unique = true)
    @Size(max=30)
    private String email;

    @Size(max=30)
    private String name;

    @Size(max=100)
    private String passwordHash;
}
