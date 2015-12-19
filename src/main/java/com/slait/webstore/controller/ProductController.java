package com.slait.webstore.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.slait.webstore.entity.Product;
import com.slait.webstore.exception.NoProductsFoundUnderCategoryException;
import com.slait.webstore.exception.ProductNotFoundException;
import com.slait.webstore.service.ProductService;
import com.slait.webstore.validator.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductValidator productValidator;

	@Autowired
	private ProductService productService;

	@Autowired
	private SessionFactory sessionFactory;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/all")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model,
			@PathVariable("category") String category) {
		List<Product> products = productService.getProductsByCategory(category);
		if (products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}

	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams,
			Model model) {
		model.addAttribute("products",
				productService.getProductByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	@RequestMapping("/tablet/{price}")
	public String filterProducts(
			@MatrixVariable(pathVar = "price") Map<String, List<String>> filterParams,
			@RequestParam("manufacturer") String manufacturer, Model model) {
		model.addAttribute("products",
				productService.getProductByFilter(filterParams));
		return "products";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(
			@ModelAttribute("newProduct") @Valid Product productToBeAdded,
			BindingResult result, HttpServletRequest request,
			@RequestParam("productImage") MultipartFile productImage,
			@RequestParam("productManual") MultipartFile productManual) {

		if (result.hasErrors()) {
			System.out.println("Number of errors: " + result.getFieldErrorCount());
			return "addProduct";
		}

		System.out.println("Name: " + productToBeAdded.getName());
		System.out.println("Desc: " + productToBeAdded.getDescription());
		System.out.println("Image: " + productImage.getName());
		System.out.println("Manual: " + productManual.getName());

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bing disallowed " + "fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		if (productImage != null) {
			try {
//				Blob imageBlob = Hibernate.getLobCreator(sessionFactory
//						.getCurrentSession()).createBlob(productImage.getBytes());
				productToBeAdded.setProductImage(productImage);
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed ", e);
			}
		}
		
		if (productManual != null) {
			try {
//				Blob manualBlob = Hibernate.getLobCreator(sessionFactory
//						.getCurrentSession()).createBlob(productManual.getBytes());
				productToBeAdded.setProductManual(productManual);
			} catch (Exception e) {
				throw new RuntimeException("Product Manual saving failed ", e);
			}
		}

		productService.addProduct(productToBeAdded);
		return "redirect:/products";
	}

	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setValidator(productValidator);
		binder.setDisallowedFields("unitsInOrder", "discontinued");
		binder.setAllowedFields("productId", "name", "unitPrice", "description",
				"manufacturer", "category", "unitsInStock", "productImage",
				"productManual", "condition", "language");
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req,
			ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}

}
