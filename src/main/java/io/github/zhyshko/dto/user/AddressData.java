package io.github.zhyshko.dto.user;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class AddressData {

    private String city;
    private String streetName;
    private Integer streetNumber;
    private String streetAdditive;
    private Integer room;
    private String additionalInfo;

}
