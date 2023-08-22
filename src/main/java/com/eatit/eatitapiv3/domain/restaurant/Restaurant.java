package com.eatit.eatitapiv3.domain.restaurant;

import com.eatit.eatitapiv3.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "restaurant")
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends BaseEntity {

    public static final String DELIM = "|#|";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "sub_name", length = 64)
    private String subName;

    @Column(name = "summary", nullable = false, length = 256)
    private String summary;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", nullable = false, length = 255)
    private String location;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "thumbnail_image_url", length = 512)
    private String thumbnailImageUrl;

    @Column(name = "thumbnail_image_name", length = 512)
    private String thumbnailImageName;

    @Column(name = "tables")
    private Integer tablesCount;

    @Column(name = "is_opened", nullable = false)
    @JsonProperty("isOpened")
    private boolean isOpened;

    @Column(name = "meal_ticket_enabled", nullable = false)
    private boolean mealTicketEnabled;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Builder(buildMethodName = "essentialBuild")
    public Restaurant(String name,
                      String subName,
                      String summary,
                      String description,
                      String location,
                      String phoneNumber,
                      int tablesCount,
                      boolean isOpened,
                      boolean mealTicketEnabled,
                      Long ownerId) {
        this.name = name;
        this.subName = subName;
        this.summary = summary;
        this.description = description;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.tablesCount = tablesCount;
        this.isOpened = isOpened;
        this.mealTicketEnabled = mealTicketEnabled;
        this.ownerId = ownerId;
    }

    public void update(Restaurant updateRestaurant) {
        this.name = updateRestaurant.getName();
        this.subName = updateRestaurant.getSubName();
        this.summary = updateRestaurant.getSummary();
        this.description = updateRestaurant.getDescription();
        this.location = updateRestaurant.getLocation();
        this.phoneNumber = updateRestaurant.getPhoneNumber();
        this.tablesCount = updateRestaurant.getTablesCount();
        this.isOpened = updateRestaurant.isOpened();
    }

    public void updateStatus(Boolean isOpened) {
        this.isOpened = isOpened;
    }

    public void updateMealTicketEnabled(Boolean mealTicketEnabled) {
        this.mealTicketEnabled = mealTicketEnabled;
    }

}
