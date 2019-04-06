package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller // ProductController를 Bean으로 등록 container에서 관리해줌
public class ProductController { //controller -> service -> dao 를 호출해줌
	
	@Autowired //DI
	private ProductService productService;
	
	@RequestMapping("/products") //url mapping을 "/products"로 줌 
	public String getProduct(Model model) {
		
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products); // key, value 
		
		return "products"; // view's logical name 
		////여기에 products과 tiles.xml에 있는 definition name="products"이 일치해야한다
	}
	
	@RequestMapping("/productDetail/{productId}")
	public String productDetail(@PathVariable int productId, Model model) {
		
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);

	
		return "productDetail";
	}
	

}
