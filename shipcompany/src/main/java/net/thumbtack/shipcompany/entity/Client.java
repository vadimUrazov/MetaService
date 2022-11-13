package net.thumbtack.shipcompany.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "client", schema = "public")
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_user")
public class Client extends User {
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private List<Cargo> cargos = new ArrayList<>();

    public Client(long id, String surname, String name, String middlename, String login, String password, String email,
                  String phone, List<Order> orders) {
        super(id, surname, name, middlename, login, password, UserType.CLIENT);
        this.email = email;
        this.phone = phone;
        this.orders = orders;
    }

    public Client(long id, String surname, String name, String middlename, String login, String password, String email, String phone) {
        super(id, surname, name, middlename, login, password, UserType.CLIENT);
        this.email = email;
        this.phone = phone;
    }

    public Client(String surname, String name, String middlename, String login, String password, String email, String phone) {
        this(0, surname, name, middlename, login, password, email, phone);
    }
}
