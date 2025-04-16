package dding.BandRoom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "band_room")
@Entity
public class BandRoom {

    @Id
    private String id;

//    private String userId;

    private String name;



    private String shortDescription;

    @Lob
    private String detailedDescription;



    private String phone;
    private Boolean parkingAvailable;
    private String parkingDescription;
    private String displayAddress; // 사용자 편의를 위한 주소 설명


    @Singular
    @ElementCollection
    private List<String> keywords;

    @Singular
    @ElementCollection
    private List<String> homepageUrls;

    @Lob
    private String notes; // 특이사항 자유기입란

    @OneToMany(mappedBy = "bandRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Studio> studios = new ArrayList<>(); //스튜디오들의 정보들

    @OneToMany(mappedBy = "bandRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BandRoomScheduleRule> scheduleRules = new ArrayList<>();

    @OneToMany(mappedBy = "bandRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SpecialClosedDay> specialClosedDays = new ArrayList<>();





    //From Image_Server
    private String thumbnailUrl; // 썸네일 또는 대표 사진들

    //From Front-end
    private String roadAddress;
    private String detailAddress;

    private Double latitude;
    private Double longitude;
}

