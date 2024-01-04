package at.ltb.apprenticedeliverysystem.core.product.exception;

public class ProductNameNotUniqueException extends RuntimeException {
    public ProductNameNotUniqueException(String message) {
        super(message);
    }
}
