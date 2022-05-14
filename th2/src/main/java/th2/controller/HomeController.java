package th2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import th2.dao.ProductDao;
import th2.model.Product;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ProductDao pDao;

	@GetMapping
	public String Index() {
		return "index";
	}
	
	@GetMapping("/view")
	public String View(Model model) {
		List<Product> products = pDao.getProducts();
		model.addAttribute("products", products);
		return "product";
	}
	
	@GetMapping("/add")
	public String Add(@ModelAttribute("type")String type,Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("type", type);
		return "update";
	}
	
	@GetMapping("/edit")
	public String Edit(@ModelAttribute("code")String code ,@ModelAttribute("type")String type,Model model) {
		Product product = pDao.getProduct(code);
		model.addAttribute("product", product);
		model.addAttribute("type", type);
		return "update";
	}
	
	@GetMapping("/delete")
	public String Delete(@ModelAttribute("code")String code,Model model) {
		Product product = pDao.getProduct(code);
		model.addAttribute("product", product);
		return "delete";
	}
		
	@PostMapping("/UpdateProduct")
	public String UpdateProduct(HttpServletRequest request, Model model) {
		String code = (String) request.getParameter("code");
		String description = (String) request.getParameter("description");
		String price = (String) request.getParameter("price");
		String type = (String) request.getParameter("type");
		String messOne=null,messTwo=null,messThree=null;
		int OK=1,exit = 0;
		if(request.getParameter("update")!=null) {
			if(code==null || code.isEmpty()) {
				messOne = "Nhập code sản phẩm";
				OK=0;
			}
			else if(type.equals("Add") && pDao.exitsProduct(code)) {
				messOne = "Sản phẩm đã tồn tại";
				OK=0;
				exit=1;
			}
			if(description==null || description.isEmpty()) {
				messTwo = "Nhập description sản phẩm";
				OK=0;
			}
			
			if(price==null || price.isEmpty()) {
				messThree = "Nhập price sản phẩm";
				OK=0;
			}
			
			try {
				Float.parseFloat(price);
			} catch (Exception e) {
				messThree = "Giá trị Price kiểu Float";
				OK=0;
			}
			
			if(OK==0) {
				model.addAttribute("messOne",messOne);
				model.addAttribute("messTwo",messTwo);
				model.addAttribute("messThree",messThree);
				if(exit==1) {
					model.addAttribute("product", new Product(code, description, Float.parseFloat(price)));
				}
				else {
					model.addAttribute("product", new Product(code, description, 0.0));
				}
				model.addAttribute("type", type);
				return "update";
			}
			else {
				if(type.equals("Add")) {	
					pDao.addProduct(new Product(code, description, Float.parseFloat(price)));
				}
				else {
					pDao.updateProduct(new Product(code, description, Float.parseFloat(price)));
				}
			}
			return "redirect:/view";
		}
		return "redirect:/view";
	}
	
	@PostMapping("/DeleteProduct")
	public String DelteteProduct(@RequestParam("code")String code, HttpServletRequest request) {
		if(request.getParameter("yes")!=null) {
			pDao.deleteProduct(code);
		}
		return "redirect:/view";
	}
}
