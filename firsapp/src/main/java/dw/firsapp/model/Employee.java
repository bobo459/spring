package dw.firsapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="employees")  //테이블명은 복수가 많기에 이름을 복수형으로 만듬
public class Employee {  //테이블화를 시켜줌. 밑에는 데이터  =>단수형으로 만듬
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //값을 생성할건데 어떤 식으로 생성할것인지?
    //id는 빅인트에 들어간다
    private long id;
    @Column(name = "first_name", nullable = false)  //nullable = false => not null을 의미
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email")
    private String email;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
