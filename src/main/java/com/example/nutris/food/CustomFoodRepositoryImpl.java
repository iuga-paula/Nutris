package com.example.nutris.food;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomFoodRepositoryImpl implements CustomFoodRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Food> searchFood(Map<String, String> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Food> query = builder.createQuery(Food.class);
        Root<Food> food = query.from(Food.class);
        List<Predicate> predicates = new ArrayList<>();

        String limit = params.get("limit");
        params.remove("limit");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (FoodConstants.searchTextValues.contains(name)) {
                // text value
                predicates.add(builder.like(food.get(name), "%" + value + "%"));
                continue;
            }

            // nutrition values can be exact value or > <
            if (value.charAt(0) == '>') {
                Float floatVal = Float.valueOf(value.substring(1));
                predicates.add(builder.greaterThan(food.get(name), floatVal));
                continue;
            }
            if (value.charAt(0) == '<') {
                Float floatVal = Float.valueOf(value.substring(1));
                predicates.add(builder.lessThan(food.get(name), floatVal));
                continue;
            }
            Float floatVal = Float.valueOf(value);
            predicates.add(builder.equal(food.get(name), floatVal));
        }

        query.where(predicates.toArray(new Predicate[0]));

        if (limit != null) {
            int intLimit = Integer.parseInt(limit);
            entityManager.createQuery(query).setMaxResults(intLimit).getResultList();
        }

        return entityManager.createQuery(query).getResultList();
    }
}
