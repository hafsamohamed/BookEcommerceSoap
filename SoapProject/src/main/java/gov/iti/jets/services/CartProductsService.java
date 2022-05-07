package gov.iti.jets.services;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.presentation.dtos.CartIdDto;
import gov.iti.jets.presentation.dtos.CartProductsDto;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface CartProductsService {
    boolean addCartProduct(int userId,int productId, int quantity);
    boolean checkIfProductExist(Integer userId, Integer productId);
    boolean updateProduct(int userId, Product product);
    boolean addProductToCart(int userId,int productId, int quantity);
    List<CartProductsDto> getCartList (int id);
    List<CartProductsDto> getAllCarts();
    boolean removeCartProduct(CartIdDto cartId);

}
