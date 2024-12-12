package com.study.NIHo.api.reservation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationAddRequestDTO {

    @NotNull
    @Valid
    private LocalDate checkInDate;

    @NotNull
    @Valid
    private LocalDate checkOutDate;

}
