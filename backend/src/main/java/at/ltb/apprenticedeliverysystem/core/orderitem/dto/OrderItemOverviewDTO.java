package at.ltb.apprenticedeliverysystem.core.orderitem.dto;

public record OrderItemOverviewDTO(String id, Integer orderedQuantity, String productName, Double oldProductPrice) {
}
