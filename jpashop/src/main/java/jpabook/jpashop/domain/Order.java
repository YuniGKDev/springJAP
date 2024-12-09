package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Setter @Getter
@ToString(callSuper = true, exclude = "orderItems")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@Table(name="ordes")
public class Order extends AuditingFields{
    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;/* 연관관계의 주인으로 기준점 */

    @OneToMany(mappedBy = "order")
    @OrderBy("createdAt DESC")
    private List<OrderItems> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    /* FK 접속이 많은 곳에 설정한다. */
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태

    private LocalDateTime orderDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
