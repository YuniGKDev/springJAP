package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orederSearch){
        /*
        List<Order> resultList = em.createQuery("SELEC O FROM ORDER O JOIN O.MEMBER M" +
                        "WHERE O.STATUS = :STATUS" +
                        "AND M.NAME LIKE :NAME", Order.class)
                .setParameter("STATUS", orederSearch.getOrderStatus())
                .setParameter("NAME", orederSearch.getMemberName())
                .setMaxResults(1000)//최대 천건을 조회한다.
                .getResultList();
        */

        /* JPA Criteria */

        /* Querydsl */

        return null;
    }

    /* N+1문제를 해결하기 위해 join fetch를 사용한다. */
    public List<Order> findAllWithMemberDelivery(){
        //재사용이 가능하다.
        return em.createQuery(
                "select o from Order o"+
                    "join fetch o.member m"+
                    "join fetch o.delivery d",
                    Order.class
        ).getResultList();
    }
}
