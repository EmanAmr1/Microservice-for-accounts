package com.microservices.cards.Service.Impl;

import com.microservices.cards.Dto.CardsDto;
import com.microservices.cards.Entity.Cards;
import com.microservices.cards.Exception.CardAlreadyExistsException;
import com.microservices.cards.Exception.ResourceNotFoundException;
import com.microservices.cards.Mapper.CardsMapper;
import com.microservices.cards.Repo.CardsRepo;
import com.microservices.cards.Service.CardsService;
import com.microservices.cards.constants.CardsConstants;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardsServiceImpl implements CardsService {

    private final CardsRepo cardsRepo;
    public CardsServiceImpl(CardsRepo cardsRepo) {
        this.cardsRepo = cardsRepo;
    }

    @Override
    public void createCards(String mobileNumber) {
      Optional<Cards> existCard= cardsRepo.findByMobileNumber(mobileNumber);
      if(existCard.isPresent()) {
          throw new CardAlreadyExistsException("Card already exists with given mobileNumber"+mobileNumber);
      }
      cardsRepo.save(CreateNewCard(mobileNumber));
    }

    private Cards CreateNewCard(String mobileNumber) {
      Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardNumber(String.valueOf(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber){
     Cards card=  cardsRepo.findByMobileNumber(mobileNumber).orElseThrow(
             ()-> new ResourceNotFoundException("card","mobile number",mobileNumber)
     );
         return CardsMapper.mapToCardsDto(new CardsDto(),card);

    }

    @Override
    public boolean updateCard(CardsDto cardsDto){

        Cards card =cardsRepo.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Card","Card Number", cardsDto.getCardNumber())
        );

        CardsMapper.mapToCardsDto(cardsDto,card);
        cardsRepo.save(card);

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber){

        Cards card = cardsRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card","Mobile Number",mobileNumber)
        );

        cardsRepo.deleteById(card.getCardId());
        return true;

    }
}
