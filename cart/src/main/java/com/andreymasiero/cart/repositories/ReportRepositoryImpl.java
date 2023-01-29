package com.andreymasiero.cart.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.andreymasiero.cart.dtos.CartReportDto;
import com.andreymasiero.cart.entities.Cart;

public class ReportRepositoryImpl implements ReportRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Cart> getShopByFilters(LocalDate begin, LocalDate end, Float minimum) {
        StringBuilder sb = new StringBuilder();
        sb.append("""
                select c
                from cart c
                where c.date >= :begin 
                """);
        if (end != null) {
           sb.append("and c.date <= :end ");
        }
        if (minimum != null) {
            sb.append("and s.total <= :minimum ");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("begin", begin);
        if (end != null) {
            query.setParameter("end", end);
        }
        if (minimum != null) {
            query.setParameter("minimum", minimum);
        }
        return query.getResultList();
    }

    @Override
    public CartReportDto getReportByDate(LocalDate begin, LocalDate end) {
        String s = """
            select count(c.id), sum(c.total), avg(c.total)
            from cart.cart c
            where c.date >= :begin
            and c.date <= :end
            """;

        Query query = entityManager.createNativeQuery(s);
        query.setParameter("begin", begin);
        query.setParameter("end", end);

        Object[] result = (Object[]) query.getSingleResult();

        CartReportDto reportDto = new CartReportDto();

        reportDto.setCount(((BigInteger) result[0]).intValue());
        reportDto.setTotal((Double) result[1]);
        reportDto.setAverage((Double) result[2]);

        return reportDto;
    }
}
