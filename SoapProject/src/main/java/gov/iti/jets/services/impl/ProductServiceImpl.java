package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.ProductDao;
import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.persistence.impl.ProductDaoImpl;
import gov.iti.jets.persistence.util.mappers.ProductMapper;
import gov.iti.jets.presentation.dtos.ProductDto;
import gov.iti.jets.services.ProductService;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "gov.iti.jets.services.ProductService")
public class ProductServiceImpl implements ProductService {

    @Override
    public boolean addProduct(ProductDto productDto) {
        try {
            ProductDao productDao = new ProductDaoImpl();
            Product product = ProductMapper.INSTANCE.productDtoToEntity(productDto);
            ProductDto product2 = ProductMapper.INSTANCE.productToDto(productDao.getProductByName(product.getName()));
            if (product2 == null) {
                productDao.addProduct(product);
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Long getNoOfRecords() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getNoOfRecords();
    }

    @Override
    public ProductDto getProductById(int id) {
        ProductDto dto = null;
        try {
            ProductDao productDao = new ProductDaoImpl();
            Product product = productDao.getProductById(id);
            if (product == null) {
               return null;
            }
            dto = ProductMapper.INSTANCE.productToDto(product);
            return dto;
        } catch (Exception e) {
            return null;
        }

    }

    public boolean removeProduct(int id) {
        try {
            ProductDao productDao = new ProductDaoImpl();
            ProductDto product = ProductMapper.INSTANCE.productToDto(productDao.getProductById(id));
            if (product == null) {
                return false;
            }
            productDao.removeProduct(id);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean editProduct(ProductDto product, String name) {
        try {
            ProductDao productDao = new ProductDaoImpl();
            ProductDto product2 = ProductMapper.INSTANCE.productToDto(productDao.getProductByName(product.getName()));

            if (product2 == null) {
                return false;
            }
            productDao.editProduct(product, product2.getName());
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> getAllProducts() {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getAllProducts();

    }

    @Override
    public Product getProductByName(String name) {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getProductByName(name);
    }
}
