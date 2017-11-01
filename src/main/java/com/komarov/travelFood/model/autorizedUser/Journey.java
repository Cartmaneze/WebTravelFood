package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Никита on 08.10.2017.
 */
@NamedQueries({
        @NamedQuery(name = Journey.DELETE, query = "DELETE FROM Journey m WHERE m.id=:id"),
        @NamedQuery(name = Journey.GET_ALL, query = "SELECT m FROM Journey m")
})
@Entity
@Table(name = "JOURNEY")
public class Journey extends NamedEntity {
    public static final String DELETE = "Journey.delete";
    public static final String GET_ALL = "Journey.getAll";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Journey() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "user=" + user +
                '}';
    }
}
