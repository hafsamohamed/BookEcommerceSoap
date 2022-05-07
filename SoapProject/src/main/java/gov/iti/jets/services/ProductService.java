package gov.iti.jets.services;

import gov.iti.jets.persistence.entities.Product;
import gov.iti.jets.presentation.dtos.ProductDto;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ProductService {
    public boolean addProduct(ProductDto productDto);
    public ProductDto getProductById(int id);
    public Product getProductByName(String name);

    public Long getNoOfRecords();

    public boolean removeProduct(int id);
    public boolean editProduct(ProductDto product, String name);
    public List<Product> getAllProducts();


}
