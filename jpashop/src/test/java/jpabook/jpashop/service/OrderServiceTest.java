package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final EntityManager em;

    @Autowired
    public OrderServiceTest(OrderService orderService, OrderRepository orderRepository, EntityManager em) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.em = em;
    }

    @DisplayName("주문 - 상품주문")
    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","한강","123-45"));
        em.persist(member);

        Book book = new Book();
        book.setName("JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder =  orderRepository.findOne(orderId);

        assertEquals(getOrder.getStatus(), OrderStatus.ORDER, "상품 주문시 상태");
        assertEquals(1, getOrder.getOrderItems().size(), "주문환 상품 종류 수");
        assertEquals(10000*orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량");
        assertEquals(8, book.getStockQuantity(), "주문슈량 만큼 재고가 줄어야한다.");
    }

    @DisplayName("주문 - 주문취소")
    @Test
    public void 주문취소() throws Exception{
        //given

        //when

        //then
    }

    @DisplayName("주문 - 재고수량초과")
    @Test()
    public void 상품주문_재고수량초과() throws Exception{
        //given


        //when

        //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount);
        });
    }
}