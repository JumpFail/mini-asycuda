import java.util.Objects;
import java.util.logging.Logger;

public class CustomsOfficer {
    private static final Logger LOG = Logger.getLogger(CustomsOfficer.class.getName());
    public enum OfficerRank { JUNIOR, SENIOR, SUPERVISOR, DIRECTOR }
    
    private final String officerId;
    private String officerName;
    private OfficerRank officerRank;

    public CustomsOfficer(String officerId, String officerName, OfficerRank officerRank) {
        this.officerId = Objects.requireNonNull(officerId, "Officer ID required");
        this.officerName = Objects.requireNonNull(officerName, "Officer name required");
        this.officerRank = Objects.requireNonNull(officerRank, "Officer rank required");
    }

    public String getOfficerId() {
        return officerId;
    }
    public String getOfficerName() {
        return officerName;
    }
    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public OfficerRank getOfficerRank() {
        return officerRank;
    }
    public void setOfficerRank(OfficerRank officerRank) {
        this.officerRank = Objects.requireNonNull(officerRank, "Officer rank required");
    }

    public void inspectShipment(Shipment shipment, DutyCalculator dutyCalculator) {
        inspectShipment(shipment, dutyCalculator, 1000.0);
    }

    public void inspectShipment(Shipment shipment, DutyCalculator dutyCalculator, double dutyThreshold) {
        Objects.requireNonNull(shipment, "Shipment required");
        Objects.requireNonNull(dutyCalculator, "Duty calculator required");
        
        for (Item item : shipment.getItems()) {
            Objects.requireNonNull(item, "Item in shipment is null");
            
            Duty duty = dutyCalculator.calculate(item);
            double calculatedDuty = duty.getAmount();

            LOG.info(String.format("Item: %s | Duty: $%.2f", item.getItemName(), calculatedDuty));
            
            // Auto-clear items below threshold, detain others
            if (calculatedDuty < dutyThreshold) {
                item.clear();
            } else {
                item.detain();
            }
        }
    }

    public void approveShipment(Shipment shipment) {
        Objects.requireNonNull(shipment, "Shipment required");
        if (officerRank != OfficerRank.SUPERVISOR) {
            throw new SecurityException("Only supervisors can approve shipments");
        }
        shipment.setStatus(Shipment.ShipmentStatus.CLEARED);
        LOG.info(String.format("Officer %s approved shipment %s", officerId, shipment.getShipmentId()));
    }

    public void rejectShipment(Shipment shipment) {
        Objects.requireNonNull(shipment, "Shipment required");
        if (officerRank != OfficerRank.SUPERVISOR) {
            throw new SecurityException("Only supervisors can reject shipments");
        }
        shipment.setStatus(Shipment.ShipmentStatus.HELD);
        LOG.info(String.format("Officer %s rejected shipment %s", officerId, shipment.getShipmentId()));
    }

    @Override
    public String toString() {
        return "CustomsOfficer{" +
                "officerId='" + officerId + '\'' +
                ", officerName='" + officerName + '\'' +
                ", officerRank=" + officerRank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomsOfficer that = (CustomsOfficer) o;
        return Objects.equals(officerId, that.officerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officerId);
    }
}
