
public class Main {
    public static void main(String[] args) throws PlanException, PlanUserNameException {

        try {
            MobileCompany mobileCompany1 = new MobileCompany("NakhlCityt", "ar12",
                    "ari00", TestCode.flatRate);
            UserInterface.mainMenu(mobileCompany1);
        } catch (CloneNotSupportedException e) {
            System.err.println("Exception in Cloning");
        }

    }
}