import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Shipment {
    private final String shipmentId;
    private List<Item> items;
    private String origin;
    private String destination;
    public enum ShipmentStatus { PENDING, IN_TRANSIT, CLEARED, HELD, RELEASED }
    private ShipmentStatus status;

    public Shipment(String shipmentId, List<Item> items, String origin, String destination) {
        this.shipmentId = Objects.requireNonNull(shipmentId, "Shipment ID required");
        this.items = (items == null) ? new ArrayList<>() : new ArrayList<>(items);
        this.origin = Objects.requireNonNull(origin, "Origin required");
        this.destination = Objects.requireNonNull(destination, "Destination required");
        this.status = ShipmentStatus.PENDING;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
    public void setItems(List<Item> items) {
        this.items = (items == null) ? new ArrayList<>() : new ArrayList<>(items);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = Objects.requireNonNull(origin, "Origin required");
        if (origin.trim().isEmpty()) {
            throw new IllegalArgumentException("Origin cannot be empty");
        }
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = Objects.requireNonNull(destination, "Destination required");
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public void addItem(Item item) {
        Objects.requireNonNull(item, "Item cannot be null");
        items.add(item);
    }

    public void removeItem(Item item) {
        Objects.requireNonNull(item, "Item cannot be null");
        items.remove(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shipment{id='").append(shipmentId).append("', origin='").append(origin)
          .append("', destination='").append(destination).append("', status=").append(status).append(", items=[");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            sb.append("\n  ").append(item.getItemName()).append(" | Price: $").append(item.getItemPrice())
              .append(" | Weight: ").append(item.getItemWeight()).append("kg | Category: ").append(item.getHsCode())
              .append(" | Inspection: ").append(item.getInspectionStatus());
        }
        sb.append("\n]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(shipmentId, shipment.shipmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipmentId);
    }
}
