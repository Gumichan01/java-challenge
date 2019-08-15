package com.gumichan01.challenge.service;

import com.gumichan01.challenge.domain.Auction;
import com.gumichan01.challenge.persistence.AuctionRepository;
import com.gumichan01.challenge.service.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository repository;

    public List<Auction> retieveAuctions(Long houseId) {
        // TODO Check if the house exists

        if (houseId == null) {
            throw new BadRequestException("The auction house id is not provided");
        }

        List<Auction> auctions = repository.findAll();
        List<Auction> auctionsFilteredByHouseId = auctions.stream()
                .filter(auction -> houseId.equals(auction.getAuctionHouse().getId())).collect(Collectors.toList());
        return auctionsFilteredByHouseId;
    }
}
