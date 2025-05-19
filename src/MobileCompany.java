
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MobileCompany implements Cloneable, Serializable {

    protected String name;
    // private ArrayList<User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;
    private HashMap<Integer, User> users;

    public MobileCompany(String name, String adminUsername, String adminPassword, int flatRate) {
        this.name = name;
        // this.users = new ArrayList<>();
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
        this.users = new HashMap<Integer, User>();
    }

    public MobileCompany() {
    }

    public int getFlatRate() {
        return flatRate;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public boolean validateAdmin(String username, String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return true;
        }
        return false;
    }

    // public boolean addUser(User user) {
    // if (findUser(user.getUserID()) == null) {
    // users.add(user);
    // return true;
    // }
    // return false;
    // }
    // public Boolean addUser(String name, int userID, Address address) {
    // if (findUser(userID) == null) {
    // users.add(new User(name, userID, address));
    // return true;
    // }
    // return false;
    // }
    // public void addUser(String name, Address address) {
    // boolean userAdded = false;
    // while (!userAdded) {
    // if (findUser(User.getLastID()) == null) {
    // users.add(new User(name, address));
    // userAdded = true;
    // } else {
    // User.increaseLastId();
    // }
    // }
    // }
    // public User findUser(int userID) {
    // return User.findUser(users, userID);
    // }
    public boolean addPlan(int userID, Plan plan) {
        if (findUser(userID) != null) {
            return findUser(userID).addPlan(plan);
        }
        return false;
    }

    public Plan findPlan(int userID, int planID) {
        User user = findUser(userID);
        if (user != null) {
            return Plan.findPlan(user.getPlans(), planID);
        }
        return null;
    }

    public void printPlans(int userID) {
        User user = findUser(userID);
        if (user != null) {
            user.print();
        }

    }

    // public void print() {
    // System.out.println("Name: " + name + " Users informations:");
    // for (User user : users) {
    // user.printBasics();
    // System.out.println("Plans List");
    // user.printPlans(flatRate);
    // }
    // }
    public String toString() {
        return "Name: " + name + " Admin username: " + adminUsername + " Admin password :" + adminPassword
                + " Flat rate:" + flatRate + " Users informations: " + User.usersToString(users);

    }

    public boolean createPersonalPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota,
            int capLimit, MyDate expiryDate, String city) throws PlanException, PlanUserNameException {
        User user = findUser(userID);
        if (user != null) {
            return user.createPersonalPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate, city);
        }
        return false;
    }

    public boolean createBusinessPlan(int userID, String username, int id, MobilePhone mobilePhone, int internetQuota,
            int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN)
            throws PlanException, PlanUserNameException {
        User user = findUser(userID);
        if (user != null) {
            return user.createBusinessPlan(username, id, mobilePhone, internetQuota, capLimit, expiryDate,
                    numberOfEmployees, ABN);
        }
        return false;

    }

    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        if (user != null) {
            return user.calcTotalPayment(flatRate);
        }
        return 0;

    }

    // public double calcTotalPayments() {
    // double totalPayment = 0;
    // for (User user : users) {
    // totalPayment += user.calcTotalPayment(flatRate);
    // }
    // return totalPayment;
    // }
    public boolean mobilePriceRise(int userID, double risePercent) {
        User user = findUser(userID);
        if (user != null) {
            user.mobilePriceRiseAll(risePercent);
            return true;
        }

        return false;
    }

    // public void mobilePriceRise(double risePercent) {
    // for (User user : users) {
    // user.mobilePriceRiseAll(risePercent);
    // }
    // }
    // public ArrayList<Plan> allPlans() {
    // ArrayList<Plan> allPlans = new ArrayList<>();
    // for (User user : users) {
    // ArrayList<Plan> tmpPlanList = user.getPlans();
    // // allPlans.addAll(user.getPlans()); duplication issue
    // for (Plan plan : tmpPlanList) {
    // if (!allPlans.contains(plan)) {
    // allPlans.add(plan);
    // }
    // }
    // }
    // return allPlans;
    // }
    // public ArrayList<Plan> filterByMobileModel(int userID, String mobileModel) {
    // User user = findUser(userID);
    // if (user != null) {
    // return user.filterByMobileModel(mobileModel);
    // }
    // return null;
    // }
    // public ArrayList<Plan> filterByExpiryDate(int userID, MyDate date) {
    // User user = findUser(userID);
    // if (user != null) {
    // return user.filterByExpiryDate(date);
    // }
    // return null;
    // }
    // public ArrayList<Plan> filterByMobileModel(String mobileModel) {
    // ArrayList<Plan> fitredPlans = new ArrayList<>();
    // for (User user : users) {
    // ArrayList<Plan> tmpPlanList = user.filterByMobileModel(mobileModel);
    // // fitredPlans.addAll(user.filterByMobileModel(mobileModel)); duplication
    // issue
    // for (Plan plan : tmpPlanList) {
    // if (!fitredPlans.contains(plan)) {
    // fitredPlans.add(plan);
    // }
    // }
    // }
    // return fitredPlans;
    // }
    // public ArrayList<Plan> filterByExpiryDate(MyDate date) {
    // ArrayList<Plan> fitredPlans = new ArrayList<>();
    // for (User user : users) {
    // ArrayList<Plan> tmpPlanList = user.filterByExpiryDate(date);
    // // fitredPlans.addAll(user.filterByExpiryDate(date)); duplication issue
    // for (Plan plan : tmpPlanList) {
    // if (!fitredPlans.contains(plan)) {
    // fitredPlans.add(plan);
    // }
    // }
    // }
    // return fitredPlans;
    // }
    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> distinctCityNames = new ArrayList<>();
        for (User user : users.values()) {
            String city = user.getAddress().getCity();
            if (!distinctCityNames.contains(city)) {
                distinctCityNames.add(city);
            }
        }
        return distinctCityNames;
    }

    // double getTotalPaymentForCity(String city) {
    // double totalPayment = 0;
    // for (User user : users) {
    // if (user.getAddress().getCity().equals(city)) {
    // totalPayment += user.calcTotalPayment(flatRate);
    // }
    // }
    // return totalPayment;
    // }
    // public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
    // ArrayList<Double> totalPaymentList = new ArrayList<>();
    // for (String city : cities) {
    // totalPaymentList.add(getTotalPaymentForCity(city));
    // }
    // return totalPaymentList;
    // }
    // public static void reportPaymentPerCity(ArrayList<String> cities,
    // ArrayList<Double> payments) {
    // System.out.println("\u001B[1m" + "City Name Total Monthly Payment" +
    // "\u001B[0m");
    // for (int i = 0; i < cities.size(); i++) {
    // System.out.println(cities.get(i) + " " + payments.get(i));
    // }
    // }
    public boolean removePlan(int userID, int planID) {
        User user = findUser(userID);
        if (user != null) {
            return user.removePlan(planID);
        }
        return false;
    }

    public boolean removeUser(int userID) {
        User user = findUser(userID);
        if (user != null) {
            users.remove(userID);
            return true;
        }
        return false;
    }

    // public ArrayList<String> populateDistinctMobileModels() {
    // ArrayList<String> distinctModelsList = new ArrayList<>();
    // for (User user : users) {
    // ArrayList<String> modelsList = user.populateDistinctMobileModels();
    // for (String model : modelsList) {
    // if (!distinctModelsList.contains(model)) {
    // distinctModelsList.add(model);
    // }
    // }
    // }
    // return distinctModelsList;
    // }
    // public ArrayList<Integer> getTotalCountPerMobileModel(ArrayList<String>
    // mobileModels) {
    // ArrayList<Integer> totalCount = new ArrayList<>();
    // for (String model : mobileModels) {
    // int count = 0;
    // for (User user : users) {
    // count += user.getTotalCountForMobileModel(model);
    // }
    // totalCount.add(count);
    // }
    // return totalCount;
    // }
    // public ArrayList<Double> getTotalPaymentPerMobileModel(ArrayList<String>
    // mobileModels) {
    // ArrayList<Double> totalPayment = new ArrayList<>();
    // for (String model : mobileModels) {
    // Double payment = 0d;
    // for (User user : users) {
    // payment += user.getTotalPaymentForMobileModel(model, flatRate);
    // }
    // totalPayment.add(payment);
    // }
    // return totalPayment;
    // }
    public static void reportPaymentsPerMobileModel(ArrayList<String> mobileModels, ArrayList<Integer> counts,
            ArrayList<Double> monthlyPayments) {
        System.out.println(
                "\u001B[1m" + "Mobile Model     Total Monthly Payment      Average Monthly Payment" + "\u001B[0m");
        for (int i = 0; i < mobileModels.size(); i++) {
            System.out.println(mobileModels.get(i) + "     " + monthlyPayments.get(i) + "      "
                    + (monthlyPayments.get(i) / counts.get(i)));
        }

    }
    // LAB4

    // public MobileCompany(MobileCompany mobileCompany) {
    // this.name = mobileCompany.name;
    // this.adminUsername = mobileCompany.adminUsername;
    // this.adminPassword = mobileCompany.adminPassword;
    // this.flatRate = mobileCompany.flatRate;
    // this.users = new ArrayList<>();
    // for (User user : mobileCompany.users) {
    // users.add(new User(user));
    // }
    // }
    // public MobileCompany clone() throws CloneNotSupportedException {
    // MobileCompany outPut = (MobileCompany) super.clone();
    // outPut.users = User.deepCopy(users);
    // return outPut;
    // }
    // public ArrayList<User> deepCopyUsers() throws CloneNotSupportedException {
    // return User.deepCopy(users);
    // }
    // public ArrayList<User> shallowCopyUsers() {
    // return User.shallowCopy(users);
    // }
    // public ArrayList<User> sortUsers() throws CloneNotSupportedException {
    // ArrayList<User> sortedUsers = deepCopyUsers();
    // Collections.sort(sortedUsers);
    // return sortedUsers;
    // }
    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void setName(String name) {
        this.name = name;
    }
    // Lab5

    public MobileCompany clone() throws CloneNotSupportedException {
        MobileCompany outPut = (MobileCompany) super.clone();
        outPut.users = User.deepCopyhHashMap(users);
        return outPut;
    }

    public void print() {
        System.out.println("Name: " + name + " Users informations:");
        for (User user : users.values()) {
            user.printBasics();
            System.out.println("Plans List");
            user.printPlans(flatRate);
        }
    }

    public boolean addUser(User user) {
        if (findUser(user.getUserID()) == null) {
            users.put(user.getUserID(), user);
            return true;
        }
        return false;
    }

    public Boolean addUser(String name, String userPassword, int userID, Address address) {
        if (findUser(userID) == null) {
            users.put(userID, new User(name, userPassword, userID, address));
            return true;
        }
        return false;
    }

    public void addUser(String name, String userPassword, Address address) {
        boolean userAdded = false;
        while (!userAdded) {
            if (findUser(User.getLastID()) == null) {
                users.put(User.getLastID(), new User(name, userPassword, address));
                userAdded = true;
            } else {
                User.increaseLastId();
            }
        }

    }

    public User findUser(int userID) {
        return User.findUser(users, userID);
    }

    // public double calcTotalPayments() {
    // double totalPayment = 0;
    // for (User user : users.values()) {
    // totalPayment += user.calcTotalPayment(flatRate);
    // }
    // return totalPayment;
    // }
    // public void mobilePriceRise(double risePercent) {
    // for (User user : users.values()) {
    // user.mobilePriceRiseAll(risePercent);
    // }
    // }
    public HashMap<Integer, Plan> allPlans() {
        HashMap<Integer, Plan> allPlans = new HashMap<Integer, Plan>();
        for (User user : users.values()) {
            HashMap<Integer, Plan> tmpPlanList = user.getPlans();
            for (Plan plan : tmpPlanList.values()) {
                if (!allPlans.containsKey(plan.getId())) {
                    allPlans.put(plan.getId(), plan);
                }
            }
        }
        return allPlans;
    }

    public HashMap<Integer, Plan> filterByMobileModel(int userID, String mobileModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByMobileModel(mobileModel);
        }
        return null;
    }

    public HashMap<Integer, Plan> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return null;
    }

    public HashMap<Integer, Plan> filterByMobileModel(String mobileModel) {
        HashMap<Integer, Plan> fitredPlans = new HashMap<Integer, Plan>();
        for (User user : users.values()) {
            HashMap<Integer, Plan> tmpPlanList = user.filterByMobileModel(mobileModel);
            // fitredPlans.addAll(user.filterByMobileModel(mobileModel)); duplication issue
            for (Plan plan : tmpPlanList.values()) {
                if (!fitredPlans.containsKey(plan.getId())) {
                    fitredPlans.put(plan.getId(), plan);
                }
            }
        }
        return fitredPlans;
    }

    public HashMap<Integer, Plan> filterByExpiryDate(MyDate date) {
        HashMap<Integer, Plan> fitredPlans = new HashMap<Integer, Plan>();
        for (User user : users.values()) {
            HashMap<Integer, Plan> tmpPlanList = user.filterByExpiryDate(date);
            // fitredPlans.addAll(user.filterByExpiryDate(date)); duplication issue
            for (Plan plan : tmpPlanList.values()) {
                if (!fitredPlans.containsKey(plan.getId())) {
                    fitredPlans.put(plan.getId(), plan);
                }
            }
        }
        return fitredPlans;
    }

    public MobileCompany(MobileCompany mobileCompany) {
        this.name = mobileCompany.name;
        this.adminUsername = mobileCompany.adminUsername;
        this.adminPassword = mobileCompany.adminPassword;
        this.flatRate = mobileCompany.flatRate;
        this.users = new HashMap<Integer, User>();
        for (User user : mobileCompany.users.values()) {
            users.put(user.userID, new User(user));
        }
    }

    public HashMap<Integer, User> deepCopyUsersHashMap() throws CloneNotSupportedException {
        return User.deepCopyhHashMap(users);
    }

    public HashMap<Integer, User> shallowCopyUsersHashMap() {
        return User.shallowCopyhHashMap(users);
    }

    public ArrayList<User> deepCopyUsers() throws CloneNotSupportedException {
        return User.deepCopy(users);
    }

    public ArrayList<User> shallowCopyUsers() {
        return User.shallowCopy(users);
    }

    public ArrayList<User> sortUsers() throws CloneNotSupportedException {
        ArrayList<User> sortedUsers = deepCopyUsers();
        Collections.sort(sortedUsers);
        return sortedUsers;
    }

    // public HashMap<String, Double> getTotalPaymentPerCity() {
    // HashMap<String, Double> totalPaymentMap = new HashMap<String, Double>();
    // for (User user : users.values()) {
    // String city = user.getAddress().getCity().toLowerCase();
    // if (totalPaymentMap.get(city) == null) {
    // totalPaymentMap.put(city, user.calcTotalPayment(5));
    // } else {
    // totalPaymentMap.put(city, totalPaymentMap.get(city) +
    // user.calcTotalPayment(5));
    // }
    // }
    // return totalPaymentMap;
    // }
    public HashMap<String, Integer> getTotalCountPerMobileModel() {
        HashMap<String, Integer> totalCountPerModel = new HashMap<String, Integer>();
        for (User user : users.values()) {
            HashMap<String, Integer> tmpTotalCountPerModel = user.getTotalCountPerMobileModel();
            for (String model : tmpTotalCountPerModel.keySet()) {
                if (totalCountPerModel.get(model) == null) {
                    totalCountPerModel.put(model, tmpTotalCountPerModel.get(model));
                } else {
                    totalCountPerModel.put(model, totalCountPerModel.get(model) + tmpTotalCountPerModel.get(model));
                }
            }
        }
        return totalCountPerModel;
    }

    public HashMap<String, Double> getTotalPaymentPerMobileModel() {
        HashMap<String, Double> totalPaymentPerModel = new HashMap<String, Double>();
        for (User user : users.values()) {
            HashMap<String, Double> tmptotalPaymentPerModel = user.getTotalPaymentPerMobileModel();
            for (String model : tmptotalPaymentPerModel.keySet()) {
                if (totalPaymentPerModel.get(model) == null) {
                    totalPaymentPerModel.put(model, tmptotalPaymentPerModel.get(model));
                } else {
                    totalPaymentPerModel.put(model,
                            totalPaymentPerModel.get(model) + tmptotalPaymentPerModel.get(model));
                }
            }
        }
        return totalPaymentPerModel;
    }

    public void reportPaymentPerCity() {
        HashMap<String, Double> totalPaymentsPerCity = getTotalPaymentPerCity();
        System.out.println(
                "\u001B[1m" + "City Name   /  Total Monthly Payment");
        for (String model : totalPaymentsPerCity.keySet()) {
            System.out.println(model + "   /  " + totalPaymentsPerCity.get(model));
        }
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

    // LAB6
    public boolean load(String fileName) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            MobileCompany loadedCompany = (MobileCompany) input.readObject();
            this.name = loadedCompany.name;
            this.users = loadedCompany.users;
            this.adminUsername = loadedCompany.adminUsername;
            this.adminPassword = loadedCompany.adminPassword;
            this.flatRate = loadedCompany.flatRate;
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: Failed to load MobileCompany from the file");
        }
        return false;
    }

    public boolean save(String fileName) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(this);
            return true;
        } catch (IOException e) {
            System.err.println("Error: Failed to save MobileCompany to the file");
            e.printStackTrace();
        }
        return false;
    }

    public String toDilimatedString() {
        String output = name + "," + adminUsername + "," + adminPassword + "," + flatRate + "&";
        for (User user : users.values()) {
            output += user.toDilimatedString() + "&";
        }
        return output;
    }

    public static MobileCompany stringToCompany(String line) throws PlanException, PlanUserNameException {
        line = line.trim();
        String[] field = line.split("&");
        String[] basicAttrebutes = field[0].split(",");
        String name = basicAttrebutes[0];
        String adminUsername = basicAttrebutes[1];
        String adminPassword = basicAttrebutes[2];
        int flatRate = Integer.parseInt(basicAttrebutes[3]);
        MobileCompany mobileCompany = new MobileCompany(name, adminUsername, adminPassword, flatRate);
        for (int i = 1; i < field.length; i++) {
            User user = User.stringToUser(field[i]);
            mobileCompany.addUser(user);
        }
        return mobileCompany;

    }

    public boolean loadTextFile(String fileName) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            String line = input.readLine();
            MobileCompany loadedCompany = stringToCompany(line);
            input.close();
            this.name = loadedCompany.name;
            this.users = loadedCompany.users;
            this.adminUsername = loadedCompany.adminUsername;
            this.adminPassword = loadedCompany.adminPassword;
            this.flatRate = loadedCompany.flatRate;
        } catch (IOException e) {
            System.err.println("Error In oppening or closing file");
            return false;
        } catch (PlanException e) {
            System.out.println(e);
            return false;
        } catch (PlanUserNameException a) {
            System.out.println(a);
        }
        return true;

    }

    public Boolean saveTextFile(String fileName) {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Failed to find File:");
            return false;
        }
        try {
            output.write(this.toDilimatedString() + "\n");

        } catch (IOException e) {
            System.out.println("error in adding the objects to the file");
            try {
                output.close();
            } catch (IOException a) {
                System.err.println("Error in closing file");
            }
        }
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ex) {
            System.err.println("error in closing the file ");
            return false;
        }
        return true;
    }

    // Assignment2
    public class ToatalPaymentComprator implements Comparator<User> {

        public int compare(User user1, User user2) {
            return Double.compare(user1.calcTotalPayment(flatRate), user2.calcTotalPayment(flatRate));
        }
    }

    public ArrayList<User> sortUsersByMonthlyPayment() throws CloneNotSupportedException {
        ArrayList<User> sortedUsers = deepCopyUsers();
        Collections.sort(sortedUsers, new ToatalPaymentComprator());
        return sortedUsers;
    }

    public HashMap<String, ArrayList<User>> getUsersPerCity() {
        HashMap<String, ArrayList<User>> usersMapPerCity = new HashMap<String, ArrayList<User>>();
        for (User user : users.values()) {
            String city = user.getAddress().getCity();
            if (usersMapPerCity.get(city) == null) {
                ArrayList<User> usersList = new ArrayList<>();
                usersList.add(user);
                usersMapPerCity.put(city, usersList);
            } else {
                ArrayList<User> usersList = usersMapPerCity.get(city);
                usersList.add(user);
                usersMapPerCity.put(city, usersList);
            }
        }
        return usersMapPerCity;
    }

    public HashMap<String, ArrayList<Plan>> filterPlansByExpiryDate(MyDate expiryDate)
            throws CloneNotSupportedException {
        HashMap<String, ArrayList<Plan>> filtredPlans = new HashMap<String, ArrayList<Plan>>();
        for (User user : users.values()) {
            String userName = user.getName();
            ArrayList<Plan> expiredPlans = Plan.deepCopy(user.filterByExpiryDate(expiryDate));
            filtredPlans.put(userName, expiredPlans);
        }
        return filtredPlans;
    }

    public User userValidate(int ID, String passWord) {
        for (User user : users.values()) {
            if (user.userVaildate(ID, passWord)) {
                return user;
            }
        }
        return null;
    }

    // Lab8
    public double calcTotalPayments() {
        return users.values().stream().mapToDouble(x -> x.calcTotalPayment(flatRate)).sum();
    }

    public void mobilePriceRise(double risePercent) {
        users.values().stream().forEach(x -> x.mobilePriceRiseAll(risePercent));
    }

    public ArrayList<Plan> filterByMobileModelFunctional(String mobileModel) {
        return users.values().stream()
                .map(x -> x.filterByMobileModelFunctional(mobileModel))
                .flatMap(ArrayList::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public HashMap<String, Double> getTotalPaymentPerCity() {
        return (HashMap<String, Double>) users.values().stream().collect(Collectors.groupingBy(user -> user.getCity(),
                Collectors.summingDouble(user -> user.calcTotalPayment(flatRate))));
    }

    // Assignment3
    public ArrayList<User> filterByName(String name) {
        ArrayList<User> filtredUsers = new ArrayList<>();
        for (User user : this.users.values()) {
            if (user.getName().contains(name)) {
                filtredUsers.add(user);
            }
        }
        return filtredUsers;
    }

    public int[] planCount(String adminUsername, String adminPassword, int[] ranges) {

        if (validateAdmin(adminUsername, adminPassword)) {
            for (int i = 0; i < ranges.length - 1; i++) {
                final int currentIndex = i;
                long count = this.allPlans().values().stream()
                        .filter(x -> x.calcPayment(5) >= ranges[currentIndex]
                                && x.calcPayment(5) < ranges[currentIndex + 1])
                        .count();
                ranges[i] = (int) count;
            }

            return ranges;
        }
        return null;
    }

}
