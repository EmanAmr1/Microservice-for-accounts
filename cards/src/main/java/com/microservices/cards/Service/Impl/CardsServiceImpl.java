package com.microservices.cards.Service.Impl;

import com.microservices.cards.Entity.Cards;
import com.microservices.cards.Exception.CardAlreadyExistsException;
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
}
