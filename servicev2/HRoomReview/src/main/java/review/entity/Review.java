package review.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "review")
public class Review {
    @Id
    private String reviewId;


    private String hRoomId;
    private String studioId;
    private String userId;



    private String profileImageUrl;
    private String comments;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
