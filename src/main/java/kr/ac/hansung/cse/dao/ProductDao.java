package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository
public class ProductDao {

	private JdbcTemplate jdbcTemplate; // dao에서는 jdbcTemplate를 사용 jdbcTemplate dataSource를 사용

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource); // jdbc 객체 생성 dataSource는 autowired를 통해
	}

	public List<Product> getProducts() {

		String sqlStatement = "select * from product"; // record형태를 -> object로

		return jdbcTemplate.query(sqlStatement, new RowMapper<Product>() { // record갯수만큼 호출됨

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException { // mapRow가 record를 object로 해주는거임
 
				Product product = new Product();

				product.setId(rs.getInt("id")); // rs에서 id타입을 가진 것 을 읽어서 product에서 id를 setting함
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});
	}

	public boolean addProduct(Product product) {

		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();
		// id는 auto_increament로 정의해서 선언할 필요가없다

		String sqlStatement = "insert into product (name, category, price, manufacturer, unitInStock, description) values (?, ?, ?, ?, ?, ?)";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description }) == 1);

	}

	public boolean deleteProduct(int id) {

		String sqlStatement = "delete from product where id = ?";
		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);

	}

	public Product getProductById(int id) {

		String sqlStatement = "select * from product where id = ?"; // record형태를 -> object로

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id },
				new RowMapper<Product>() { // record갯수만큼 호출됨

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException { // mapRow가 record를 object로 해주는거임

				Product product = new Product();

				product.setId(rs.getInt("id")); // rs에서 id타입을 가진 것 을 읽어서 product에서 id를 setting함
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});

	}

	public boolean updateProduct(Product product) {
		
		int id = product.getId();
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();
		

		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, description=? where id = ?";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description, id}) == 1);

	}

}
