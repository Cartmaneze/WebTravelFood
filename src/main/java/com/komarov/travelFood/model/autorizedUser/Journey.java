package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Никита on 08.10.2017.
 */
@NamedQueries({
        @NamedQuery(name = Journey.DELETE, query = "DELETE FROM Journey m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Journey.GET_ALL, query = "SELECT m FROM Journey m WHERE m.user.id=:userId")
})
@Entity
@Table(name = "JOURNEY")
public class Journey extends NamedEntity {
    public static final String DELETE = "Journey.delete";
    public static final String GET_ALL = "Journey.getAll";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "journey")
    private List<Day> dayList;

    public Journey() {
    }

    public Journey(int id, String name, User user) {
        super(id, name);
        this.user = user;
    }

    public Journey(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "user=" + user +
                '}';
    }
}
