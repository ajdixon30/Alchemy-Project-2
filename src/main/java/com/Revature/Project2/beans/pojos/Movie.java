package com.Revature.Project2.beans.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Model class for movies
 */
@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Movie implements Serializable {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String genre;

    @JsonIgnoreProperties({"movie","user"}) //to prevent infinite loop
    @OneToMany(mappedBy = "movie")
    private List<Rating> ratingsByMovie;

}
