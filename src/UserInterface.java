import javax.sound.sampled.SourceDataLine;

public class UserInterface {
    public static void displayMainMenu() {
        System.out.println("____Main Menu____");
        System.out.println("1-Admin Login");
        System.out.println("2-User Login");
        System.out.println("3-Exit");
    }

    public static void mainMenu(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, PlanUserNameException {
        int choice = 0;
        while (choice != 3) {
            displayMainMenu();
            choice = UIMethods.getInt("Choose an option: ");
            switch (choice) {
                case 1:
                    adminLogin(mobileCompany);
                    break;
                case 2:
                    userLogin(mobileCompany);
                    break;
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Wrong choice Please choose a number between 1-3");
            }
        }
    }

    // Admin
    // UI----------------------------------------------------------------------------------------------------
    public static void adminLogin(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, PlanUserNameException {
        String userName = UIMethods.getString("Enter your UserName:");
        String passWord = UIMethods.getString("Enter your PassWord:");
        if (mobileCompany.validateAdmin(userName, passWord)) {
            System.out.println("successfully logged in");
            adminMenu(mobileCompany);
        } else {
            System.out.println("Log in was not successful");
        }
    }

    public static void displayAdminMenu() {
        System.err.println("_____Admin Menu_____");
        System.out.println("1-Test Code");
        System.out.println("2-Create User");
        System.out.println("3-Create Personal Plan");
        System.out.println("4-Create Business Plan");
        System.out.println("5-Print User Information");
        System.out.println("6-Filter By Mobile Model All PLans");
        System.out.println("7-Filter By Expiry Date for A User Plans");
        System.out.println("8-Update Address");
        System.out.println("9-Data aggregation menu");
        System.out.println("10-Remove plan from user");
        System.out.println("11-Change your password");
        System.out.println("12-Filter Plans By Expiry Date for each user");
        System.out.println("13-Sort Users By Monthly Patments");
        System.out.println("14-Sort Users By City Name");
        System.out.println("15-Save & Load");
        System.out.println("16-Log Out");

    }

    public static void adminMenu(MobileCompany mobileCompany)
            throws CloneNotSupportedException, PlanException, PlanUserNameException {
        int option = 0;
        while (option != 16) {
            displayAdminMenu();
            option = UIMethods.getInt("Choose an option(1 to 16): ");
            switch (option) {
                case 1:
                    TestCode.TestCodeBody(mobileCompany);
                    break;
                case 2:
                    creatUserMenu(mobileCompany);
                    break;
                case 3:
                    createPersonalPlan(mobileCompany);
                    break;
                case 4:
                    creatBusinessPlan(mobileCompany);
                    break;
                case 5:
                    printUser(mobileCompany);
                    break;
                case 6:
                    filterByMobileModel(mobileCompany);
                    break;
                case 7:
                    filterByExpiryDate(mobileCompany);
                    break;
                case 8:
                    updateUserAddress(mobileCompany);
                    break;
                case 9:
                    adminDataAggregationMenu(mobileCompany);
                    break;
                case 10:
                    removePlan(mobileCompany);
                    break;
                case 11:
                    changePassword(mobileCompany);
                    break;
                case 12:
                    filterByExpiryDateEachUser(mobileCompany);
                    break;
                case 13:
                    sortUsersByMonthlypayments(mobileCompany);
                    break;
                case 14:
                    sortUsersByCityName(mobileCompany);
                    break;
                case 15:
                    saveAndLoadCompanyMenu(mobileCompany);
                    break;
                case 16:
                    System.out.println("Logged out");
                    break;
                default:
                    System.out.println("That option doesn't exist try a number within 1-16");
            }
            pressAnyKeyToContinue();
        }

    }

    public static void displayAdminDataAggregationMenu() {
        System.out.println("___User Menu___");
        System.out.println("1-Print Report per cities");
        System.out.println("2-Print Report per MobileModels");
        System.out.println("3-Back to Admin Menu");
    }

    public static void adminDataAggregationMenu(MobileCompany mobileCompany) {
        int choice = 0;
        while (choice != 3) {
            displayAdminDataAggregationMenu();
            choice = UIMethods.getInt("Choose an option: ");
            switch (choice) {
                case 1:
                    mobileCompany.reportPaymentPerCity();
                    break;
                case 2:
                    mobileCompany.reportPaymentsPerMobileModel();
                    break;
                case 3:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Wrong choice Please choose a number between 1-3");
            }
            pressAnyKeyToContinue();
        }
    }

    public static void displayCreatUserMenu() {
        System.out.println("1-Creat User by manual input ID");
        System.out.println("2-Creat User by unique autogenerate ID");
        System.out.println("3-Back to admin menu");
    }

    public static void creatUserMenu(MobileCompany mobileCompany) {
        int choice = 0;
        while (choice != 3) {
            displayCreatUserMenu();
            choice = UIMethods.getInt("Choose an option: ");
            switch (choice) {
                case 1:
                    creatUserManualId(mobileCompany);
                    break;
                case 2:
                    creatUserAutoIdGenerate(mobileCompany);
                    break;
                case 3:
                    System.out.println("Back to admin Menu");
                    break;
                default:
                    System.out.println("Wrong choice Please choose a number between 1-3");
            }
            pressAnyKeyToContinue();
        }
    }

    public static void saveAndLoadCompanyMenu(MobileCompany mobileCompany) {
        System.out.println(
                "1-Save to Bineryfiles" + '\n' + "2-Load From Binary File" + '\n' + "3- Save to textFile" + '\n'
                        + "4-Load From textFile" + '\n' + "5-Back To Menu");
        int choice = 0;
        while (choice != 5) {
            choice = UIMethods.getInt("Enter an Option: ");
            switch (choice) {
                case 1:
                    saveCompanyToBinary(mobileCompany);
                    break;
                case 2:
                    loadCompanyFromBinary(mobileCompany);
                    break;
                case 3:
                    saveCompanyToText(mobileCompany);
                    break;
                case 4:
                    loadCompanyFromText(mobileCompany);
                    break;
                case 5:
                    System.out.println("Back To Menu");
                    break;
                default:
                    System.out.println("Wrong Entry");
                    break;
            }
            pressAnyKeyToContinue();
        }
    }
    // User
    // UI---------------------------------------------------------------------------------------

    public static void userLogin(MobileCompany mobileCompany) throws CloneNotSupportedException {
        int userID = UIMethods.getInt("Please enter your ID:");
        User user = mobileCompany.findUser(userID);
        if (user != null) {
            String password = UIMethods.getString("Please Enter your Password");
            if (user.userVaildate(userID, password)) {
                System.out.println("Welcome " + user.getName());
                userMenu(user, mobileCompany.getFlatRate());
            } else {
                System.out.println("Wrong Password For this User");
            }

        } else {
            System.out.println("User with given ID does not exist , back to menu");
        }
    }

    public static void displayUserMenu() {
        System.out.println("___User Menu___");
        System.out.println("1-Print Your basics Information");
        System.out.println("2-Create Personal Plan");
        System.out.println("3-Create Business Plan");
        System.out.println("4-Print All Plans with Monthly Payments");
        System.out.println("5-Change the Address");
        System.out.println("6-Filter your plans by mobile model");
        System.out.println("7-Filter your plans by expiry date");
        System.out.println("8-Report monthly payments per Mobile Models");
        System.out.println("9-Remove a plan");
        System.out.println("10-Save and Load");
        System.out.println("11-Sort Plans By Date");
        System.out.println("12-Log Out");
    }

    public static void userMenu(User user, int flatRate) throws CloneNotSupportedException {
        int choice = 0;

        while (choice != 12) {
            displayUserMenu();
            choice = UIMethods.getInt("Choose an option between 1-12");

            switch (choice) {
                case 1:
                    user.printBasics();
                    break;
                case 2:
                    creatPersonalPlan(user);
                    break;
                case 3:
                    creatBusinessPlan(user);
                    break;
                case 4:
                    user.printPlans(flatRate);
                    break;
                case 5:
                    UIMethods.updateUserAddress(user);
                    break;
                case 6:
                    filterByMobileModel(user);
                    break;
                case 7:
                    filterByMobileModel(user);
                    break;
                case 8:
                    user.reportPaymentsPerMobileModel();
                    break;
                case 9:
                    removePlan(user);
                    break;
                case 10:
                    saveAndLoadPlansMenu(user);
                    break;
                case 11:
                    printSortedPlansByDate(user);
                    break;
                case 12:
                    System.out.println("Log out.......");
                    break;
                default:
                    System.out.println("Wrong option choose a option beetwen 1-12");
            }
            pressAnyKeyToContinue();
        }
    }

    public static void saveAndLoadPlansMenu(User user) {
        System.out.println(
                "1-Save to Bineryfiles" + '\n' + "2-Load From Binary File" + '\n' + "3- Save to textFile" + '\n'
                        + "4-Load to textFile" + '\n' + "5-Back To Menu");
        int choice = 0;
        while (choice != 5) {
            choice = UIMethods.getInt("Enter an Option: ");
            switch (choice) {
                case 1:
                    savePlansToBinary(user);
                    break;
                case 2:
                    loadPlansFromBinary(user);
                    break;
                case 3:
                    savePlansToText(user);
                    break;
                case 4:
                    loadPlansFromText(user);
                    break;
                case 5:
                    System.out.println("Back To Menu");
                    break;
                default:
                    System.out.println("Wrong Entry");
                    break;
            }
            pressAnyKeyToContinue();
        }
    }
    // Methods---------------------------------------------------------------------------------------------------------

    public static void creatUserManualId(MobileCompany mobileCompany) {
        UIMethods.addUser(mobileCompany, UIMethods.makeUser());
    }

    public static void creatUserAutoIdGenerate(MobileCompany mobileCompany) {
        UIMethods.addUser(mobileCompany);
    }

    public static void createPersonalPlan(MobileCompany mobileCompany) {
        try {
            int userID = UIMethods.getInt("Enter the user ID: ");
            UIMethods.addPlan(mobileCompany, userID, UIMethods.getPersonalPlan());
        } catch (PlanException e) {
            System.out.println(e);
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }
    }

    public static void creatBusinessPlan(MobileCompany mobileCompany) {
        try {
            int userID = UIMethods.getInt("Enter the user ID: ");
            UIMethods.addPlan(mobileCompany, userID, UIMethods.getBusinessPlan());
        } catch (PlanException e) {
            System.out.println(e);
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }

    }

    public static void creatPersonalPlan(User user) {
        try {
            UIMethods.addPlan(user, UIMethods.getPersonalPlan());
        } catch (PlanException e) {
            System.out.println(e);
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }
    }

    public static void creatBusinessPlan(User user) {
        try {
            UIMethods.addPlan(user, UIMethods.getBusinessPlan());
        } catch (PlanException e) {
            System.out.println(e);
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }

    }

    public static void printUser(MobileCompany mobileCompany) {
        int userID = UIMethods.getInt("Enter the user ID: ");
        UIMethods.printUser(mobileCompany, userID);
    }

    public static void filterByMobileModel(MobileCompany mobileCompany) {
        String moedel = UIMethods.getString("Enter the model of phone you wish to filter");
        UIMethods.printPlans(UIMethods.filterByMobileModel(mobileCompany, moedel), mobileCompany.getFlatRate());
    }

    public static void filterByExpiryDate(MobileCompany mobileCompany) {
        int userID = UIMethods.getInt("Enter the user ID:");
        if (UIMethods.findUser(userID, mobileCompany) == null) {
            System.out.println("Wrong user Id");
            return;
        }
        MyDate date = UIMethods.creatDate();
        UIMethods.printPlans(UIMethods.filterByExpiryDate(mobileCompany, date, userID), mobileCompany.getFlatRate());

    }

    public static void updateUserAddress(MobileCompany mobileCompany) {
        int userID = UIMethods.getInt("Enter the user ID:");
        UIMethods.updateUserAddress(mobileCompany, userID);
    }

    public static void filterByMobileModel(User user) {
        String model = UIMethods.getString("Please enter the model you wish to filter plans by:");
        UIMethods.printPlans(UIMethods.filterByMobileModel(model, user));
    }

    public static void filterByExpiryDate(User user) {
        MyDate date = UIMethods.creatDate();
        UIMethods.printPlans(UIMethods.filterByExpiryDate(date, user));
    }

    public static void removePlan(User user) {
        int planID = UIMethods.getInt("Enter the planID you want to remove");
        UIMethods.removePlan(user, planID);
    }

    public static void removePlan(MobileCompany mobileCompany) {
        int userID = UIMethods.getInt("Enter the userID you want to remove plan from:");
        int planID = UIMethods.getInt("Enter the planID that you want to remove from this User");
        UIMethods.removePlan(mobileCompany, userID, planID);
    }

    public static void changePassword(MobileCompany mobileCompany) {
        String newPassword = UIMethods.getString("Please enter the new password:(Enter 1 to quiet)");
        if (!newPassword.trim().equals("1")) {
            mobileCompany.setAdminPassword(newPassword);
            System.out.println("Password have been changed");
        }
        System.out.println("Back to menu");

    }

    public static void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
        System.out.println("Continuing...");

    }
    // Assignment2

    public static void savePlansToBinary(User user) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        UIMethods.savePlansToBinery(user.getPlans(), fileName);
    }

