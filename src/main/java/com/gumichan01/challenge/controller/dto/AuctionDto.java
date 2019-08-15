package com.gumichan01.challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AuctionDto {

    @JsonProperty(required = true)
    @JsonAlias
    private String name;
    @JsonProperty(required = true)
    private String description;
    @JsonProperty(value = "starting_time", required = true)
    private Date startingTime;
    @JsonProperty(value = "end_time", required = true)
    private Date endTime;
    @JsonProperty(value = "start_price", required = true)
    private Double startPrice;
    @JsonProperty(value = "house_id", required = true)
    private Long houseId;

    public AuctionDto(String name, String description, Date startingTime, Date endTime, Double startPrice, Long houseId) {
        this.name = name;
        this.description = description;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.startPrice = startPrice;
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    @Override
    public String toString() {
        return "AuctionDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startingTime=" + startingTime +
                ", endTime=" + endTime +
                ", startPrice=" + startPrice +
                ", houesId=" + houseId +
                '}';
    }
}