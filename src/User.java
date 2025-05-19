import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class User implements Cloneable, Comparable<User>, Serializable {
    protected String name;
    protected String userPassword;
    protected int userID;
    protected Address address;
    // protected ArrayList<Plan> plans;
    protected static int lastID = 100;
    protected HashMap<Integer, Plan> plans;

    // public User(String name, int userID, Address address) {
    // this.name = name;
    // this.userID = userID;
    // this.address = address;
    // // this.plans = new ArrayList<>();
    // this.plans = new HashMap<Integer, Plan>();
    // }
    public User(String name, String userPassword, int userID, Address address) {
        this.name = name;
        this.userPassword = userPassword;
        this.userID = userID;
        this.address = address;
        // this.plans = new ArrayList<>();
        this.plans = new HashMap<Integer, Plan>();
    }

    public User(String name, String userPassword, Address address) {
        this.name = name;
        this.userPassword = userPassword;
        this.userID = lastID;
        lastID++;
        this.address = address;
        // this.plans = new ArrayList<>();
        this.plans = new HashMap<Integer, Plan>();
    }

    public int getUserID() {
        return userID;
    }

    // public ArrayList<Plan> getPlans() {
    // return plans;
    // }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getCity() {
        return address.getCity();
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    public static int getLastID() {
        return lastID;
    }

    public static void increaseLastId() {
        lastID++;
    }

    public boolean addPlan(Plan plan) {
        return Plan.addPlan(plans, plan);
    }

    public Plan findPlan(int planID) {
        return Plan.findPlan(plans, planID);
    }

    public void print() {
        System.out.print("Name: " + name + " ID: " + userID + " Adress(");
        address.print();
        System.out.println(")");
        System.out.println();
        System.out.println("************************************************************************");
        System.out.println("Plans list: ");
        Plan.printPlans(plans);
        System.out.println("************************************************************************");
        System.out.println("_____________________________________________________________________");
    }

    public void printBasics() {
        System.out.print("Name: " + name + " ID: " + userID + " Adress(");
        address.print();
    }

    public String toString() {
        return "Name: " + name + " ID: " + userID + " Adress((: " + address.toString()
                + ".)) - Plans: "
                + Plan.plansToString(plans);

    }

    public void printPlans(int flatRate) {
        Plan.printPlans(plans, flatRate);
    }

    public double calcTotalPayment(int flatRate) {
        return Plan.calcTotalPayment(plans, flatRate);
    }

    public void mobilePriceRiseAll(double risePercent) {
        Plan.mobilePriceRiseAll(plans, risePercent);
    }

    // public ArrayList<Plan> filterByMobileModel(String mobileModel) {
    // return Plan.filterByMobileModel(plans, mobileModel);
    // }

    // public boolean createPersonalPlan(String username, int id, MobilePhone
    // mobilePhone, int internetQuota, int capLimit,
    // MyDate expiryDate, String city) {
    // if (findPlan(id) == null) {
    // PersonalPlan newPersonalPlan = new PersonalPlan(username, id, mobilePhone,
    // internetQuota, capLimit,
    // expiryDate, city);
    // plans.add(newPersonalPlan);
    // return true;
    // }
    // return false;
    // }

    // boolean createBusinessPlan(String username, int id, MobilePhone mobilePhone,
    // int internetQuota, int capLimit,
    // MyDate expiryDate, int numberOfEmployees, int ABN) {
    // if (findPlan(id) == null) {
    // BusinessPlan newBusinessPlan = new BusinessPlan(username, id, mobilePhone,
    // internetQuota, capLimit,
    // expiryDate, numberOfEmployees, ABN);
    // plans.add(newBusinessPlan);
    // return true;
    // }
    // return false;
    // }

    // public ArrayList<Plan> filterByExpiryDate(MyDate date) {
    // return Plan.filterByExpiryDate(plans, date);
    // }

    public static User findUser(HashMap<Integer, User> users, int userID) {
        return users.get(userID);
    }

    public static String usersToString(ArrayList<User> users) {
        String string = "";
        for (User user : users) {
            string = string + user.toString();
        }
        return string;
    }

    // public boolean removePlan(int planID) {
    // Plan plan = findPlan(planID);
    // if (plan != null) {
    // plans.remove(plan);
    // return true;
    // }
    // return false;
    // }

    // public ArrayList<String> populateDistinctMobileModels() {
    // ArrayList<String> distinctModelNames = new ArrayList<>();
    // for (Plan plan : plans) {
    // String model = plan.getHandset().getModel();
    // if (!distinctModelNames.contains(model)) {
    // distinctModelNames.add(model);
    // }
    // }
    // return distinctModelNames;
    // }

    // public int getTotalCountForMobileModel(String mobileModel) {
    // int count = 0;
    // for (Plan plan : plans) {
    // if (plan.getHandset().getModel().equals(mobileModel)) {
    // count++;
    // }
    // }
    // return count;
    // }

    // public double getTotalPaymentForMobileModel(String mobileModel, int flatRate)
    // {
    // double totalPayment = 0;
    // for (Plan plan : plans) {
    // if (plan.getHandset().getModel().equals(mobileModel)) {
    // totalPayment += plan.calcPayment(flatRate);
    // }
    // }
    // return totalPayment;
    // }

    // public ArrayList<Integer> getTotalCountPerMobileModel(ArrayList<String>
    // mobileModels) {
    // ArrayList<Integer> totalCountList = new ArrayList<>();
    // for (String model : mobileModels) {
    // totalCountList.add(getTotalCountForMobileModel(model));
    // }
    // return totalCountList;
    // }

    // public ArrayList<Double> getTotalPaymentPerMobileModel(ArrayList<String>
    // mobileModels, int flatRate) {
    // ArrayList<Double> totalPaymentList = new ArrayList<>();
    // for (String model : mobileModels) {
    // totalPaymentList.add(getTotalPaymentForMobileModel(model, flatRate));
    // }
    // return totalPaymentList;
    // }

    // public static void reportPaymentsPerMobileModel(ArrayList<String>
    // mobileModels, ArrayList<Integer> counts,
    // ArrayList<Double> monthlyPayments) {
    // System.out.println(
    // "\u001B[1m" + "Mobile Model Total Monthly Payment Average Monthly Payment" +
    // "\u001B[0m");
    // for (int i = 0; i < mobileModels.size(); i++) {
    // System.out.println(mobileModels.get(i) + " " + monthlyPayments.get(i) + " "
    // + (monthlyPayments.get(i) / counts.get(i)));
    // }
    // }

    // LAB4

    // public User(User user) {
    // this.name = user.name;
    // this.userID = user.userID;
    // this.address = new Address(user.address);
    // this.plans = new ArrayList<>();
    // for (Plan plan : user.plans) {
    // if (plan instanceof PersonalPlan) {
    // plans.add(new PersonalPlan((PersonalPlan) plan));
    // } else if (plan instanceof BusinessPlan) {
    // plans.add(new BusinessPlan((BusinessPlan) plan));
    // }
    // }
    // }

    // public User clone() throws CloneNotSupportedException {
    // User outPut = (User) super.clone();
    // outPut.address = address.clone();
    // outPut.plans = new ArrayList<>();
    // for (Plan plan : plans) {
    // outPut.plans.add( plan.clone());
    // }
    // return outPut;
    // }

    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
        ArrayList<User> shallowCopy = new ArrayList<>();
        for (User user : users) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : users) {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    public ArrayList<Plan> deepCopyPlans() throws CloneNotSupportedException {
        return Plan.deepCopy(plans);
    }

    public ArrayList<Plan> shallowCopyPlans() {
        return Plan.shallowCopy(plans);
    }

    public int compareTo(User other) {
        return this.address.compareTo(other.address);
    }

    // public int compareTo1(User other) {
    // return Double.compare(calcTotalPayment(TestCode.flatRate),
    // other.calcTotalPayment(TestCode.flatRate));
    // }

    // public ArrayList<Plan> sortPlansByDate() throws CloneNotSupportedException {
    // ArrayList<Plan> sortedPlans = deepCopyPlans();
    // Collections.sort(sortedPlans);
    // return sortedPlans;
    // }

    public static void printUsers(ArrayList<User> users) {
        for (User user : users) {
            user.print();
        }
    }

    // Lab5
    public HashMap<Integer, Plan> getPlans() {
        return plans;
    }

    public User(User user) {
        this.name = user.name;
        this.userID = user.userID;
        this.address = new Address(user.address);
        this.plans = new HashMap<Integer, Plan>();
        for (Plan plan : user.plans.values()) {
            if (plan instanceof PersonalPlan) {
                plans.put(plan.getId(), new PersonalPlan((PersonalPlan) plan));
            } else if (plan instanceof BusinessPlan) {
                plans.put(plan.getId(), new BusinessPlan((BusinessPlan) plan));
            }
        }
    }

    public User clone() throws CloneNotSupportedException {
        User outPut = (User) super.clone();
        outPut.address = address.clone();
        outPut.plans = new HashMap<Integer, Plan>();
        for (Plan plan : plans.values()) {
            outPut.plans.put(plan.getId(), plan.clone());
        }
        return outPut;
    }

    public static HashMap<Integer, User> shallowCopyhHashMap(HashMap<Integer, User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            shallowCopy.put(user.userID, user);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, User> deepCopyhHashMap(HashMap<Integer, User> users)
            throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<Integer, User>();
        for (User user : users.values()) {
            deepCopy.put(user.userID, user.clone());
        }
        return deepCopy;
    }

    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users) {
        ArrayList<User> shallowCopy = new ArrayList<>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public static ArrayList<User> deepCopy(HashMap<Integer, User> users) throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : users.values()) {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    public HashMap<Integer, Plan> deepCopyPlansHashMap() throws CloneNotSupportedException {
        return Plan.deepCopyHashMap(plans);
    }

    public HashMap<Integer, Plan> shallowCopyPlansHashMap() {
        return Plan.shallowCopyHashMap(plans);
    }

    public ArrayList<Plan> sortPlansByDate() throws CloneNotSupportedException {
        ArrayList<Plan> sortedPlans = shallowCopyPlans();
        Collections.sort(sortedPlans);
        return sortedPlans;
    }

    public static void printUsers(HashMap<Integer, User> users) {
        for (User user : users.values()) {
            user.print();
        }
    }

    public static String usersToString(HashMap<Integer, User> users) {
        String string = "";
        for (User user : users.values()) {
            string = string + user.toString();
        }
        return string;
    }

    public boolean createPersonalPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit,
            MyDate expiryDate, String city) throws PlanException, PlanUserNameException {
        if (findPlan(id) == null) {
            PersonalPlan newPersonalPlan = new PersonalPlan(username, id, mobilePhone, internetQuota, capLimit,
                    expiryDate, city);
            plans.put(newPersonalPlan.getId(), newPersonalPlan);
            return true;
        }
        return false;
    }

    public boolean createBusinessPlan(String username, int id, MobilePhone mobilePhone, int internetQuota, int capLimit,
            MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException, PlanUserNameException {
        if (findPlan(id) == null) {
            BusinessPlan newBusinessPlan = new BusinessPlan(username, id, mobilePhone, internetQuota, capLimit,
                    expiryDate, numberOfEmployees, ABN);
            plans.put(newBusinessPlan.getId(), newBusinessPlan);
            return true;
        }
        return false;
    }

    public boolean removePlan(int planID) {
        if (plans.get(userID) != null) {
            plans.remove(planID);
            return true;
        }
        return false;
    }

    public HashMap<Integer, Plan> filterByMobileModel(String mobileModel) {
        return Plan.filterByMobileModel(plans, mobileModel);
    }

    public HashMap<Integer, Plan> filterByExpiryDate(MyDate date) {
        return Plan.filterByExpiryDate(plans, date);
    }

    public ArrayList<String> populateDistinctMobileModels() {
        ArrayList<String> distinctModelNames = new ArrayList<>();
        for (Plan plan : plans.values()) {
            String model = plan.getHandset().getModel();
            if (!distinctModelNames.contains(model)) {
                distinctModelNames.add(model);
            }
        }
        return distinctModelNames;
    }

    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> totalCountMap = new HashMap<String, Integer>();
        for (Plan plan : plans.values()) {
            String model = plan.getHandset().getModel().toLowerCase();
            if (totalCountMap.get(model) == null) {
                totalCountMap.put(model, 1);
            } else {
                totalCountMap.put(model, totalCountMap.get(model) + 1);
            }
        }
        return totalCountMap;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel() {
        HashMap<String, Double> totalPaymentMap = new HashMap<String, Double>();
        for (Plan plan : plans.values()) {
            String model = plan.getHandset().getModel().toLowerCase();
            if (totalPaymentMap.get(model) == null) {
                totalPaymentMap.put(model, plan.calcPayment(5));
            } else {
                totalPaymentMap.put(model, totalPaymentMap.get(model) + plan.calcPayment(5));
            }
        }
        return totalPaymentMap;
    }

    public void reportPaymentsPerMobileModel() {
        HashMap<String, Integer> totalCountMap = getTotalCountPerMobileModel();
        HashMap<String, Double> totalPaymentMap = getTotalPaymentPerMobileModel();
        System.out.println(
                "\u001B[1m" + "Mobile Model   /  Total Monthly Payment      Average Monthly Payment" + "\u001B[0m");
        for (String model : totalCountMap.keySet()) {
            System.out.println(model + "   /  " + totalPaymentMap.get(model)
                    + "   /  " + totalPaymentMap.get(model) / totalCountMap.get(model));
        }
    }

    // Lab6
    public static HashMap<Integer, User> load(String fileName) {
        HashMap<Integer, User> loadedUsers = new HashMap<Integer, User>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
        }
        try {
            while (true) {
                User user = (User) input.readObject();
                loadedUsers.put(user.userID, user);
            }
        } catch (EOFException ex) {
            System.out.println("no more Users!");
        } catch (ClassNotFoundException ex) {
            System.err.println("error in wrong class in the file ");
        } catch (IOException ex) {
            System.err.println("error in add object to the file ");
        }
        try {
            if (input != null)
                input.close();
        } catch (IOException ex) {
            System.err.println("error in close the file ");
        }
        return loadedUsers;
    }

    public static boolean save(HashMap<Integer, User> users, String fileName) {
        ObjectOutputStream outputst = null;

        try {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            return false;
        }
        try {
            for (User user : users.values()) {
                outputst.writeObject(user);
            }
        } catch (IOException ex) {
            System.err.println("error in adding the objects to the file ");
            return false;
        }
        try {
            if (outputst != null)
                outputst.close();
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            return false;
        }
        return true;
    }

    public String toDilimatedString() {
        String output = name + "," + userPassword + "," + userID + "," + address.toDilimatedString() + "!";
        for (Plan plan : plans.values()) {
            output += plan.toDilimatedString() + "!";
        }
        return output;
    }

    public static User stringToUser(String line) throws PlanException, PlanUserNameException {
        line = line.trim();
        String[] field = line.split("!");
        String[] basicAttrebutes = field[0].split(",");
        String name = basicAttrebutes[0];
        String userPassword = basicAttrebutes[1];
        int userID = Integer.parseInt(basicAttrebutes[2]);
        int streetNum = Integer.parseInt(basicAttrebutes[3]);
        String street = basicAttrebutes[4];
        String suburb = basicAttrebutes[5];
        String city = basicAttrebutes[6];
        Address address = new Address(streetNum, street, suburb, city);
        User user = new User(name, userPassword, userID, address);
        for (int i = 1; i < field.length; i++) {
            Plan plan = Plan.stringToPlan(field[i]);
            user.addPlan(plan);
        }
        return user;

    }

    public static HashMap<Integer, User> loadTextFile(String fileName)
            throws IOException, PlanException, PlanUserNameException {
        HashMap<Integer, User> loadedUsers = new HashMap<Integer, User>();
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line = input.readLine();
        while (line != null) {
            User user = stringToUser(line);
            loadedUsers.put(user.getUserID(), user);
            line = input.readLine();
        }
        input.close();
        return loadedUsers;
    }

    public static Boolean saveTextFile(HashMap<Integer, User> users, String fileName) {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Failed to find File:");
            return false;
        }
        try {
            for (User user : users.values()) {
                output.write(user.toDilimatedString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("error in adding the objects to the file");
            try {
                output.close();
            } catch (IOException a) {
                System.err.println("Error in closing file");
            }
            return false;
        }
        try {
            if (output != null)
                output.close();
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            return false;
        }
        return true;
    }

    // Assignment2
    public boolean userVaildate(int userID, String password) {
        return (this.userID == userID && this.userPassword.equals(password));
    }

    public void setPlans(HashMap<Integer, Plan> plans) {
        this.plans = plans;
    }

    public void print(int flatRate) {
        System.out.print("Name: " + name + " ID: " + userID + " Adress(");
        address.print();
        System.out.println(")");
        System.out.println();
        System.out.println("************************************************************************");
        System.out.println("Plans list: ");
        Plan.printPlans(plans);
        System.out.println("************************************************************************");
        System.out.println("TotalPayment: " + calcTotalPayment(flatRate));
        System.out.println("_____________________________________________________________________");
    }
    // Lab7

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Lab8
    public ArrayList<Plan> filterByMobileModelFunctional(String mobileModel) {
        return Plan.filterByMobileModelFunctional(plans, mobileModel);
    }

    // Assignment 3

    public int[] planCount(int id, String password, int[] ranges) {
        if (userVaildate(id, password)) {
            for (int i = 0; i < ranges.length - 1; i++) {
                final int currentIndex = i;
                long count = plans.values().stream()
                        .filter(x -> x.calcPayment(5) >= ranges[currentIndex]
                                && x.calcPayment(5) < ranges[currentIndex + 1])
                        .count();
                ranges[i] = (int) count;
            }

            return ranges;
        }
        return null;
    }

    public HashMap<String, int[]> planMobileModelCount(int id, String password, int[] ranges) {
        if (this.userVaildate(id, password)) {
            ArrayList<String> models = this.populateDistinctMobileModels();
            HashMap<String, int[]> output = new HashMap<>();
            models.forEach(model -> {
                int[] counts = new int[ranges.length - 1];

                for (int i = 0; i < ranges.length; i++) {
                    final int currentIndex = i;

                    long count = this.filterByMobileModel(model).values().stream()
                            .filter(x -> x.calcPayment(5) >= ranges[currentIndex]
                                    && x.calcPayment(5) < ranges[currentIndex + 1])
                            .count();
                    counts[i] = (int) count;
                }

                output.put(model, counts);
            });

            return output;
        }
        return null;
    }

}
