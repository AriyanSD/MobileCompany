
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class Plan implements Cloneable, Comparable<Plan>, Serializable {

    protected String userName;
    protected int id;
    protected MobilePhone handset;
    protected int internetQuota;
    protected int capLimit;
    protected MyDate expiryDate;

    // public Plan(String userName, int id, MobilePhone handset, int internetQuota,
    // int capLimit, MyDate expiryDate) {
    // this.userName = userName;
    // this.id = id;
    // this.handset = handset;
    // this.internetQuota = internetQuota;
    // this.capLimit = capLimit;
    // this.expiryDate = expiryDate;
    // }
    public int getId() {
        return id;
    }

    public MobilePhone getHandset() {
        return handset;
    }

    public Double getHandsetPrice() {
        return handset.getPrice();
    }

    public int getCapLimit() {
        return capLimit;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public int getExpiryDateYear() {
        return expiryDate.getYear();
    }

    public int getInternetQuota() {
        return internetQuota;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMobileModel(String model) {
        handset.setModel(model);
    }

    public String toString() {
        return " UserName: " + userName + " ID: " + id + " Hand Set informations: ( " + handset.toString() + " ) "
                + " internetQuota: " + internetQuota
                + " CapLimit: " + capLimit + " ExpiryDate: " + expiryDate.toString();
    }

    public void print() {
        System.out.print(" UserName: " + userName + " ID: " + id);
        System.out.print(" Hand set informations: ( ");
        handset.print();
        System.out.print(" ) ");
        System.out.print(
                " internetQuota: " + internetQuota + " CapLimit: " + capLimit);
        System.out.print("ExpiryDate:");
        expiryDate.print();
    }

    // public static void printPlans(ArrayList<Plan> plans) {
    // for (Plan plan : plans) {
    // plan.print();
    // }
    // }

    public static void printPlans(ArrayList<Plan> plans, int flatRate) {
        for (Plan plan : plans) {
            plan.print();
            System.out.println("Monthly payment:" + plan.calcPayment(flatRate));
        }
        System.out.println("Total payment: " + calcTotalPayment(plans, flatRate));
    }

    public static String plansToString(ArrayList<Plan> plans) {
        String string = "";
        for (Plan plan : plans) {
            string = string + plan.toString();
        }
        return string;
    }

    // public static double calcTotalPayment(ArrayList<Plan> plans, int flatRate) {
    // double total = 0;
    // for (Plan plan : plans) {
    // total += plan.calcPayment(flatRate);
    // }
    // return total;
    // }

    public void mobilePriceRise(Double risePercent) {
        handset.priceRice(1 + risePercent);
    }

    // public static void mobilePriceRiseAll(ArrayList<Plan> plans, double
    // risePercent) {
    // for (Plan plan : plans) {
    // plan.handset.priceRice(risePercent);
    // }

    // }

    // public static ArrayList<Plan> filterByMobileModel(ArrayList<Plan> plans,
    // String mobileModel) {
    // ArrayList<Plan> filteredPlans = new ArrayList<>();
    // for (Plan plan : plans) {
    // if (plan.handset.getModel().contains(mobileModel)) {
    // filteredPlans.add(plan);
    // }
    // }
    // return filteredPlans;
    // }

    public static Plan findPlan(ArrayList<Plan> plans, int planID) {
        for (Plan plan : plans) {
            if (plan.id == planID) {
                return plan;
            }
        }
        return null;
    }

    public static boolean addPlan(ArrayList<Plan> plans, Plan plan) {
        if (findPlan(plans, plan.getId()) == null) {
            plans.add(plan);
            return true;
        }
        return false;
    }

    // Lab3
    public static ArrayList<Plan> filterByExpiryDate(ArrayList<Plan> plans, MyDate date) {
        ArrayList<Plan> filtredPlans = new ArrayList<>();
        for (Plan plan : plans) {
            if (plan.expiryDate.isExpired(date)) {
                filtredPlans.add(plan);
            }
        }
        return filtredPlans;
    }

    public abstract double calcPayment(int flatRate);

    // Lab4
    public Plan(Plan plan) {
        this.userName = plan.userName;
        this.id = plan.id;
        this.handset = new MobilePhone(plan.handset);
        this.internetQuota = plan.internetQuota;
        this.capLimit = plan.capLimit;
        this.expiryDate = new MyDate(plan.expiryDate);
    }

    public Plan clone() throws CloneNotSupportedException {
        Plan outPut = (Plan) super.clone();
        outPut.handset = handset.clone();
        outPut.expiryDate = expiryDate.clone();
        return outPut;
    }

    // public static ArrayList<Plan> shallowCopy(ArrayList<Plan> plans) {
    // ArrayList<Plan> shallowCopy = new ArrayList<>();
    // for (Plan plan : plans) {
    // shallowCopy.add(plan);
    // }
    // return shallowCopy;
    // }

    // public static ArrayList<Plan> deepCopy(ArrayList<Plan> plans) throws
    // CloneNotSupportedException {
    // ArrayList<Plan> deepCopy = new ArrayList<>();
    // for (Plan plan : plans) {
    // deepCopy.add(plan.clone());
    // }
    // return deepCopy;
    // }

    public int compareTo1(Plan other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    public void setCapLimit(int capLimit) {
        this.capLimit = capLimit;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Lab5
    // public Plan(String userName, int id, MobilePhone handset, int internetQuota,
    // int capLimit, MyDate expiryDate)
    // throws PlanException {
    // this.userName = userName;
    // this.handset = handset;
    // this.internetQuota = internetQuota;
    // this.capLimit = capLimit;
    // this.expiryDate = expiryDate;
    // if (!isValidID(id)) {
    // Random random = new Random();
    // int generatedNumber = random.nextInt(1000000) + 3000000;
    // this.id = generatedNumber;
    // throw new PlanException(generatedNumber);
    // }
    // this.id = id;
    // }
    // public static boolean isValidID(int Id) {
    // String idString = Integer.toString(Id);
    // if (idString.length() == 7 && idString.startsWith("3")) {
    // return true;
    // }
    // return false;
    // }
    public static Plan findPlan(HashMap<Integer, Plan> plans, int planID) {
        return plans.get(planID);
    }

    public static boolean addPlan(HashMap<Integer, Plan> plans, Plan plan) {
        if (findPlan(plans, plan.getId()) == null) {
            plans.put(plan.getId(), plan);
            return true;
        }
        return false;
    }

    public static void printPlans(HashMap<Integer, Plan> plans) {
        for (Plan plan : plans.values()) {
            plan.print();
        }
    }

    public static void printPlans(HashMap<Integer, Plan> plans, int flatRate) {
        for (Plan plan : plans.values()) {
            plan.print();
            System.out.println("Monthly payment:" + plan.calcPayment(flatRate));
        }
        System.out.println("Total payment: " + calcTotalPayment(plans, flatRate));
    }

    public static String plansToString(HashMap<Integer, Plan> plans) {
        String string = "";
        for (Plan plan : plans.values()) {
            string = string + plan.toString();
        }
        return string;
    }

    public static double calcTotalPayment(HashMap<Integer, Plan> plans, int flatRate) {
        double total = 0;
        for (Plan plan : plans.values()) {
            total += plan.calcPayment(flatRate);
        }
        return total;
    }

    public static void mobilePriceRiseAll(HashMap<Integer, Plan> plans, double risePercent) {
        for (Plan plan : plans.values()) {
            plan.handset.priceRice(risePercent);
        }

    }

    public static HashMap<Integer, Plan> filterByMobileModel(HashMap<Integer, Plan> plans, String mobileModel) {
        HashMap<Integer, Plan> filteredPlans = new HashMap<Integer, Plan>();
        for (Plan plan : plans.values()) {
            if (plan.handset.getModel().contains(mobileModel)) {
                filteredPlans.put(plan.getId(), plan);
            }
        }
        return filteredPlans;
    }

    public static HashMap<Integer, Plan> filterByExpiryDate(HashMap<Integer, Plan> plans, MyDate date) {
        HashMap<Integer, Plan> filtredPlans = new HashMap<Integer, Plan>();
        for (Plan plan : plans.values()) {
            if (plan.expiryDate.isExpired(date)) {
                filtredPlans.put(plan.getId(), plan);
            }
        }
        return filtredPlans;
    }

    public static HashMap<Integer, Plan> shallowCopyHashMap(HashMap<Integer, Plan> plans) {
        HashMap<Integer, Plan> shallowCopy = new HashMap<Integer, Plan>();
        for (Plan plan : plans.values()) {
            shallowCopy.put(plan.getId(), plan);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, Plan> deepCopyHashMap(HashMap<Integer, Plan> plans)
            throws CloneNotSupportedException {
        HashMap<Integer, Plan> deepCopy = new HashMap<Integer, Plan>();
        for (Plan plan : plans.values()) {
            deepCopy.put(plan.getId(), plan.clone());
        }
        return deepCopy;
    }

    public static ArrayList<Plan> shallowCopy(HashMap<Integer, Plan> plans) {
        ArrayList<Plan> shallowCopy = new ArrayList<>();
        for (Plan plan : plans.values()) {
            shallowCopy.add(plan);
        }
        return shallowCopy;
    }

    public static ArrayList<Plan> deepCopy(HashMap<Integer, Plan> plans) throws CloneNotSupportedException {
        ArrayList<Plan> deepCopy = new ArrayList<>();
        for (Plan plan : plans.values()) {
            deepCopy.add(plan.clone());
        }
        return deepCopy;
    }

    // Lab6
    public static HashMap<Integer, Plan> load(String fileName) {
        HashMap<Integer, Plan> loadedPlans = new HashMap<Integer, Plan>();
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            return loadedPlans;
        }
        try {
            while (true) {
                Plan plan = (Plan) input.readObject();
                loadedPlans.put(plan.id, plan);
            }
        } catch (EOFException ex) {
            System.out.println("no more Users!");
        } catch (ClassNotFoundException ex) {
            System.err.println("error in wrong class in the file ");
        } catch (IOException ex) {
            System.err.println("error in add object to the file ");
        }
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ex) {
            System.err.println("error in close the file ");
        }
        return loadedPlans;
    }

    public static boolean save(HashMap<Integer, Plan> plans, String fileName) {
        ObjectOutputStream output = null;

        try {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)));
        } catch (IOException ex) {
            System.err.println("error in create/open the file ");
            return false;
        }
        try {
            for (Plan plan : plans.values()) {
                output.writeObject(plan);
            }
        } catch (IOException ex) {
            System.err.println("error in adding the objects to the file ");
            return false;
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

    public abstract String toDilimatedString();

    public static Plan stringToPlan(String line) throws PlanException, PlanUserNameException {
        Plan plan = null;
        line = line.trim();
        String[] field = line.split(",");
        String userName = field[1];
        int id = Integer.parseInt(field[2]);
        String model = field[3];
        MobileType type = MobileType.valueOf(field[4]);
        // switch (field[4]) {
        // case "IOS":
        // type = MobileType.IOS;
        // break;
        // case "Android":
        // type = MobileType.Android;
        // break;
        // case "Windows":
        // type = MobileType.Windows;
        // break;

        // }
        int memorySize = Integer.parseInt(field[5]);
        double price = Double.parseDouble(field[6]);
        int internetQuota = Integer.parseInt(field[7]);
        int capLimit = Integer.parseInt(field[8]);
        int year = Integer.parseInt(field[9]);
        int month = Integer.parseInt(field[10]);
        int day = Integer.parseInt(field[11]);
        switch (field[0]) {
            case "P":
                String city = field[12];
                plan = new PersonalPlan(userName, id, new MobilePhone(model, type, memorySize, price),
                        internetQuota, capLimit, new MyDate(year, month, day), city);
                break;
            case "B":
                int numberOfEmployees = Integer.parseInt(field[12]);
                int ABN = Integer.parseInt(field[13]);
                plan = new BusinessPlan(userName, id, new MobilePhone(model, type, memorySize, price),
                        internetQuota, capLimit, new MyDate(year, month, day), numberOfEmployees, ABN);
                break;
        }
        return plan;
    }

    public static HashMap<Integer, Plan> loadTextFile(String fileName)
            throws IOException, PlanException, PlanUserNameException {
        HashMap<Integer, Plan> loadedPlans = new HashMap<Integer, Plan>();
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line = input.readLine();
        while (line != null) {
            Plan plan = stringToPlan(line);
            loadedPlans.put(plan.getId(), plan);
            line = input.readLine();
        }
        input.close();
        return loadedPlans;
    }

    public static Boolean saveTextFile(HashMap<Integer, Plan> plans, String fileName) {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Failed to find File:");
            return false;
        }
        try {
            for (Plan plan : plans.values()) {
                output.write(plan.toDilimatedString() + "\n");
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
    public Plan(String userName, int id, MobilePhone handset, int internetQuota,
            int capLimit, MyDate expiryDate)
            throws PlanException, PlanUserNameException {
        this.handset = handset;
        this.internetQuota = internetQuota;
        this.capLimit = capLimit;
        this.expiryDate = expiryDate;
        if (!isValidID(id)) {
            this.id = generateValidId();
            throw new PlanException(this.id);
        }
        if (!isValidUsername(userName)) {
            this.userName = generateValidUsername();
            throw new PlanUserNameException(this.userName);
        }
        this.id = id;
        this.userName = userName;
    }

    private boolean isValidUsername(String userName) {
        String pattern = "USR\\d{6}U";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(userName);
        return matcher.matches();
    }

    public boolean isValidID(int id) {
        String idString = Integer.toString(id);
        String pattern = "^3\\d{6}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(idString);
        return matcher.matches();
    }

    private String generateValidUsername() {
        Random random = new Random();
        int randomNum = random.nextInt(900000) + 100000;
        return "USR" + randomNum + "U";
    }

    private int generateValidId() {
        Random random = new Random();
        int generatedNumber = random.nextInt(1000000) + 3000000;
        return generatedNumber;
    }

    // Lab7
    public int compareTo(Plan other) {
        return this.userName.compareTo(other.userName);
    }

    // Lab8
    public static void printPlans(ArrayList<Plan> plans) {
        plans.forEach(x -> x.print());
    }

    public static double calcTotalPayment(ArrayList<Plan> plans, int flatRate) {
        return plans.stream().mapToDouble(x -> x.calcPayment(flatRate)).sum();
    }

    public static ArrayList<Plan> filterByMobileModel(ArrayList<Plan> plans, String mobileModel) {
        return (ArrayList<Plan>) plans.stream().filter(x -> x.getHandset().getModel().contains(mobileModel))
                .collect(Collectors.toList());
    }

    public static void mobilePriceRiseAll(ArrayList<Plan> plans, double risePercent) {
        plans.stream().forEach(x -> x.mobilePriceRise(risePercent));

    }

    public static ArrayList<Plan> shallowCopy(ArrayList<Plan> plans) {
        return plans.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    public Plan cloneWithOutException() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
            return null;
        }
    }

    public static ArrayList<Plan> deepCopy(ArrayList<Plan> plans) {
        return plans.stream().map(x -> x.cloneWithOutException()).collect(Collectors.toCollection(ArrayList::new));

    }

    public static ArrayList<Plan> filterByMobileModelFunctional(HashMap<Integer, Plan> plans, String mobileModel) {
        return plans.values().stream().filter(x -> x.getHandset().getModel().contains(mobileModel))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Plan> filterPlans(ArrayList<Plan> plans, Predicate<Plan> criteria) {
        return plans.stream().filter(criteria).collect(Collectors.toCollection(ArrayList::new));
    }

}
