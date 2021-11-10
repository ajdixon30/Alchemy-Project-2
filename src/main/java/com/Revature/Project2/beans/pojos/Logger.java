package com.Revature.Project2.beans.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Logger {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String dateTime;

    @Column(columnDefinition = "VARCHAR(20000)")
    private String message;

    @Column
    private Integer warningLevel;

    public Logger(String dateTime, String message, Integer warningLevel) {
        this.dateTime = dateTime;
        this.message = message;
        this.warningLevel = warningLevel;
    }
}
