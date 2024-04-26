package dw.wholesale_company.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "주문")
public class Order {
    @Id
    @Column(name="주문번호")
    private String orderId;


    @ManyToOne
    @JoinColumn(name="고객번호")
    private Customer customer;  //Customer 이클래스에서 불러올것이다. customerObj 붙이는게 일반적이다. => 변수명을 coustomerId라고 적으면 나중에 Id만 기억에 남아서 헷갈릴수 있으므로 피해가자.
    @ManyToOne
    @JoinColumn(name="사원번호")
    private Employee employee;

    @Column(name="주문일")
    private LocalDate orderDate;
    @Column(name="요청일")
    private LocalDate requestDate;
    @Column(name="발송일")
    private LocalDate shippingDate;
}
