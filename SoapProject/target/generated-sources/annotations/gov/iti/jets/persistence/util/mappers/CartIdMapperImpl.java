package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.presentation.dtos.CartIdDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-07T21:33:04+0200",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CartIdMapperImpl implements CartIdMapper {

    @Override
    public CartId cartIdDtoToEntity(CartIdDto cartIdDto) {
        if ( cartIdDto == null ) {
            return null;
        }

        CartId cartId = new CartId();

        cartId.setUserId( cartIdDto.getUserId() );
        cartId.setProductId( cartIdDto.getProductId() );

        return cartId;
    }

    @Override
    public CartIdDto cartIdToDto(CartId cartId) {
        if ( cartId == null ) {
            return null;
        }

        CartIdDto cartIdDto = new CartIdDto();

        cartIdDto.setUserId( cartId.getUserId() );
        cartIdDto.setProductId( cartId.getProductId() );

        return cartIdDto;
    }
}
