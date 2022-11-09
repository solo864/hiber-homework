package az.online.shop.entity;

import az.online.shop.model.Gender;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = "customer")
@ToString(exclude = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "personal_info")
@Entity
public class PersonalInfo extends BaseEntity<Integer> {

    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Customer customer;
}