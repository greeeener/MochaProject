package com.project.mocha.Creation.Repository;

import com.project.mocha.Creation.Entity.Creation;
import com.project.mocha.Genre.Entity.Genre;
import com.project.mocha.Keyword.Entity.Keyword;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.project.mocha.Creation.Entity.QCreation.creation;
import static com.project.mocha.Creator.Entity.QCreator.creator;

@RequiredArgsConstructor
public class CreationRepositoryImpl implements CreationRepositoryCustom{
    private final JPAQueryFactory queryFactory;




    @Override
    public Page<Creation> findAllWithFiltering(String searchKeyword, String publisher, Boolean is_end, List<Genre> genreList, List<Keyword> keywordList, Pageable pageable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            booleanBuilder.or(creation.title.containsIgnoreCase(searchKeyword));
            booleanBuilder.or(creation.CreatorList.any().creator_name.containsIgnoreCase(searchKeyword));
            booleanBuilder.or(creation.publisher.containsIgnoreCase(searchKeyword));
        }

        if(is_end != null){
            booleanBuilder.and(creation.is_end.eq(is_end));
        }

        if (genreList != null && !genreList.isEmpty()) {
            for (Genre genre : genreList) {
                booleanBuilder.and(creation.GenreList.any().eq(genre));
            }
        }

        if (keywordList != null && !keywordList.isEmpty()) {
            for (Keyword keyword : keywordList) {
                booleanBuilder.and(creation.KeywordList.any().eq(keyword));
            }
        }

        // 쿼리 실행
        List<Creation> creations =  queryFactory
                .selectFrom(creation)
                .where(booleanBuilder)
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(creation.count())
                .from(creation)
                .where(booleanBuilder)
                .fetchOne();

        return new PageImpl<>(creations, pageable, total);


    }


    private OrderSpecifier[] getOrderSpecifier(Sort sort) {
        OrderSpecifier[] specifiers = sort.stream()
                .map(this::getOrderSpecifier)
                .toArray(OrderSpecifier[]::new);

        if (specifiers.length == 0) {
            PathBuilder pathBuilder = new PathBuilder(creation.getType(), creation.getMetadata());
            specifiers = new OrderSpecifier[]{new OrderSpecifier(Order.ASC, pathBuilder.get("id"))};
        }

        return specifiers;
    }

    private OrderSpecifier getOrderSpecifier(Sort.Order order) {
        Order direction = order.isAscending() ? Order.ASC : Order.DESC;
        PathBuilder pathBuilder = new PathBuilder(creation.getType(), creation.getMetadata());
        return new OrderSpecifier(direction, pathBuilder.get(order.getProperty()));
    }
}