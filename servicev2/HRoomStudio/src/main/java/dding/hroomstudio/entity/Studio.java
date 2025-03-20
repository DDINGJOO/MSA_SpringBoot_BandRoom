package dding.hroomstudio.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name= "studios")
public class Studio {
    @Id
    private String studioId;
    private String hRoomId;
    private String adminId;



    private String name;
    private String price;



    private String profileImageUrl;
    private String introduction;




    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
