package dding.hroomstudio.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "gears")
public class Gears {

    @Id
    @GeneratedValue
    private Long id;
    private String studioId;

    private  String gearImageUrl;

    private  String category;
    private String name;
    private String comments;

    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
