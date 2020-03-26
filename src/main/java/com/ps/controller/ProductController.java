package com.ps.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.model.Product;
import com.ps.model.Response;
import com.ps.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/product")
@CrossOrigin
@Api(tags = "Product", description = "Product Management")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Get All Product List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product List retrived Successfully") })
	public ResponseEntity<List<Product>> getAllProduct() {
		return ResponseEntity.ok(service.getAllProduct());
	}

	@PostMapping("/add")
	@ApiOperation("Add a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product added successfully") })
	public ResponseEntity<Response> addProduct(@RequestBody Product product) {
		service.addProduct(product);
		Response resp = new Response();
		resp.setStatus("Product added successfully");
		return ResponseEntity.ok(resp);
	}

	@PutMapping("/update/{productId}")
	@ApiOperation("Update a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Updated successfully"),
			@ApiResponse(code = 400, message = "Product Id Not Found") })
	public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestBody Product product) {

		try {
			return ResponseEntity.ok(service.updateProduct(UUID.fromString(productId), product));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{productId}")
	@ApiOperation("Delete a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product deleted successfully"),
			@ApiResponse(code = 400, message = "Product Id Not Found") })
	public ResponseEntity<?> deleteProduct(@PathVariable String productId) {

		try {
			service.removeProduct(UUID.fromString(productId));
			Response resp = new Response();
			resp.setStatus("Product deleted successfully");
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/get/{productId}")
	@ApiOperation("get a Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "get a Product"),
			@ApiResponse(code = 400, message = "Product Id Not Found") })
	public ResponseEntity<?> getProduct(@PathVariable String productId) {
		try {
			service.removeProduct(UUID.fromString(productId));
			return ResponseEntity.ok(service.getProductBuId(UUID.fromString(productId)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
