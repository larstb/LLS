package at.ltb.apprenticedeliverysystem.core.category.exception;

public class CategoryNameNotUniqueException extends RuntimeException {
    public CategoryNameNotUniqueException(String message) {
        super(message);
    }
}
