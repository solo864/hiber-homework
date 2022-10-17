package az.online.shop.entity;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_product")
@Entity
public class OrderProduct extends BaseEntity<Integer> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private LocalDate createdAt;

    private Integer count;

    public void addOrder(Order order) {
        this.order = order;
        this.order.getOrderProducts().add(this);
    }

    public void addProduct(Product product) {
        this.product = product;
        this.product.getOrderProducts().add(this);
    }
}