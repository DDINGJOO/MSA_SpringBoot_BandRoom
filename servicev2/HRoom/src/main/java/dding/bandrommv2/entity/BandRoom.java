package dding.bandrommv2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name= "bandroomv2")
public class BandRoom {
    @Id
    private String hRoomId;
    private String adminId;



    private String name;
    private String tel;
    private String addressId;
    private String profileImageUrl;


    private String introduction;



    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
