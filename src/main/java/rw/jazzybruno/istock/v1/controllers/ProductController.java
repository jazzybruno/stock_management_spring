package rw.jazzybruno.istock.v1.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.jazzybruno.istock.v1.dto.Product.CreateProductDTO;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import rw.jazzybruno.istock.v1.serviceImpls.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() throws Exception{
        return productService.getAllProducts();
    }

    @GetMapping("/id/{prod_id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long prod_id) throws Exception{
        return productService.getProductById(prod_id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody CreateProductDTO createProductDTO) throws Exception{
        return productService.createProduct(createProductDTO);
    }

    @PutMapping("/update/{prod_id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long prod_id  ,  @RequestBody CreateProductDTO createProductDTO) throws Exception{
        return productService.updateProduct(prod_id , createProductDTO);
    }

    @DeleteMapping("/delete/{prod_id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long prod_id) throws Exception{
        return productService.deleteProduct(prod_id);
    }
}
