package com.gumichan01.challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gumichan01.challenge.domain.model.Auction;

import java.util.Date;

public class AuctionDto {

    private Long id;
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String description;
    @JsonProperty(value = "starting_time", required = true)
    private Date startingTime;
    @JsonProperty(value = "end_time", required = true)
    private Date endTime;
    @JsonProperty(value = "start_price", required = true)
    private Double startPrice;
    @JsonProperty(value = "current_price")
    private Double currentPrice;

    public AuctionDto() {
    }

    public AuctionDto(Auction auction) {
        assert auction != null;
        this.id = auction.getId();
        this.name = auction.getProductName();
        this.description = auction.getDescription();
        this.startingTime = auction.getStartingTime();
        this.endTime = auction.getEndTime();
        this.startPrice = auction.getStartPrice();
        this.currentPrice = auction.getCurrentPrice();
    }

    public AuctionDto(String name, String description, Date startingTime, Date endTime, Double startPrice, Double currentPrice) {
        this.name = name;
        this.description = description;
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "AuctionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startingTime=" + startingTime +
                ", endTime=" + endTime +
                ", startPrice=" + startPrice +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
