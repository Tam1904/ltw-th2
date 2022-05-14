package th2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th2.model.Product;

@Repository
public class ProductDaoRepository implements ProductDao {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Product> getProducts() {
		String sql = "select * from product";
		return jdbc.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	@Override
	public void addProduct(Product product) {
		String sql = "insert into product value(?,?,?)";
		jdbc.update(sql,product.getCode(),product.getDescription(),product.getPrice());
	}

	@Override
	public Boolean exitsProduct(String code) {
		String sql = "select * from product where code = ?";
		List<Product> products = jdbc.query(sql, new BeanPropertyRowMapper<Product>(Product.class),code);
		if(products.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteProduct(String code) {
		String sql = "delete from product where code = ?";
		jdbc.update(sql,code);
	}

	@Override
	public Product getProduct(String code) {
		String sql = "select * from product where code = ?";
		Product product = jdbc.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class),code);
		return product;
	}

	@Override
	public void updateProduct(Product product) {
		String sql = "update product set description = ?, price = ? where code = ?";
		jdbc.update(sql,product.getDescription(),product.getPrice(),product.getCode());
	}

}
