package org.greenrivers.java.controller;

import io.swagger.annotations.*;
import org.greenrivers.java.transferobject.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@Api(value="/product", protocols="http", description="Operations pertaining to products in Online Store")
public class ProductController {
    private List<Product> dummyProducts = new ArrayList<>();

    @RequestMapping(value="list", method= RequestMethod.GET, produces = "application/json")
    @ApiOperation(value="View a list of products", response=Product.class, responseContainer="List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Successful retrieved list")
    })
    public List<Product> list() {
        return dummyProducts;
    }

    @RequestMapping(value="show/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value="get product by product id", response=Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Successful get product"),
            @ApiResponse(code = 404, message="product not found")
    })
    public Product show(
            @PathVariable("id")
            @ApiParam(value="product id") Long id, HttpServletResponse response) {
        if(id > 0) {
            return new Product(1L, "Test", BigDecimal.valueOf(109, 2));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @RequestMapping(value="/", method=RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Add a product")
    public ResponseEntity saveProduct(@RequestBody Product product) {
        dummyProducts.add(product);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Update a product")
    public ResponseEntity update(
            @PathVariable("id")
            @ApiParam(value="product id") Long id, @RequestBody Product product) {
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "delete a product")
    public ResponseEntity delete(
            @PathVariable("id")
            @ApiParam(value="product id") Long id) {
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }
}
