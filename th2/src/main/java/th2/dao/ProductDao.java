package th2.dao;

import java.util.List;

import th2.model.Product;

public interface ProductDao {
	List<Product> getProducts();
	void addProduct(Product product);
	Boolean exitsProduct(String code);
	void deleteProduct(String code);
	Product getProduct(String code);
	void updateProduct(Product product);
}
