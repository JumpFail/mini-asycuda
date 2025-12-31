public class Item {
    private final String itemId;
    private String itemName;
    private String hsCode;
    private double itemPrice;
    private double itemQuantity;
    private String unit;
    private double itemWeight;
    private String countryOfOrigin;

    public enum InspectionStatus{ PENDING, CLEARED, DETAINED, REJECTED }
    private InspectionStatus inspectionStatus;

    public Item(String itemId, String itemName, String hsCode, double itemPrice, double itemQuantity, String unit, double itemWeight, String countryOfOrigin) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.hsCode = hsCode;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.unit = unit;
        this.itemWeight = itemWeight;
        this.countryOfOrigin = countryOfOrigin;
        this.inspectionStatus = InspectionStatus.PENDING;
    }

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }
    public String getHsCode() {
        return hsCode;
    }
    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }
    public double getItemQuantity() {
        return itemQuantity;
    }
    public void setItemQuantity(double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public double getItemWeight() {
        return itemWeight;
    }
    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }
    public InspectionStatus getInspectionStatus() {
        return inspectionStatus;
    }
    public void setInspectionStatus(InspectionStatus inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public double getCustomsValue() {
        return itemPrice * itemQuantity;
    }

    public void clear(){
        this.inspectionStatus = InspectionStatus.CLEARED;
    }

    public void detain(){
        this.inspectionStatus = InspectionStatus.DETAINED;
    }

    public void reject(){
        this.inspectionStatus = InspectionStatus.REJECTED;
    }

    @Override
    public String toString() {
        return itemName +
               " | HS: " + hsCode +
               " | Value: $" + getCustomsValue() +
               " | Origin: " + countryOfOrigin +
               " | Status: " + inspectionStatus;
    }
}
