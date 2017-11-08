package com.komarov.travelFood.model.autorizedUser;

import com.komarov.travelFood.model.NamedEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Никита on 11.10.2017.
 */
@NamedQueries({
        @NamedQuery(name = Day.DELETE, query = "DELETE FROM Day m WHERE m.id=:id AND m.journey.id=:journeyId"),
        @NamedQuery(name = Day.GET_ALL, query = "SELECT m FROM Day m WHERE m.journey.id=:journeyId")
})
@Entity
@Table(name = "DAYS")
public class Day extends NamedEntity {
    public static final String DELETE = "Day.delete";
    public static final String GET_ALL = "Day.getAll";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journey_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Journey journey;

    public Day() {
    }

    public Day(int id, String name, Journey journey) {
        super(id, name);
        this.journey = journey;
    }

    public Day(String name, Journey journey) {
        this.name = name;
        this.journey = journey;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    @Override
    public String toString() {
        return "Day{" +
                "day=" + journey +
                '}';
    }
}
