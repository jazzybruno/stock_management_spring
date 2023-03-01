package rw.jazzybruno.istock.v1.dto.Product;

import org.springframework.stereotype.Component;
import rw.jazzybruno.istock.v1.models.Product;

import java.util.function.Function;

@Component
public class ProductDTOMapper implements Function<Product , ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getProd_id(),
                product.getProdName(),
                product.getProdDescription(),
                product.getQuantity(),
                product.getUnitPrice()
        );
    }
}
