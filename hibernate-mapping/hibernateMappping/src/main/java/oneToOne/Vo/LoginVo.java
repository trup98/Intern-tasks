package oneToOne.Vo;

import javax.persistence.*;

@Entity
@Table(name = "Login")
public class LoginVo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "LoginId")
    private int id;

    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;

    @ManyToOne(cascade = CascadeType.ALL)
    private RegisterVo registerVo;
    public RegisterVo getRegisterVo() {
        return registerVo;
    }

    public void setRegisterVo(RegisterVo registerVo) {
        this.registerVo = registerVo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
