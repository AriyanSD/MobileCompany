import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UIMethods {
    static Scanner sc = new Scanner(System.in);

    public static void addPlan(User user, Plan plan) {
        if (user.addPlan(plan)) {
            System.out.println("Plan Added successfully");
        } else {
            System.out.println("Faild to add plan");
        }
    }

    public static void addPlan(MobileCompany mobileCompany, int userID, Plan plan) {
        if (mobileCompany.addPlan(userID, plan)) {
            System.out.println("Plan added successfully");
        } else {
            System.out.println("Faild to add this plan");
        }
    }

    public static void removePlan(User user, int planID) {
        if (user.removePlan(planID)) {
            System.out.println("Plan removed successfully");
        } else {
            System.out.println("Faild to remove plan");
        }
    }

    public static void removePlan(MobileCompany mobileCompany, int userID, int planID) {
        if (mobileCompany.removePlan(userID, planID)) {
            System.out.println("Plan removed successfully");
        } else {
            System.out.println("Faild to remove this plan");
        }
    }

    public static Plan findPlan(User user, int planID) {// UI method for finding our plan
        Plan plan = user.findPlan(planID);
        if (plan == null) {
            System.out.println("There is no plan with inputed ID");
        }
        return plan;
    }

    public static User findUser(int userID, MobileCompany mobileCompany) {
        User user = mobileCompany.findUser(userID);
        if (user == null) {
            System.out.println("There is no plan with inputed ID");
        }
        return user;
    }

    // public static int getInt(String messageToShow) {// Get int by scanner with
    // inputed message to show
    // System.out.println(messageToShow);
    // int userInput = sc.nextInt();
    // sc.nextLine();
    // return userInput;
    // }

    // public static double getDouble(String messageToShow) {// Get double by
    // scanner with inputed message to show
    // System.out.println(messageToShow);
    // double userInput = sc.nextDouble();
    // sc.nextLine();
    // return userInput;
    // }

    // public static String getString(String messageToShow) {// Get string by
    // scanner with inputed message to show
    // System.out.println(messageToShow);
    // String userInput = sc.nextLine();
    // return userInput;
    // }

    public static Address creatAddress() { // make a new addres by our user inputes
        int streetNum = getInt("Please enter Street Number :");
        String street = getString("Please enter Street name :");
        String subrub = getString("Please enter subrub :");
        String city = getString("Please enter city :");
        return new Address(streetNum, street, subrub, city);
    }

    public static User makeUser() { // make a new user by our user inputes
        String name = getString("Enter the name of the user:");
        int userID = getInt("Enter the ID of the user:");
        String userPassword = UIMethods.getString("Enter the Password for this user");
        Address address = creatAddress();
        return new User(name, userPassword, userID, address);
    }

    // public static ArrayList<Plan> filterByMobileModel(String model, User user) {
    // ArrayList<Plan> filtredPlans = user.filterByMobileModel(model);
    // if (filtredPlans.size() == 0) {
    // System.out.println("There is no result by entered model Please try again ");
    // }
    // return filtredPlans;
    // }

    // public static ArrayList<Plan> filterByMobileModel(MobileCompany
    // mobileCompany, String model) {
    // ArrayList<Plan> filtredPlans = mobileCompany.filterByMobileModel(model);
    // if (filtredPlans.size() == 0) {
    // System.out.println("There is no result by entered model Please try again ");
    // }
    // return filtredPlans;
    // }

    // public static ArrayList<Plan> filterByExpiryDate(MyDate date, User user) {
    // ArrayList<Plan> filtredPlans = user.filterByExpiryDate(date);
    // if (filtredPlans.size() == 0) {
    // System.out.println("There is no result by entered model Please try again ");
    // }
    // return filtredPlans;
    // }

    // public static ArrayList<Plan> filterByExpiryDate(MobileCompany mobileCompany,
    // MyDate date, int userID) {
    // ArrayList<Plan> filtredPlans = mobileCompany.filterByExpiryDate(userID,
    // date);
    // if (filtredPlans.size() == 0) {
    // System.out.println("There is no result by entered model Please try again ");
    // }
    // return filtredPlans;
    // }

    public static void addUser(MobileCompany mobileCompany, User user) {
        if (mobileCompany.addUser(user)) {
            System.out.println("User Added successfully");
        } else {
            System.out.println("Faild to add User");
        }
    }

    public static void addUser(MobileCompany mobileCompany, String name, String userPassword, int userID,
            Address address) {
        if (mobileCompany.addUser(name, userPassword, userID, address)) {
            System.out.println("User added successfully");
        } else {
            System.out.println("Faild to add User");
        }
    }

    public static void addUser(MobileCompany mobileCompany) {
        String name = UIMethods.getString("Enter the name of user:");
        String userPassword = UIMethods.getString("Enter the User password");
        Address address = UIMethods.creatAddress();
        mobileCompany.addUser(name, userPassword, address);
        System.out.println("User added successfully");
    }

    public static void validateAdmin(String username, String password, MobileCompany mobileCompany1) {
        if (mobileCompany1.validateAdmin(username, password)) {
            System.out.println("LogIn successfully");
        } else {
            System.out.println("LogIn not successfully");
        }
    }

    public static void creatBusinessPlan(MobileCompany mobileCompany, int userID, String username, int id,
            MobilePhone mobilePhone, int internetQuota,
            int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) {
        try {
            if (mobileCompany.createBusinessPlan(userID, username, id, mobilePhone, internetQuota, capLimit, expiryDate,
                    numberOfEmployees, ABN)) {
                System.out.println("Bussines Plan created and added successfully");
            } else {
                System.out.println("faild to creat or add plan");
            }
        } catch (PlanException e) {
            System.out.println(e);
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }

    }

    public static void creatPersonalPlan(MobileCompany mobileCompany, int userID, String username, int id,
            MobilePhone mobilePhone, int internetQuota,
            int capLimit, MyDate expiryDate, String city) {
        try {
            if (mobileCompany.createPersonalPlan(userID, username, id, mobilePhone, internetQuota, capLimit, expiryDate,
                    city)) {
                System.out.println("Personal Plan created and added successfully");
            } else {
                System.out.println("faild to creat or add plan");
            }
        } catch (PlanException e) {
            System.out.println(e);
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }
    }

    public static PersonalPlan getPersonalPlan() throws PlanException, PlanUserNameException {
        String userName = getString("Please enter the username:");
        int id = getInt("Please enter the ID:");
        MobilePhone handset = creatHandset();
        int internetQuota = getInt("Enter the Internet Quota:");
        int capLimit = getInt("Enter the CapLimit:");
        MyDate expiryDate = creatDate();
        String city = getString("Please enter the city");
        return new PersonalPlan(userName, id, handset, internetQuota, capLimit, expiryDate, city);
    }

    public static BusinessPlan getBusinessPlan() throws PlanException, PlanUserNameException {
        String userName = getString("Please enter the username:");
        int id = getInt("Please enter the ID:");
        MobilePhone handset = creatHandset();
        int internetQuota = getInt("Enter the Internet Quota:");
        int capLimit = getInt("Enter the CapLimit:");
        MyDate expiryDate = creatDate();
        int ABN = getInt("Please enter the ABN");
        int numberOfEmployees = getInt("Please enter the number of employees");
        return new BusinessPlan(userName, id, handset, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
    }

    public static MobilePhone creatHandset() {
        String model = getString("Please enter the model of phone: ");
        int memorySize = getInt("Please enter the memory size of phone:");
        MobileType type = null;
        System.out.println("Please choose a type:");
        System.out.println("1.Android");
        System.out.println("2.IOS");
        System.out.println("3.Windows");
        int choice = getInt("Choose an modeltype from 1-3");
        switch (choice) {
            case 1:
                type = MobileType.Android;
                break;
            case 2:
                type = MobileType.IOS;
                break;
            case 3:
                type = MobileType.Windows;
                break;
            default:
                System.out.println("Incoorect choice");
                break;
        }
        double price = getDouble("Enter the price of this phone");
        return new MobilePhone(model, type, memorySize, price);
    }

    public static void printUser(MobileCompany mobileCompany, int userID) {
        if (mobileCompany.findUser(userID) != null) {
            mobileCompany.printPlans(userID);
        } else {
            System.out.println("User not found");
        }
    }

    public static void printPlan(MobileCompany mobileCompany, int planID, int userID) {
        if (mobileCompany.findPlan(userID, planID) != null) {
            mobileCompany.findPlan(userID, planID).print();
        } else {
            System.out.println("Invalid user or plan ID");
        }
    }

    public static void printPlans(ArrayList<Plan> plans, int flatRate) {
        if (plans.size() == 0) {
            System.out.println("There is no plan to print ");
            return;
        }
        Plan.printPlans(plans, flatRate);
    }

    public static void printPlans(ArrayList<Plan> plans) {
        if (plans.size() == 0) {
            System.out.println("There is no plan to print ");
            return;
        }
        Plan.printPlans(plans);
    }

    public static void updateUserAddress(MobileCompany mobileCompany, int userID) {
        User user = findUser(userID, mobileCompany);
        if (user == null) {
            return;
        }
        user.setAddress(creatAddress());
        System.out.println("Address Updated successfully");
    }

    public static void updateUserAddress(User user) {
        user.setAddress(creatAddress());
        System.out.println("Address Updated successfully");
    }

    public static MyDate creatDate() {
        int year = getInt("Please enter the year:");
        int month = getInt("Please enter the month:");
        int day = getInt("Please enter the day:");
        return new MyDate(year, month, day);
    }

    // public static void reportPaymentPerCity(MobileCompany mobileCompany, int
    // flatRate) {
    // ArrayList<String> distinctCities = mobileCompany.populateDistinctCityNames();
    // ArrayList<Double> totalPaymentsPerCity = mobileCompany
    // .getTotalPaymentPerCity(mobileCompany.populateDistinctCityNames());
    // MobileCompany.reportPaymentPerCity(distinctCities, totalPaymentsPerCity);
    // }

    // public static void reportPaymentsPerMobileModel(MobileCompany mobileCompany,
    // int flatRate) {
    // ArrayList<String> distinctMobileModelsCompany =
    // mobileCompany.populateDistinctMobileModels();
    // ArrayList<Double> totalPaymentsPerModelCompany = mobileCompany
    // .getTotalPaymentPerMobileModel(distinctMobileModelsCompany);
    // ArrayList<Integer> totalCountPerCityCompany = mobileCompany
    // .getTotalCountPerMobileModel(distinctMobileModelsCompany);
    // MobileCompany.reportPaymentsPerMobileModel(distinctMobileModelsCompany,
    // totalCountPerCityCompany,
    // totalPaymentsPerModelCompany);
    // }

    // public static void reportPaymentsPerMobileModel(User user, int flatRate) {
    // ArrayList<String> distinctMobileModels = user.populateDistinctMobileModels();
    // ArrayList<Double> totalPaymentsPerModel =
    // user.getTotalPaymentPerMobileModel(distinctMobileModels, flatRate);
    // ArrayList<Integer> totalCountPerCity =
    // user.getTotalCountPerMobileModel(distinctMobileModels);
    // User.reportPaymentsPerMobileModel(distinctMobileModels, totalCountPerCity,
    // totalPaymentsPerModel);
    // }

    // Lab 4
    public static ArrayList<Plan> deepCopyPlans(User user) throws CloneNotSupportedException {
        ArrayList<Plan> deepCopyPlans = new ArrayList<>();
        if (user.getPlans().size() != 0) {
            deepCopyPlans = user.deepCopyPlans();
            System.out.println("Plans deep copied successfully");
        } else {
            System.out.println("Failed to deep copy plans(This user has no plan yet)");

        }
        return deepCopyPlans;
    }

    public static ArrayList<Plan> shallowCopyPlans(User user) throws CloneNotSupportedException {
        ArrayList<Plan> shallowCopyPlans = new ArrayList<>();
        if (user.getPlans().size() != 0) {
            shallowCopyPlans = user.shallowCopyPlans();
            System.out.println("Plans shallow Copied successfully");
        } else {
            System.out.println("Failed to shallow copy plans(This user has no plan yet)");

        }
        return shallowCopyPlans;
    }

    public static ArrayList<Plan> sortPlansByDate(User user) throws CloneNotSupportedException {
        System.out.println("Plans sorted:");
        return user.sortPlansByDate();
    }

    public static ArrayList<User> deepCopyUsers(MobileCompany mobileCompany) throws CloneNotSupportedException {
        ArrayList<User> deepCopyUsers = new ArrayList<>();
        if (mobileCompany.getUsers().size() != 0) {
            deepCopyUsers = mobileCompany.deepCopyUsers();
            System.out.println("Users deep copied successfully");
        } else {
            System.out.println("Failed to deep copy Users(This company has no user yet)");

        }
        return deepCopyUsers;
    }

    public static ArrayList<User> shallowCopyUsers(MobileCompany mobileCompany) throws CloneNotSupportedException {
        ArrayList<User> shallowCopyUsers = new ArrayList<>();
        if (mobileCompany.getUsers().size() != 0) {
            shallowCopyUsers = mobileCompany.shallowCopyUsers();
            System.out.println("Users shallow Copied successfully");
        } else {
            System.out.println("Failed to shallow copy Users(This Company has no user yet)");

        }
        return shallowCopyUsers;
    }

    public static ArrayList<User> sortUsres(MobileCompany mobileCompany) throws CloneNotSupportedException {
        System.out.println("Users sorted:");
        return mobileCompany.sortUsers();
    }

    public static void printUsers(ArrayList<User> users) {
        if (users.size() == 0) {
            System.out.println("There is no user to print ");
            return;
        }
        User.printUsers(users);
    }

    // Lab5
    public static int getInt(String messageToShow) {// Get int by scanner with inputed message to show
        System.out.println(messageToShow);
        int userInput = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                userInput = sc.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("MisMatch input exception Please enter an integer:");
                sc.nextLine();
                continue;
            }
        }
        sc.nextLine();
        return userInput;
    }

    public static double getDouble(String messageToShow) {// Get double by scanner with inputed message to show
        System.out.println(messageToShow);
        double userInput = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                userInput = sc.nextDouble();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("MisMatch input exception Please enter an double:");
                sc.nextLine();
                continue;
            }
        }
        sc.nextLine();
        return userInput;
    }

    public static String getString(String messageToShow) {// Get string by scanner with inputed message to show
        System.out.println(messageToShow);
        String userInput = "";
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                userInput = sc.nextLine();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("MisMatch input exception Please enter an String:");
                continue;
            }
        }
        return userInput;
    }

    public static void printPlans(HashMap<Integer, Plan> plans) {
        if (plans.size() == 0) {
            System.out.println("There is no plan to print ");
            return;
        }
        Plan.printPlans(plans);
    }

    public static void printPlans(HashMap<Integer, Plan> plans, int flatRate) {
        if (plans.size() == 0) {
            System.out.println("There is no plan to print ");
            return;
        }
        Plan.printPlans(plans, flatRate);
    }

    public static void printUsers(HashMap<Integer, User> users) {
        if (users.size() == 0) {
            System.out.println("There is no user to print ");
            return;
        }
        User.printUsers(users);
    }

    public static HashMap<Integer, Plan> filterByMobileModel(String model, User user) {
        HashMap<Integer, Plan> filtredPlans = user.filterByMobileModel(model);
        if (filtredPlans.size() == 0) {
            System.out.println("There is no result by entered model Please try again ");
        }
        return filtredPlans;
    }

    public static HashMap<Integer, Plan> filterByMobileModel(MobileCompany mobileCompany, String model) {
        HashMap<Integer, Plan> filtredPlans = mobileCompany.filterByMobileModel(model);
        if (filtredPlans.size() == 0) {
            System.out.println("There is no result by entered model Please try again ");
        }
        return filtredPlans;
    }

    public static HashMap<Integer, Plan> filterByExpiryDate(MyDate date, User user) {
        HashMap<Integer, Plan> filtredPlans = user.filterByExpiryDate(date);
        if (filtredPlans.size() == 0) {
            System.out.println("There is no result by entered model Please try again ");
        }
        return filtredPlans;
    }

    public static HashMap<Integer, Plan> filterByExpiryDate(MobileCompany mobileCompany, MyDate date, int userID) {
        HashMap<Integer, Plan> filtredPlans = mobileCompany.filterByExpiryDate(userID, date);
        if (filtredPlans.size() == 0) {
            System.out.println("There is no result by entered model Please try again ");
        }
        return filtredPlans;
    }

    public static BusinessPlan creatBussinesPlan(String userName, int id, MobilePhone handset, int internetQuota,
            int capLimit,
            MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException, PlanUserNameException {
        BusinessPlan businessPlan = null;
        try {
            businessPlan = new BusinessPlan(userName, id, handset, internetQuota, capLimit, expiryDate,
                    numberOfEmployees, ABN);
        } catch (PlanException e) {
            System.out.println(e);
            try {
                businessPlan = new BusinessPlan(userName, e.ID, handset, internetQuota, capLimit, expiryDate,
                        numberOfEmployees, ABN);
            } catch (PlanUserNameException a) {
                System.out.println(a);
                businessPlan = new BusinessPlan(a.userName, e.ID, handset, internetQuota, capLimit, expiryDate,
                        numberOfEmployees, ABN);
            }
        }
        return businessPlan;
    }

    public static PersonalPlan creatPersonalPlan(String userName, int id, MobilePhone handset, int internetQuota,
            int capLimit,
            MyDate expiryDate, String city) throws PlanException, PlanUserNameException {
        PersonalPlan personalPlan = null;
        try {
            personalPlan = new PersonalPlan(userName, id, handset, internetQuota, capLimit, expiryDate, city);
        } catch (PlanException e) {
            System.out.println(e);
            try {
                personalPlan = new PersonalPlan(userName, e.ID, handset, internetQuota, capLimit, expiryDate, city);
            } catch (PlanUserNameException a) {
                System.out.println(a);
                personalPlan = new PersonalPlan(a.userName, e.ID, handset, internetQuota, capLimit, expiryDate, city);
            }
        } catch (PlanUserNameException a) {
            System.out.println(a);
            personalPlan = new PersonalPlan(a.userName, id, handset, internetQuota, capLimit, expiryDate, city);
        }
        return personalPlan;

    }

    // Lab6
    public static HashMap<Integer, Plan> loadPlanFromTextFile(String fileName) {
        HashMap<Integer, Plan> loadedPlans = new HashMap<Integer, Plan>();
        try {
            loadedPlans = Plan.loadTextFile(fileName);

        } catch (IOException e) {
            System.err.println(e);
        } catch (PlanException a) {
            System.err.println(a);
        } catch (PlanUserNameException b) {
            System.out.println(b);
        }
        return loadedPlans;
    }

    public static HashMap<Integer, User> loadUserFromTextFile(String fileName) {
        HashMap<Integer, User> loadedUsers = new HashMap<Integer, User>();
        try {
            loadedUsers = User.loadTextFile(fileName);

        } catch (IOException e) {
            System.err.println(e);
        } catch (PlanException a) {
            System.err.println(a);
        } catch (PlanUserNameException b) {
            System.out.println(b);
        }
        return loadedUsers;
    }

    public static void savePlansToBinery(HashMap<Integer, Plan> plans, String fileName) {
        boolean operation = Plan.save(plans, fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully saved to binary files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to save in binary file" + "\u001B[0m");
        }
    }

    public static void saveUsersToBinery(HashMap<Integer, User> users, String fileName) {
        boolean operation = User.save(users, fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully saved to binary files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to save in binary file" + "\u001B[0m");
        }
    }

    public static void savePlansToTextFiles(HashMap<Integer, Plan> plans, String fileName) {
        boolean operation = Plan.saveTextFile(plans, fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully saved to Text files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to save in Text file" + "\u001B[0m");
        }
    }

    public static void saveUsersToTextFiles(HashMap<Integer, User> users, String fileName) {
        boolean operation = User.saveTextFile(users, fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully saved to Text files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to save in Text file" + "\u001B[0m");
        }
    }

    public static void saveCompanyToBinery(MobileCompany mobileCompany, String fileName) {
        boolean operation = mobileCompany.save(fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully saved to binary files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to save in binary file" + "\u001B[0m");
        }
    }

    public static void loadCompanyFromBinery(MobileCompany mobileCompany, String fileName) {
        boolean operation = mobileCompany.load(fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully loaded from binary files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to loaded from binary file" + "\u001B[0m");
        }
    }

    public static void saveCompanyToTextFiles(MobileCompany mobileCompany, String fileName) {
        boolean operation = mobileCompany.saveTextFile(fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully saved to Text files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to save in Text file" + "\u001B[0m");
        }
    }

    public static void loadCompanyFromTextFiles(MobileCompany mobileCompany, String fileName) {
        boolean operation = mobileCompany.loadTextFile(fileName);
        if (operation) {
            System.out.println("\u001B[33m" + "Succsusfully loaded to Text files" + "\u001B[0m");
        } else {
            System.out.println("\u001B[33m" + "Faild to loaded in Text file" + "\u001B[0m");
        }
    }

    // Assignment2
    public static HashMap<String, ArrayList<Plan>> filterByMobileExpiryDateEachUser(MobileCompany mobileCompany,
            MyDate date) throws CloneNotSupportedException {
        HashMap<String, ArrayList<Plan>> filtredPlans = mobileCompany.filterPlansByExpiryDate(date);
        if (filtredPlans.size() == 0) {
            System.out.println("There is no result ");
        }
        return filtredPlans;
    }

    public static void printExpiredPlansOfEachUser(MobileCompany mobileCompany, MyDate date)
            throws CloneNotSupportedException {
        HashMap<String, ArrayList<Plan>> filtreHashMap = filterByMobileExpiryDateEachUser(mobileCompany, date);
        try {
            for (String userName : filtreHashMap.keySet()) {
                System.out.println("UserName:" + userName);
                System.out.println("\u001B[33m"
                        + "___________________________________________________Filtred Plans_____________________________________________"
                        + "\u001B[0m");
                printPlans(filtreHashMap.get(userName));
                System.out.println("\u001B[0m"
                        + "_____________________________________________________________________________________________________________________________"
                        + "\u001B[0m");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e);
        } catch (NullPointerException e) {
            System.err.println(e);
        }
    }

    public static void printUsers(ArrayList<User> users, int flatRate) {
        if (users.size() == 0) {
            System.out.println("There is no user to print ");
            return;
        }
        for (User user : users) {
            user.print(flatRate);
        }
    }

}
