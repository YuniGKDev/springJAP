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

    public List<SimpleOrderDto> findOrderDtos() {
        /* 해당 쿼리로는 객체만 반환된다 DTO반환을 위해서는 쿼리를 변경해야한다. */
        return em.createQuery(
                "select o from Order o"+
                    //내가 원하는 내용만 조회한다. 재사용이 불가하다.
                    //select new jpabook.jpashop.repository.SimpleOrderDto(o.id, m.name, o.orderdate, o.status, d.address)
                        "join o.member m"+
                        "join o.delivery d",
                SimpleOrderDto.class//반환 타입
        ).getResultList();
    }
}
