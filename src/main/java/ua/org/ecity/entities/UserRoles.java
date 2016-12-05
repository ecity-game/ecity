package ua.org.ecity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @Column(name = "user_id")
    private int userid;
    @Column(name = "role_id")
    private int roleid;

    public UserRoles() {
    }

    public UserRoles(int userid, int roleid) {
        this.userid = userid;
        this.roleid = roleid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
}
