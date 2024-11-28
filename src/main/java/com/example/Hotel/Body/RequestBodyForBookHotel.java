package com.example.Hotel.Body;


import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestBodyForBookHotel {


    private  Long userid;
    private Long hotelid;
}