    public static void loadPlansFromBinary(User user) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        user.setPlans(Plan.load(fileName));
    }

    public static void savePlansToText(User user) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        UIMethods.savePlansToTextFiles(user.getPlans(), fileName);
    }

    public static void loadPlansFromText(User user) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        user.setPlans(UIMethods.loadPlanFromTextFile(fileName));
    }

    public static void printSortedPlansByDate(User user) throws CloneNotSupportedException {
        UIMethods.printPlans(user.sortPlansByDate());
    }

    public static void filterByExpiryDateEachUser(MobileCompany mobileCompany) throws CloneNotSupportedException {
        MyDate date = UIMethods.creatDate();
        UIMethods.printExpiredPlansOfEachUser(mobileCompany, date);
    }

    public static void sortUsersByMonthlypayments(MobileCompany mobileCompany) throws CloneNotSupportedException {
        UIMethods.printUsers(mobileCompany.sortUsersByMonthlyPayment(), mobileCompany.getFlatRate());
    }

    public static void sortUsersByCityName(MobileCompany mobileCompany) throws CloneNotSupportedException {
        UIMethods.printUsers(mobileCompany.sortUsers());
    }

    public static void saveCompanyToBinary(MobileCompany mobileCompany) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        UIMethods.saveCompanyToBinery(mobileCompany, fileName);
    }

    public static void loadCompanyFromBinary(MobileCompany mobileCompany) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        mobileCompany = new MobileCompany();
        UIMethods.loadCompanyFromBinery(mobileCompany, fileName);
    }

    public static void saveCompanyToText(MobileCompany mobileCompany) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        UIMethods.saveCompanyToTextFiles(mobileCompany, fileName);
    }

    public static void loadCompanyFromText(MobileCompany mobileCompany) {
        String fileName = UIMethods.getString("Please Enter the File Name:");
        mobileCompany = new MobileCompany();
        UIMethods.loadCompanyFromTextFiles(mobileCompany, fileName);
    }

}
