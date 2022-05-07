package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.presentation.dtos.CartProductsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-07T21:34:47+0200",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CartProductsMapperImpl implements CartProductsMapper {

    @Override
    public CartProducts cartProductsDtoToEntity(CartProductsDto cartProductsDto) {
        if ( cartProductsDto == null ) {
            return null;
        }

        CartProducts cartProducts = new CartProducts();

        cartProducts.setCartId( cartProductsDto.getCartId() );
        cartProducts.setQuantity( cartProductsDto.getQuantity() );
        cartProducts.setTotalPrice( cartProductsDto.getTotalPrice() );

        return cartProducts;
    }

    @Override
    public CartProductsDto cartProductsToDto(CartProducts cartProducts) {
        if ( cartProducts == null ) {
            return null;
        }

        CartProductsDto cartProductsDto = new CartProductsDto();

        cartProductsDto.setCartId( cartProducts.getCartId() );
        cartProductsDto.setQuantity( cartProducts.getQuantity() );
        cartProductsDto.setTotalPrice( cartProducts.getTotalPrice() );

        return cartProductsDto;
    }
}
