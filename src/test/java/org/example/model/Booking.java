package org.example.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Booking {
    private Integer bookingid;
    private BookingData booking;
}
