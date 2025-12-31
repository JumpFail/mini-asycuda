public class Duty {

    private final double rate;
    private final double amount;

    public Duty(double rate, double amount) {
        this.rate = rate;
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public double getAmount() {
        return amount;
    }
}

public class DutyCalculator {

    private final TariffService tariffService;

    public DutyCalculator(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    public Duty calculate(Item item) {
        double rate = tariffService.getDutyRate(item);
        double amount = item.getCustomsValue() * rate;

        return new Duty(rate, amount);
    }
}
