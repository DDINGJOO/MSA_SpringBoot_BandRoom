package dding.BandRoom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "band_room")
@Entity
public class BandRoom {

    @Id
    private String id;

    private String name;



    private String shortDescription;

    @Lob
    private String detailedDescription;



    private String phone;
    private Boolean parkingAvailable;
    private String displayAddress; // 사용자 편의를 위한 주소 설명


    @ElementCollection
    private List<String> keywords = new ArrayList<>(); // 필터링 키워드

    @ElementCollection
    private List<String> homepageUrls = new ArrayList<>(); // 여러 개의 홈페이지 링크

    @Lob
    private String notes; // 특이사항 자유기입란

    @OneToMany(mappedBy = "bandRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Studio> studios = new ArrayList<>();

    @OneToMany(mappedBy = "bandRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BandRoomScheduleRule> scheduleRules = new ArrayList<>();

    @OneToMany(mappedBy = "bandRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpecialClosedDay> specialClosedDays = new ArrayList<>();

    private String Keyword;




    //From Image_Server
    private String thumbnailUrl; // 썸네일 또는 대표 사진들

    //From Front-end
    private String roadAddress;
    private String detailAddress;

    private Double latitude;
    private Double longitude;
}

