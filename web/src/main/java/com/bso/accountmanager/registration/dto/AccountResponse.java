package com.bso.accountmanager.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class AccountResponse {
    private final UUID id;
    private Integer branch;

    @JsonProperty("account")
    private Long accountNumber;
    private BigDecimal balance;
    private final List<EntryResponse> entries;

    @Data
    @Builder(toBuilder = true)
    public static class EntryResponse {
        private final UUID id;
        private final BigDecimal amount;
        private final String type;
    }
}
