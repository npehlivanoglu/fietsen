package be.vdab.fietsen.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record GewijzigdeWedde(@NotNull @Positive BigDecimal wedde) {
}
