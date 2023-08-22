package com.eatit.eatitapiv3.domain.restaurant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantTimeTable is a Querydsl query type for RestaurantTimeTable
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurantTimeTable extends EntityPathBase<RestaurantTimeTable> {

    private static final long serialVersionUID = -1333979525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantTimeTable restaurantTimeTable = new QRestaurantTimeTable("restaurantTimeTable");

    public final StringPath breakTimes = createString("breakTimes");

    public final StringPath dayType = createString("dayType");

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isClosed = createBoolean("isClosed");

    public final QRestaurant restaurant;

    public final StringPath startTime = createString("startTime");

    public QRestaurantTimeTable(String variable) {
        this(RestaurantTimeTable.class, forVariable(variable), INITS);
    }

    public QRestaurantTimeTable(Path<? extends RestaurantTimeTable> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantTimeTable(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantTimeTable(PathMetadata metadata, PathInits inits) {
        this(RestaurantTimeTable.class, metadata, inits);
    }

    public QRestaurantTimeTable(Class<? extends RestaurantTimeTable> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant")) : null;
    }

}

