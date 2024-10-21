package com.microservices.cards.Controller;

import com.microservices.cards.Dto.ResponseDto;
import com.microservices.cards.Service.CardsService;
import com.microservices.cards.constants.CardsConstants;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private final CardsService cardsService;
    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){

           cardsService.createCards(mobileNumber);

         return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.CREDIT_CARD,CardsConstants.MESSAGE_200));
    }
}
