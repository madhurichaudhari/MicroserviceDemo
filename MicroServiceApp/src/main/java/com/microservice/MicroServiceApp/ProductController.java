package com.microservice.MicroServiceApp;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;



@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	private  List<Product> listofproduct;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	  @RequestMapping("/") 
	  public List<Product> getAllProduct() {
	  listofproduct = productService.listAll();
	 // model.addAttribute("listofproduct", listofproduct);
	  return listofproduct;
	    }
	  
		
	  @RequestMapping("/productId") 
	  public Product getProductId() {
	  listofproduct = productService.listAll();
	 // model.addAttribute("listofproduct", listofproduct);
	  return listofproduct.get(0);
	    }
	  
	/* Create HATEOAS(it includes link of other microservices) */
	  
	  
	  @GetMapping("/getUserHATEOAS/{id}")
	  public Resource<Product> getproductDetails(@PathVariable Long id ) {
		     Product productdetail =productService.findOne(id);
		  Resource<Product> resource=new Resource<Product>(productdetail);
		  
		  ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).getAllProduct());
		  ControllerLinkBuilder linkTo2=linkTo(methodOn(this.getClass()).getproductDetails(id));
		  
		  resource.add(linkTo.withRel("user_list"));
		  resource.add(linkTo2.withRel("user_list_details"));
		     
	          return resource;
	  }
	  
	  
	 
	  @PostMapping("/createUser") 
	  public Product createUser(@RequestBody Product product ) {
		  Product  productdetail =productService.save(product);
        return productdetail;
	  
	  }
	  
	  @DeleteMapping("/deleteUser/{id}")
	  public void productDeleteData(@PathVariable Long id ) {
		  
		  
		  productService.delete(id);
		  
		  
		  
		  }
	  @GetMapping("/getUser/{id}")
	  public Product productHomePage(@PathVariable Long id ) {
		     Product productdetail =productService.findOne(id);
	          return productdetail;
	  }
	  
	  
	
	  @PostMapping("/createUserWithResponseValidation") 
	  public  ResponseEntity<Object> createUserWithResponseValidation(@Valid @RequestBody Product product ) {
		  Product productdetail =productService.save(product);
	      URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
	    		                                                 
	      return ResponseEntity.created(uri).build();
	      }
	 
	 
	 
	 
	/*
	 * @GetMapping("/getUser{id}") public Product productHomePage(@PathVariable Long
	 * id ) { Product productdetail =productService.findOne(id);
	 * 
	 * if(productdetail==null) { new throw
	 * UserNotFoundException(id,"User Not Found"); }
	 * 
	 * return productdetail;
	 * 
	 * }
	 */
	/*
	 * @DeleteMapping("/deleteUser{id}") public Product productDelete(@PathVariable
	 * Long id ) { Product product=productService.delete(id); if(product==null) {
	 * throw new UserNotFoundException(id,"not found");
	 * 
	 * } }
	 */
	
	
	
	
	@RequestMapping("/product")
	 @HystrixCommand(fallbackMethod = "fallback_response" )
	public CurrencyConversionBean product(Model model) {
		List<Product> listofproduct = productService.listAll();
		model.addAttribute("listofproduct", listofproduct);
		
		System.out.println("listofproductmad"+listofproduct);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new
				  RestTemplate().getForEntity(
						  "http://10.110.95.12:8100/currency-converter/from/USD/to/INR/quantity/1" ,
				  CurrencyConversionBean.class);
		
		
		Product product = new RestTemplate().getForObject(
				"http://10.110.95.223:8081/", Product.class); 
		
		System.out.println("productmad"+product);
		
		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(),product.getName(),
				product.getBrand(),
				response.getQuantity(),response.getPort());
		
	
	}

	@RequestMapping(value = "/p")
	@HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
	   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
	})
	public String hello() throws InterruptedException {
	   Thread.sleep(10000);
	   return "Welcome Hystrix";
	}
	private CurrencyConversionBean fallback_response(Model m) {
	   return new CurrencyConversionBean("Request fails. It takes long time to response");
	}
	  public ResponseEntity<Product> defaultResponse() {
	        System.out.println("You are seeing this fallback response because the underlying microservice is down or has thrown an error!");
	 
	        Product fallbackItem = new Product();
	        fallbackItem.setName("Dummy Name");
	        fallbackItem.setBrand("fake");
	 
	        return new ResponseEntity<Product>(fallbackItem, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
	
	/*
	 * @GetMapping("/p") public String getP() {
	 * 
	 * if(RandomUtil.next) return "product"; }
	 */
	/*
	 * Iterator<Product> iterator=listofproduct.iterator();
	 * while(iterator.hasNext()) { Product product=iterator.next();
	 * if(product.getId().equals(id)) { iterator.remove(); return product;
	 * 
	 * }
	 */
	
	
}
