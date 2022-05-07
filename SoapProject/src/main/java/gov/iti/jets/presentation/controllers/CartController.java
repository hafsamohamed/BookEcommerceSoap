package gov.iti.jets.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.CartProductsDao;
import gov.iti.jets.persistence.ProductDao;
import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.entities.CartId;
import gov.iti.jets.persistence.entities.CartProducts;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.impl.CartProductsDaoImpl;
import gov.iti.jets.persistence.impl.ProductDaoImpl;
import gov.iti.jets.persistence.impl.UserDaoImpl;
import gov.iti.jets.persistence.util.mappers.CartProductsMapper;
import gov.iti.jets.presentation.dtos.CartIdDto;
import gov.iti.jets.presentation.dtos.CartProductsDto;



public class CartController {
    public static CartProductsMapper cartProductsMapper = CartProductsMapper.INSTANCE;
    public static CartProductsDao cartProductsService = new CartProductsDaoImpl();
    public static UserDao userService = new UserDaoImpl();
    public static ProductDao productService = new ProductDaoImpl();
    boolean check = false;

    public List<CartProductsDto> getAllCarts() {
        List<CartProductsDto> cartProductsDtos = new ArrayList<>();
        cartProductsService.getAllCarts()
                .forEach(cart -> cartProductsDtos.add(cartProductsMapper.cartProductsToDto(cart)));

        if (cartProductsDtos.isEmpty()) {
            return null;
        }
        return cartProductsDtos;
    }

    public List<CartProductsDto> getAllCartProducts(int id) {
        User user = userService.getUser(id);

        if (user == null) {
            return null;
        }

        List<CartProductsDto> cartProductsDtos = new ArrayList<>();
        cartProductsService.getCartProductsList(id)
                .forEach(cart -> cartProductsDtos.add(cartProductsMapper.cartProductsToDto(cart)));

        if (cartProductsDtos.isEmpty()) {
            return null;
        }
        return cartProductsDtos;
    }

    public boolean addProductToCart(int userId,int productId, int quantity) {

        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return false;
        }
        cartProductsService.getCartProductsList(userId)
                .forEach(cart -> {
                    if (cart.getCartId().getProductId() == productId) {
                        check = true;
                    }
                });

        if (check) {
            check = false;
            return false;
        }
        CartProducts cartProducts = new CartProducts();
        cartProducts.setCartId(new CartId(userId, productId));
        cartProducts.setProduct(product);
        cartProducts.setUser(user);
        cartProducts.setQuantity(quantity);
        cartProducts.setTotalPrice((int) product.getPrice() * quantity);

        cartProductsService.addCartProduct(cartProducts);
        return true;

    }

    public boolean updateCartProduct(int userId, Product product) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }

        Product product2 = productService.getProductByName(product.getName());
        if (product2 == null) {
            return false;
        }

        cartProductsService.getCartProductsList(userId)
                .forEach(cart -> {
                    if (cart.getCartId().getProductId() == product2.getId()) {
                        check = true;
                    }
                });

        if (!check) {
            return false;
        }
        check = false;
        cartProductsService.updateProduct( product2.getId(), userId, product.getQuantity(), product.getPrice());
        return true;
    }


    public boolean deleteOrder(int userId, int productId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return false;
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return false;
        }
        cartProductsService.getCartProductsList(userId)
                .forEach(cart -> {
                    if (cart.getCartId().getProductId() == productId) {
                        check = true;
                    }
                });

                if (!check) {
                    return false;
                }
                check = false;
                CartIdDto cartIdDto = new CartIdDto(userId, productId);
                cartProductsService.removeCartProduct(cartIdDto);
                return true;
            }

}
