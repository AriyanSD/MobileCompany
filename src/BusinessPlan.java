public class BusinessPlan extends Plan {
    protected int numberOfEmployees;
    protected int ABN;

    public BusinessPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit,
            MyDate expiryDate,
            int numberOfEmployees, int ABN) throws PlanException, PlanUserNameException {
        super(userName, id, handset, internetQuota, capLimit, expiryDate);
        this.numberOfEmployees = numberOfEmployees;
        this.ABN = ABN;
    }

    public int getABN() {
        return ABN;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void print() {
        super.print();
        System.out.println(" NumberOfEmployees: " + numberOfEmployees + " ABN: " + ABN);
    }

    public String toString() {
        return super.toString() + " Number Of Employees: " + numberOfEmployees + " ABN: " + ABN;
    }

    public double calcPayment(int flatRate) {
        double result = handset.getPrice() / 24 + (double) capLimit / 10 + (double) internetQuota * 10 + flatRate;
        if (numberOfEmployees > 10) {
            result += ((double) numberOfEmployees - 10) * 50;
        }
        return result;
    }

    // Lab4
    public BusinessPlan(BusinessPlan businessPlan) {
        super(businessPlan);
        this.numberOfEmployees = businessPlan.numberOfEmployees;
        this.ABN = businessPlan.ABN;
    };

    public BusinessPlan clone() throws CloneNotSupportedException {
        return (BusinessPlan) super.clone();
    }

    // Lab6
    public String toDilimatedString() {
        return "B," + userName + "," + id + "," + handset.toDilimatedString() + ","
                + internetQuota + "," + capLimit + ","
                + expiryDate.toDilimatedString() + "," + numberOfEmployees + "," + ABN;
    }
}
