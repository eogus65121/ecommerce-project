package com.done.ecommerce.users.dto;


import com.done.ecommerce.users.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AddressRequest {

    private String state;
    private String city;
    private String town;

    public Address toAddress(){
        return Address.builder()
                .state(state)
                .city(city)
                .town(town)
                .build();
    }
}
