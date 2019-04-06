package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin") // 향후 admin이라는 request가 날라오면 여기서 받겠다. / class level에서 url지정
public class AdminController {
	// service layer를 설정
	@Autowired
	private ProductService productService;

	@RequestMapping // 추가적으로 method level에서 다시 추가적으로 requestMapping을 넣어준거 admin url넘어오면 이 메소드가 실행함
	public String adminPage() {
		return "admin"; // 여기 이름이 definition 이름임 따라서 tiles.xml에서 지정
	}

	@RequestMapping("/productInventory") // class level에서 admin을 넣었기때문에 method level에서는 admin을 안써도됨
	public String getProducts(Model model) { // dataBase를 조회해서 product정보를 가져와서 model에 저장후 view로 넘김
												// controller가 db에서 정보를 읽어서 model에 저장 -> view에서 display

		List<Product> products = productService.getProducts(); // db에서 products 정보를 가져옴
		model.addAttribute("products", products); // 가져온 값을 model에 addAttribute 메소드를 이용해서 저장

		return "productInventory";
	}

	@RequestMapping(value = "/ProductInventory/addProduct", method = RequestMethod.GET) // get Method만 처리하겠다
	public String addProduct(Model model) {
		// Spring form tag 다시보기
		Product product = new Product(); // product객체 생성 , 이 객체를 가지고 form을 채워 넣을 예정
		product.setCategory("컴퓨터"); // addProduct.jsp화면에서 category에 default값이 컴퓨터인 이유

		model.addAttribute("product", product); // 생선한 객체 product에 추가

		return "addProduct";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result) {
		// Model model -> Product product로 바꾸면 Spring에 의해서 product객체가 자동적으로 생성 , 자동 바인딩
		// 완료
		// 이 객체가 사용자가 입력한 data를 자동으로 넣어준다.// path에 value가 model에 값과 일치해야한다.
		// Spring form tag 다시보기
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addProduct";
		}
		
		if (!productService.addProduct(product)) {
			System.out.println("Adding product cannnot be done");
		}

		// return "productInventory"; //이렇게 넣어버리면 products라는 model에 들어가지 않아서 display가
		// 되지않는다.
		return "redirect:/admin/productInventory"; // redirect를 통해 productInventory에 가서 다시 model에 parameter를 넣음으로써 가능해짐

	}

	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET) // get Method만 처리하겠다
	public String deleteProduct(@PathVariable int id) { // url에 id값이 pathvariable 에 들어감

		if (!productService.deleteProduct(id)) {
			System.out.println("Deleting Product cannot be done");
		}
		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET) // get Method만 처리하겠다
	public String updateProduct(@PathVariable int id, Model model) { // update하려면 기존의 product를 확인해야함

		Product product = productService.getProductById(id);

		model.addAttribute("product", product);

		return "updateProduct";

	}

	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result) {
		
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateProduct";
		}
		
		//System.out.println(product);
		if (!productService.updateProduct(product)) {
			System.out.println("Updating product cannnot be done");
		}

		return "redirect:/admin/productInventory"; // redirect를 통해 productInventory에 가서 다시 model에 parameter를 넣음으로써 가능해짐

	}

}
