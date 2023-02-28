package rw.jazzybruno.istock.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.jazzybruno.istock.v1.dto.Product.CreateProductDTO;
import rw.jazzybruno.istock.v1.dto.Product.ProductDTOMapper;
import rw.jazzybruno.istock.v1.dto.User.CreateUserDTO;
import rw.jazzybruno.istock.v1.models.Product;
import rw.jazzybruno.istock.v1.models.User;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import rw.jazzybruno.istock.v1.repositories.ProductRepository;
import rw.jazzybruno.istock.v1.services.ProductService;
import rw.jazzybruno.istock.v1.utils.Hash;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository ;
    private final ProductDTOMapper productDTOMapper;
    @Override
    public ResponseEntity<ApiResponse> getAllProducts() throws Exception {
        try {
            List<Product> products = productRepository.findAll();
            return  ResponseEntity.status(500).body(new ApiResponse(
                    true,
                    "Successfully fetched the products",
                    products.stream().map(productDTOMapper).collect(Collectors.toList())
            ));

        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(
                    false,
                    "Failed to fetch the products"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getProductById(Long prod_id) throws Exception {
        if(productRepository.existsById(prod_id)){
            try {
                Optional<Product> product = productRepository.findById(prod_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully fetched the products",
                        product.map(productDTOMapper)
                ));
            }catch (Exception e){
                return ResponseEntity.status(500).body(new ApiResponse(
                        false,
                        "Failed to fetch the products"
                ));
            }
        }else{
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The product with the id:" + prod_id + " does not exist"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createProduct(CreateProductDTO createProductDTO) throws Exception {
        Product product1 = productRepository.findProductByProdName(createProductDTO.getProdName()).get();
        if(product1 == null){
          Product product = new Product(
                  createProductDTO.getProdName(),
                  createProductDTO.getProdDescription(),
                  createProductDTO.getQuantity(),
                  createProductDTO.getUnitPrice()
          );

            try {
                productRepository.save(product);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully saved the product",
                        product
                ));
            }catch (Exception e){
                return ResponseEntity.status(500).body(new ApiResponse(
                        false,
                        "Failed to create the product"
                ));
            }
        }else{
            return ResponseEntity.status(401).body(new ApiResponse(
                    false,
                    "The product with the name:" + createProductDTO.getProdName() + " already exists"
            ));
        }
    }

    public void updateProductMapper(Optional<Product> product, CreateProductDTO createProductDTO){
        product.get().setQuantity(createProductDTO.getQuantity());
        product.get().setProdName(createProductDTO.getProdName());
        product.get().setUnitPrice(createProductDTO.getUnitPrice());
        product.get().setProdDescription(createProductDTO.getProdDescription());
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> updateProduct(Long prod_id, CreateProductDTO createProductDTO) throws Exception {
        if(productRepository.existsById(prod_id)){
            Optional<Product> product = productRepository.findById(prod_id);
            updateProductMapper(product, createProductDTO);
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully updated the user",
                    product.map(productDTOMapper)
            ));
        }else{
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The product with the id:" + prod_id + " does not exist"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteProduct(Long prod_id) throws Exception {
        if (productRepository.existsById(prod_id)) {
            Optional<Product> product = productRepository.findById(prod_id);
            productRepository.deleteById(prod_id);
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully deleted the product",
                    product.map(productDTOMapper)
            ));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The product with the id:" + prod_id + " does not exist"
            ));
        }
    }
    }

