public class Equipment {
    private String id;
    private String name;
    private String category;
    private int totalQuantity;
    private int availableQuantity;
    private String condition;

    public Equipment(String id, String name, String category,
                     int totalQuantity, String condition) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
        this.condition = condition;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getTotalQuantity() { return totalQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
    public String getCondition() { return condition; }

    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }
    public void setCondition(String condition) { this.condition = condition; }

    public void borrow(int quantity) {
        availableQuantity -= quantity;
    }

    public void returnItems(int quantity) {
        availableQuantity += quantity;
    }

    public String getStatus() {
        if (availableQuantity == 0) return "Fully Borrowed";
        if (availableQuantity < totalQuantity) return "Partially Borrowed";
        return "Available";
    }

    @Override
    public String toString() {
        return String.format("%-8s %-20s %-15s %4d %4d %-15s %-15s",
                id, name, category, totalQuantity, availableQuantity,
                condition, getStatus());
    }
}
