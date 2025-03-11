package org.example.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class BookingDates {
    private String checkin;
    private String checkout;
}
