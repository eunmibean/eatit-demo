package com.eatit.eatitapiv3.domain.restaurant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = -1243536090L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final com.eatit.eatitapiv3.domain.QBaseEntity _super = new com.eatit.eatitapiv3.domain.QBaseEntity(this);

    //inherited
    public final NumberPath<Long> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOpened = createBoolean("isOpened");

    public final StringPath location = createString("location");

    public final BooleanPath mealTicketEnabled = createBoolean("mealTicketEnabled");

    public final StringPath name = createString("name");

    public final NumberPath<Long> ownerId = createNumber("ownerId", Long.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath subName = createString("subName");

    public final StringPath summary = createString("summary");

    public final NumberPath<Integer> tablesCount = createNumber("tablesCount", Integer.class);

    public final StringPath thumbnailImageName = createString("thumbnailImageName");

    public final StringPath thumbnailImageUrl = createString("thumbnailImageUrl");

    //inherited
    public final NumberPath<Long> updatedAt = _super.updatedAt;

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

