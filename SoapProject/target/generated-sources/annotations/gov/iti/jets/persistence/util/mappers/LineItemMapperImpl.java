package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.LineItem;
import gov.iti.jets.presentation.dtos.LineItemDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-07T21:33:04+0200",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class LineItemMapperImpl implements LineItemMapper {

    @Override
    public LineItem lineItemDtoToEntity(LineItemDto lineItemDto) {
        if ( lineItemDto == null ) {
            return null;
        }

        LineItem lineItem = new LineItem();

        lineItem.setId( lineItemDto.getId() );
        lineItem.setProductName( lineItemDto.getProductName() );
        lineItem.setProductQuantity( lineItemDto.getProductQuantity() );
        if ( lineItemDto.getProductPrice() != null ) {
            lineItem.setProductPrice( lineItemDto.getProductPrice() );
        }

        return lineItem;
    }

    @Override
    public LineItemDto lineItemToDto(LineItem lineItem) {
        if ( lineItem == null ) {
            return null;
        }

        LineItemDto lineItemDto = new LineItemDto();

        lineItemDto.setId( lineItem.getId() );
        lineItemDto.setProductName( lineItem.getProductName() );
        lineItemDto.setProductQuantity( lineItem.getProductQuantity() );
        lineItemDto.setProductPrice( lineItem.getProductPrice() );

        return lineItemDto;
    }
}
