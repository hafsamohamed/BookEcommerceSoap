package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products", catalog = "bookecommercesoap")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", length = 10)
    private int id;

    @Column(name = "product_name", length = 25)
    private String name;

    @Column(name = "product_price", length = 10)
    private double price;

    @Column(name = "product_quantity", length = 10)
    private int quantity;

    @Column(name = "product_description", length = 1500)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", orphanRemoval = true)
    private List<CartProducts> cartProductsList = new ArrayList<>();

    public Product() {
    }

    public Product(String name, double price, int quantity, String description) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CartProducts> getCartProductsList() {
        return cartProductsList;
    }

    public void setCartProductsList(List<CartProducts> cartProductsList) {
        this.cartProductsList = cartProductsList;
    }

    @Override
    public String toString() {
        return "Product [cartProductsList=" + cartProductsList + ", description=" + description + ", id=" + id
                + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }

    
}
