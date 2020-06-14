package co.com.jvirtualstore.model;

import javax.persistence.*;

@Entity(name="authorities")
public class UserAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName = "username")
    private User user;

    private String authority;


    public UserAuthority(User user, String authority) {
        this.user=user;
        this.authority=authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
