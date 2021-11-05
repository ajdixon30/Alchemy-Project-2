package com.Revature.Project2.beans.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class Request {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String addRequest;

    @Column
    private String requestStatus;

}
