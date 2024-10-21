package com.microservices.cards.Controller;

import com.microservices.cards.Dto.CardsDto;
import com.microservices.cards.Dto.ResponseDto;
import com.microservices.cards.Service.CardsService;
import com.microservices.cards.constants.CardsConstants;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber){

        CardsDto cardsDto =cardsService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestParam CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else
            {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
            }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
        boolean isDeleted =cardsService.deleteCard(mobileNumber);
        if(isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }
    }

}
