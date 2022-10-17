package az.online.shop.entity;

import az.online.shop.model.Role;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NamedEntityGraph(
        name = "WithOrders",
        attributeNodes = {
                @NamedAttributeNode("orders")
        }
)
@EqualsAndHashCode(exclude = {"orders", "personalInfo"})
@ToString(exclude = {"orders", "personalInfo"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Customer extends BaseEntity<Integer> {

    private String firstName;

    private String surname;

    private String email;

    private String password;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalInfo personalInfo;

    @Builder.Default
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public void setPersonalInfo(PersonalInfo info) {
        info.setCustomer(this);
        this.personalInfo = info;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }
}