package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.CartProductsDao;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.impl.CartProductsDaoImpl;
import gov.iti.jets.presentation.controllers.CartController;
import gov.iti.jets.presentation.dtos.CartIdDto;
import gov.iti.jets.presentation.dtos.CartProductsDto;
import gov.iti.jets.services.CartProductsService;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "gov.iti.jets.services.CartProductsService")
public class CartProductsServiceImpl implements CartProductsService {
    public static CartController cartController = new CartController();

    @Override
    public boolean addCartProduct(int userId,int productId, int quantity) {
        return cartController.addProductToCart(userId, productId, quantity);
    }

    @Override
    public boolean checkIfProductExist(Integer userId, Integer productId) {
        CartProductsDao cartProductsDao = new CartProductsDaoImpl();
        return cartProductsDao.chechIfProductExist(userId, productId);
    }

    @Override
    public boolean updateProduct(int userId, Product product) {
        return cartController.updateCartProduct(userId, product);
    }

    @Override
    public boolean addProductToCart(int userId,int productId, int quantity) {
        return cartController.addProductToCart(userId,productId,quantity);
    }

    @Override
    public List<CartProductsDto> getCartList(int id) {
        return cartController.getAllCartProducts(id);
    }

    @Override
    public List<CartProductsDto> getAllCarts() {
        return cartController.getAllCarts();
    }

    @Override
    public boolean removeCartProduct(CartIdDto cartId) {
        return cartController.deleteOrder(cartId.getUserId(), cartId.getProductId());
    }
}
