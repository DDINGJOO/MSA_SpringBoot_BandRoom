package dding.BandRoom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "studio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Studio {

    @Id
    private String id;

    private String name;

    @ElementCollection
    private List<String> imageUrls;

    @Lob
    private String description;

    private Integer defaultPrice; // 기본 요금 (없으면 정책 기반으로만 운영)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_room_id")
    private BandRoom bandRoom;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudioPricingPolicy> pricingPolicies;
}
