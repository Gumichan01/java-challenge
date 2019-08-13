package com.gumichan01.challenge.service;

import com.gumichan01.challenge.domain.AuctionHouse;
import com.gumichan01.challenge.persistence.AuctionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionHouseService {

    @Autowired
    private AuctionHouseRepository repository;

    public List<AuctionHouse> retrieveAllAuctionHouses() {
        return repository.findAll();
    }

    public AuctionHouse registerAuctionHouse(AuctionHouse auctionHouse) {
        return repository.save(auctionHouse);
    }
    // TODO register a new house
    // TODO delete a house
}
