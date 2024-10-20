package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    private String email;

    private String mobileNumber;

    @NotEmpty(message = "Account type can't be null or empty")
    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch Address type can't be null or empty")
    @Schema(
            description = "Eazy Bank branch address", example = "123 Giza"
    )
    private String branchAddress;

    @NotEmpty(message = "AccountNumber can't be empty or null")
    @Pattern(regexp = "^$|[0-9]{10}",message = "Account Number must be 10 digits")
    @Schema(
            description = "Account Number of Eazy Bank account", example = "3454433243"
    )
    private Long accountNumber;
}
