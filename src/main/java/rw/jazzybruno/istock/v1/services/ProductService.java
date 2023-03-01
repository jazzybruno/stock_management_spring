package rw.jazzybruno.istock.v1.services;

import org.springframework.http.ResponseEntity;
import rw.jazzybruno.istock.v1.dto.Product.CreateProductDTO;
import rw.jazzybruno.istock.v1.dto.User.CreateUserDTO;
import rw.jazzybruno.istock.v1.payload.ApiResponse;

public interface ProductService {
    public ResponseEntity<ApiResponse> getAllProducts() throws Exception;
    public ResponseEntity<ApiResponse> getProductById(Long prod_id) throws Exception;
    public ResponseEntity<ApiResponse> createProduct(CreateProductDTO createProductDTO) throws Exception;
    public ResponseEntity<ApiResponse> updateProduct(Long prod_id ,  CreateProductDTO createProductDTO) throws Exception;
    ResponseEntity<ApiResponse> deleteProduct(Long prod_id) throws Exception;
}
