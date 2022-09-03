package com.teosprint.flashcard.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardHashTag is a Querydsl query type for CardHashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardHashTag extends EntityPathBase<CardHashTag> {

    private static final long serialVersionUID = -1792354814L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardHashTag cardHashTag = new QCardHashTag("cardHashTag");

    public final QCard card;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCardHashTag(String variable) {
        this(CardHashTag.class, forVariable(variable), INITS);
    }

    public QCardHashTag(Path<? extends CardHashTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardHashTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardHashTag(PathMetadata metadata, PathInits inits) {
        this(CardHashTag.class, metadata, inits);
    }

    public QCardHashTag(Class<? extends CardHashTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.card = inits.isInitialized("card") ? new QCard(forProperty("card")) : null;
    }

}

