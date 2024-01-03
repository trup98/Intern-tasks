package oneToOne.Vo;

import javax.persistence.*;

@Entity
@Table(name = "Register")
public class RegisterVo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LoginId")
    private LoginVo loginVo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LoginVo getLoginVo() {
        return loginVo;
    }

    public void setLoginVo(LoginVo loginVo) {
        this.loginVo = loginVo;
    }
}
