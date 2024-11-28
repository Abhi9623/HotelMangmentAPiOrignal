package com.example.Hotel.Body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respbodyall {
    private final String res="Scuccess";
    private  String token;
}
