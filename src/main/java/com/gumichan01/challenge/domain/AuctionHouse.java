package com.gumichan01.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class AuctionHouse {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    public AuctionHouse() {
    }

    public AuctionHouse(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof AuctionHouse))
            return false;

        AuctionHouse that = (AuctionHouse) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "AuctionHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
