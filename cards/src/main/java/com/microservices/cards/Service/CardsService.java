package com.microservices.cards.Service;

import com.microservices.cards.Dto.CardsDto;

public interface CardsService {

    public void createCards(String mobileNumber);

    public CardsDto fetchCard(String mobileNumber);

    public boolean updateCard(CardsDto cardsDto);
}
