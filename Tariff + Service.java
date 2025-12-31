public class Tariff {

    private final String hsCode;
    private final double importDutyRate;
    private final double vatRate;
    private final double exciseRate;

    public Tariff(String hsCode, double importDutyRate, double vatRate, double exciseRate) {
        this.hsCode = hsCode;
        this.importDutyRate = importDutyRate;
        this.vatRate = vatRate;
        this.exciseRate = exciseRate;
    }

    public String getHsCode() {
        return hsCode;
    }

    public double getImportDutyRate() {
        return importDutyRate;
    }

    public double getVatRate() {
        return vatRate;
    }

    public double getExciseRate() {
        return exciseRate;
    }
}


import java.util.HashMap;
import java.util.Map;

public class TariffService {

    private final Map<String, Tariff> tariffTable = new HashMap<>();

    public TariffService() {
        tariffTable.put("85", new Tariff("85", 0.20, 0.15, 0.00));
        tariffTable.put("61", new Tariff("61", 0.15, 0.15, 0.00));
    }

    public Tariff getTariff(String hsCode) {
        return tariffTable.get(hsCode.substring(0, 2));
    }

    public double getDutyRate(Item item) {
        String hsCode = item.getHsCode();

        if (hsCode.startsWith("85")) {        // Electronics
            return 0.20;
        } else if (hsCode.startsWith("61")) { // Clothing
            return 0.15;
        } else if (hsCode.startsWith("02")) { // Food
            return 0.05;
        }

        return 0.10; // default rate
    }
}
