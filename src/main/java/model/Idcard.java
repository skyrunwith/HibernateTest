package model;

import org.hibernate.annotations.Table;

import javax.persistence.*;

/**
 * Created by FZD on 2017/4/26.
 */
@Entity
public class Idcard {
    private int id;
    private String num;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
