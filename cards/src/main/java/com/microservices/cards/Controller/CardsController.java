package com.microservices.cards.Controller;

import com.microservices.cards.Dto.CardsDto;
import com.microservices.cards.Dto.ResponseDto;
import com.microservices.cards.Service.CardsService;
import com.microservices.cards.constants.CardsConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private final CardsService cardsService;
    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard( @Valid @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber){

           cardsService.createCards(mobileNumber);

         return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.CREDIT_CARD,CardsConstants.MESSAGE_200));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam
                                              @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){

        CardsDto cardsDto =cardsService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid  @RequestBody CardsDto cardsDto) {
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
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber){
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
