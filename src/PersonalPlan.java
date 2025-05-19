public class PersonalPlan extends Plan {
    protected String city;

    public PersonalPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit,
            MyDate expiryDate, String city) throws PlanException, PlanUserNameException {
        super(userName, id, handset, internetQuota, capLimit, expiryDate);
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void print() {
        super.print();
        System.out.println(" City: " + city);
    }

    public double calcPayment(int flatRate) {
        return handset.getPrice() / 24 + ((double) capLimit / 20) + ((double) internetQuota * 5) + flatRate;

    }

    // LAB4
    public PersonalPlan(PersonalPlan personalPlan) {
        super(personalPlan);
        this.city = personalPlan.city;
    }

    public PersonalPlan clone() throws CloneNotSupportedException {
        return (PersonalPlan) super.clone();
    }

    public void setCity(String city) {
        this.city = city;
    }
    // Lab6

    public String toDilimatedString() {
        return "P," + userName + "," + id + "," + handset.toDilimatedString() + "," + internetQuota + "," + capLimit
                + ","
                + expiryDate.toDilimatedString() + "," + city;
    }
}
