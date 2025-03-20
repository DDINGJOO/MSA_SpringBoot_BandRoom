package review.entity;


import jakarta.persistence.Id;

public class Points {

    @Id
    private String reviewId;
    String name;
    int point;
}
