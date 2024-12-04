package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.AuditingFields;
import lombok.*;

import java.util.Objects;

@Entity
@Setter @Getter
/* 상속관계전략 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public abstract class Item extends AuditingFields {
    @Id
    @GeneratedValue
    @Column(name = "item_id", nullable = false)
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
