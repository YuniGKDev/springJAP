package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* *
* xToOne(ManyToOne, OneToOne)
* Order
* Order -> Member
* Order -> Delivery
* */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        /* 필요한 내용만 호출 */
        for(Order order : all){
            /* 강제로 레이즈 로딩 */
            /*  order.getMember() 해당 부분 까진 prox객체이다.
            * getName() 호출 시, db에 쿼리가 실행되어 강제로 데이터를 조회하게 한다.
            * -> Lazy 강제 초기화 */
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }
}
