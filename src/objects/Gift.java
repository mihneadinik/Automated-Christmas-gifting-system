package objects;

import enums.Category;

public final class Gift {
    private String productName;
    private Double price;
    private Category category;
    private Integer quantity;

    public Gift(final String productName, final Double price,
                final Category category, final Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Gift(final Gift otherGift) {
        this.productName = otherGift.getProductName();
        this.price = otherGift.getPrice();
        this.category = otherGift.getCategory();
        this.quantity = otherGift.getQuantity();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
