package com.Revature.Project2.beans.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class User implements Serializable {

    @Id
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private boolean admin; //true = admin, false = user

    @OneToMany(mappedBy = "user")
    private List<Rating> ratingsByUser;

}