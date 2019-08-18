package com.gumichan01.challenge.service;

import com.gumichan01.challenge.controller.dto.AuctionDto;
import com.gumichan01.challenge.domain.Auction;
import com.gumichan01.challenge.domain.AuctionHouse;
import com.gumichan01.challenge.persistence.AuctionHouseRepository;
import com.gumichan01.challenge.persistence.AuctionRepository;
import com.gumichan01.challenge.service.exception.AuctionConstraintViolationException;
import com.gumichan01.challenge.service.exception.BadRequestException;
import com.gumichan01.challenge.service.exception.InconsistentAuctionException;
import com.gumichan01.challenge.service.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    private static final Logger logger = LoggerFactory.getLogger(AuctionService.class);

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Autowired
    private AuctionRepository auctionRepository;

    public List<Auction> retrieveAuctionsBy(Long auctionHouseId) {
        logger.info("Get auctions from auction house by this id: " + auctionHouseId);
        if (auctionHouseId == null) {
            throw new BadRequestException("The auction house id is not provided.\n");
        }

        return auctionRepository.findAllByAuctionHouseId(auctionHouseId);
    }

    public void deleteAuction(Long id) {
        logger.info("delete auction by id: " + id);
        if (id == null) {
            throw new BadRequestException("Invalid request: no identifier provided.\n");
        }

        Optional<Auction> auctionById = auctionRepository.findById(id);
        if (!auctionById.isPresent()) {
            throw new ResourceNotFoundException("Auction to delete not found.\n");
        }

        if (isProcessing(auctionById.get())) {
            throw new AuctionConstraintViolationException("This auction is already started. Wait until it is terminated to delete it.\n");
        }

        logger.info("delete the auction: " + auctionById.get());
        auctionRepository.deleteById(id);
    }

    private boolean isProcessing(Auction auction) {
        Date now = Calendar.getInstance().getTime();
        return inDateInterval(now, auction.getStartingTime(), auction.getEndTime());
    }

    public Auction registerAuction(AuctionDto auctionDto) {
        logger.info("auction param: ");
        logger.info(auctionDto.toString());

        if (!isValidAuctionDto(auctionDto)) {
            throw new BadRequestException("Invalid parameters: some mandatory fields are not provided.\n");
        }

        if (!isConsistent(auctionDto)) {
            throw new InconsistentAuctionException("Invalid period of times.\n" +
                    "Expected: starting_time < end_time; " +
                    "\ngot: (starting_time = " + auctionDto.getStartingTime() +
                    ") >= (end_time = " + auctionDto.getEndTime() + ").\n");
        }

        AuctionHouse house = getAuctionHouseBy(auctionDto.getAuctionHouseId());
        logger.info("house related to the auction: ");
        logger.info(house.toString());
        Auction auctionToSave = new Auction(auctionDto);
        auctionToSave.setAuctionHouse(house);
        auctionToSave.setCurrentPrice(null);
        logger.info("save " + auctionToSave);
        return auctionRepository.save(auctionToSave);
    }

    private AuctionHouse getAuctionHouseBy(Long auctionHouseId) {
        Optional<AuctionHouse> auctionHouseById = auctionHouseRepository.findById(auctionHouseId);
        if (!auctionHouseById.isPresent()) {
            throw new ResourceNotFoundException("Cannot create the auction. The house to link with does not exist.\n");
        }
        return auctionHouseById.get();
    }

    private boolean inDateInterval(Date refTime, Date startingTime, Date endTime) {
        Instant instant = refTime.toInstant();
        Instant startInstant = startingTime.toInstant();
        Instant endInstant = endTime.toInstant();
        return startInstant.isBefore(instant) && endInstant.isAfter(instant);
    }

    private boolean isConsistent(AuctionDto auctionDto) {
        Date auctionStartingTime = auctionDto.getStartingTime();
        Date auctionEndTime = auctionDto.getEndTime();
        return auctionEndTime.after(auctionStartingTime);
    }

    private boolean isValidAuctionDto(AuctionDto auctionDto) {
        return auctionDto != null && auctionDto.getName() != null && auctionDto.getDescription() != null
                && auctionDto.getStartingTime() != null && auctionDto.getEndTime() != null
                && auctionDto.getAuctionHouseId() != null;
    }
}
