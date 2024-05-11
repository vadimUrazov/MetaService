package net.thumbtack.buscompany.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "admin", schema = "public")
@PrimaryKeyJoinColumn(name = "id_user")
public class Admin extends User {

    @Column(name = "position")
    private String position;

    public Admin(long id, String surname, String name, String middlename, String login,
                 String password, String position) {
        super(id, surname, name, middlename, login, password, UserType.ADMIN);
        this.position = position;
    }

    public Admin(String surname, String name, String middlename, String login, String password,
                 String position) {
        this(0, surname, name, middlename, login, password, position);
    }
}
