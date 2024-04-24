package dw.wholesale_company.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "주문세부")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //오토인크리먼트=> 만들어지는 순서대로 카운트한다.중복이 없이 신경쓰지 않고 자동으로 숫자가 증가되는방식.숫자.
    @Column(name="주문세부번호") //하이버네이트가 사용하는 이름.
    private long orderDetailId; //알파벳으로 사용하여야 하는 이유는 자바에서 사용을 할것이기때문에

    //컴포직 프라이머키, 2가지 이상의 프라이머키를 가지고 있다.
    //여기서는 주문세부번호를 프라이머키로 만들어서 컴포직프라이머키로 밑에 부분을 사용안하게 만들어준것이다.
    @ManyToOne
    @JoinColumn(name="주문번호")
    private Order order;
    @ManyToOne
    @JoinColumn(name="제품번호")
    private Product product;


    @Column(name="단가")
    private long unitPrice;
    @Column(name="주문수량")
    private long orderQuantity;
    @Column(name="할인율")
    private float discountRate;


}
