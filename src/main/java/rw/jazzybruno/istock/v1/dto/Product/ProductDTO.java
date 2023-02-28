package rw.jazzybruno.istock.v1.dto.Product;

public record ProductDTO(
        Long prod_id,
        String prodName,
        String prodDescription,
        int quantity,
        int unitPrice
) {
}
