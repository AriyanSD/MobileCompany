import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestCode {
        final static int flatRate = 5;

        public static void TestCodeBody(MobileCompany mobileCompany1)
                        throws CloneNotSupportedException, PlanException, PlanUserNameException {
                // Creating test objects

                // Creating phone Objects for adding to plans
                MobilePhone mobile1 = new MobilePhone("Galaxy S7", MobileType.Android, 4, 600);
                MobilePhone mobile2 = new MobilePhone("Iphone X", MobileType.IOS, 3, 300);
                MobilePhone mobile3 = new MobilePhone("pixel 4a", MobileType.Windows, 6, 500);
                MobilePhone mobile4 = new MobilePhone("huawei Y9prime", MobileType.Android, 4, 300);
                MobilePhone mobile5 = new MobilePhone("xiaomi 11 pro", MobileType.Android, 12, 900);
                // creating test plans objects
                PersonalPlan plan1 = UIMethods.creatPersonalPlan("123", 1, mobile1, 2, 3, new MyDate(2028, 2, 3),
                                "Paris");
                BusinessPlan plan2 = UIMethods.creatBussinesPlan("12345", 2, mobile2, 3, 2, new MyDate(2024, 7, 12), 4,
                                5);
                PersonalPlan plan3 = UIMethods.creatPersonalPlan("Patrik", 3, mobile2, 9, 1, new MyDate(2025, 12, 7),
                                "London");
                BusinessPlan plan4 = UIMethods.creatBussinesPlan("Mojtaba", 4, mobile1, 8, 6, new MyDate(2023, 7, 2), 1,
                                2);
                BusinessPlan plan5 = UIMethods.creatBussinesPlan("chock", 5, mobile3, 15, 1, new MyDate(2027, 2, 3), 5,
                                1);
                PersonalPlan plan6 = UIMethods.creatPersonalPlan("joe", 3000000, mobile3, 13, 12,
                                new MyDate(2034, 10, 7),
                                "Ohio");

                // Creat Users
                User user1 = new User("Ahmad", "1", 1, new Address(5, "Daneshladeh", "4", "Karaj"));
                User user2 = new User("Demitry", "1", 2, new Address(4, "Lenin", "5", "Fardis"));
                User user3 = new User("Arash", "1", 3, new Address(2, "Navab", "7", "Tehran"));
                User user4 = new User("Hirad", "1", 4, new Address(5, "Motahari", "6", "Tehran"));
                // Add Users to our mobile company
                UIMethods.addUser(mobileCompany1, user2);
                UIMethods.addUser(mobileCompany1, "Ali", "1", 5, new Address(2, "Hoseini", "4", "karaj"));
                UIMethods.addUser(mobileCompany1, user4);
                UIMethods.addUser(mobileCompany1, user3);
                UIMethods.addUser(mobileCompany1, user1);

                // Add Plan to our users in MobileCompany
                UIMethods.addPlan(mobileCompany1, 1, plan1);
                UIMethods.addPlan(mobileCompany1, 1, plan2);
                UIMethods.addPlan(mobileCompany1, 1, plan3);
                UIMethods.addPlan(mobileCompany1, 1, plan4);
                UIMethods.addPlan(mobileCompany1, 2, plan6);
                UIMethods.addPlan(mobileCompany1, 2, plan3);
                UIMethods.addPlan(mobileCompany1, 2, plan1);
                UIMethods.addPlan(mobileCompany1, 3, plan2);
                UIMethods.addPlan(mobileCompany1, 3, plan5);
                UIMethods.addPlan(mobileCompany1, 4, plan4);
                UIMethods.addPlan(mobileCompany1, 4, plan5);
                UIMethods.addPlan(mobileCompany1, 4, plan6);
                UIMethods.addPlan(mobileCompany1, 5, plan2);

                // Lab8
                ArrayList<Plan> plans = Plan.deepCopy(mobileCompany1.allPlans());
                System.out.println("\u001B[32m" + "---------------------------------------------------" + "\u001B[0m");
                System.out.println("\u001B[32m"
                                + "--------------------------Search and filter all the plans with the capLimit<100 and sort the filtered list based on internet quota and print the sorted filtered list:-------------------------"
                                + "\u001B[0m");
                plans.stream().filter(x -> x.getCapLimit() < 100).sorted(Comparator.comparing(Plan::getInternetQuota))
                                .forEach(System.out::println);

                System.out.println("\u001B[32m"
                                + "--------------------------Search for the username containing 123 in the list of plans and displays the information for all the plans with the given username pattern.------------------------"
                                + "\u001B[0m");
                plans.stream().filter(x -> x.getUserName().contains("a")).forEach(x -> x.print());

                Double t1 = plans.stream().filter(x -> x.getUserName().contains("123"))
                                .mapToDouble(x -> x.calcPayment(flatRate)).sum();

                System.out.println("\u001B[32m" + "Total Payments for users contain 123 in their user name: "
                                + "\u001B[0m" + t1);

                System.out.println("\u001B[32m"
                                + "------------------------Find the first plan with the payment between $20 to $50 and display the username, ID and monthly payment ---------------------------"
                                + "\u001B[0m");
                Predicate<Plan> c2 = x -> x.calcPayment(flatRate) < 50 && x.calcPayment(flatRate) > 20;
                plans.stream().filter(c2).findFirst()
                                .map(x -> String.format("%s %s %s", x.getUserName(), x.getId(),
                                                x.calcPayment(flatRate)))
                                .ifPresent(System.out::println);

                System.out.println("\u001B[32m"
                                + "------------------------Find all plans with the payment between $20 to $50, sort them by ID and display the username, ID and monthly payment for each plan.---------------------------"
                                + "\u001B[0m");
                plans.stream().filter(c2)
                                .sorted(Comparator.comparing(Plan::getId)).map(x -> String.format("%s %s %s",
                                                x.getUserName(), x.getId(), x.calcPayment(flatRate)))
                                .forEach(System.out::println);

                Double d2 = plans.stream().filter(c2)
                                .mapToDouble(x -> x.calcPayment(flatRate)).sum();
                System.out.println("\u001B[32m"
                                + "Calculate the total monthly payment for all plans with the payment between $20 to $50: "
                                + "\u001B[0m" + d2);

                System.out.println("\u001B[32m"
                                + "-----------------Filter all plans by userName123----------------------------------"
                                + "\u001B[0m");
                Predicate<Plan> c1 = x -> x.getUserName().equals("123");
                ArrayList<Plan> plans1 = Plan.filterPlans(plans, c1);
                UIMethods.printPlans(plans1);
                System.out.println("\u001B[32m"
                                + "-----------------------Filter All Plans by date2020----------------------------"
                                + "\u001B[0m");
                UIMethods.printPlans(Plan.filterPlans(plans, x -> x.getExpiryDate().getYear() == 2020));

                System.out.println("\u001B[32m"
                                + "-------------------------Filter all plans by OS:IOS--------------------------"
                                + "\u001B[0m");
                ArrayList<Plan> p2 = Plan.filterPlans(plans, x -> x.getHandset().getType().toString().equals("IOS"))
                                .stream().sorted(Comparator.comparing(Plan::getHandsetPrice))
                                .collect(Collectors.toCollection(ArrayList::new));
                UIMethods.printPlans(p2);

                System.out.println("\u001B[32m"
                                + "--------------------------Aggregate (by using group by) the list of plans based on expiryDate year and print the report-------------------------"
                                + "\u001B[0m");

                Map<Integer, List<Plan>> p3 = plans.stream().collect(Collectors.groupingBy(Plan::getExpiryDateYear));
                for (Integer year : p3.keySet()) {
                        System.out.println("Year: " + year);
                        ArrayList<Plan> plansList = new ArrayList<>(p3.get(year));
                        UIMethods.printPlans(plansList);

                }

                // // Lab6
                // // testing binary file and list of plans
                // System.out.println(
                // "\u001B[34m" + "_____________________________________________________testing
                // binary file and list of
                // plans______________________________________________________"
                // + "\u001B[0m");
                // System.out.println("\u001B[32m" + "////////////////////Save to
                // binary://///////////////" + "\u001B[0m");
                // UIMethods.savePlansToBinery(mobileCompany1.allPlans(), "plans.ser");
                // System.out.println(
                // "\u001B[32m" + "//////////////////////Loaded from binary///////////////" +
                // "\u001B[0m");
                // HashMap<Integer, Plan> plans = Plan.load("plans.ser");
                // UIMethods.printPlans(plans);
                // Plan plan7 = UIMethods.creatPersonalPlan("ar", 3030000, mobile4, 5, 4, new
                // MyDate(2222, 3, 4), "tst");
                // plans.put(plan7.getId(), plan7);
                // System.out.println("\u001B[32m"
                // + "////////////////////Add a new Plan to plans then Save to
                // binary://///////////////"
                // + "\u001B[0m");
                // UIMethods.savePlansToBinery(plans, "plans.ser");
                // plans.clear();
                // System.out.println("\u001B[32m"
                // + "//////////////////////Loaded from binary with new PlanID:
                // 3030000///////////////"
                // + "\u001B[0m");
                // plans = Plan.load("plans.ser");
                // UIMethods.printPlans(plans);
                // System.out.println(
                // "\u001B[34m" +
                // "_________________________________________________________________________________________________________________________________________________"
                // + "\u001B[0m");

                // // testing binary file and list of users
                // System.out.println(
                // "\u001B[34m" + "_____________________________________________________testing
                // binary file and list of
                // users______________________________________________________"
                // + "\u001B[0m");
                // System.out.println("\u001B[32m" + "////////////////////Save to
                // binary://///////////////" + "\u001B[0m");
                // UIMethods.saveUsersToBinery(mobileCompany1.getUsers(), "users.ser");
                // System.out.println(
                // "\u001B[32m" + "//////////////////////Loaded from binary///////////////" +
                // "\u001B[0m");
                // HashMap<Integer, User> users = User.load("users.ser");
                // UIMethods.printUsers(users);
                // System.out.println("\u001B[32m"
                // + "////////////////////Add a new User to users then Save to
                // binary://///////////////"
                // + "\u001B[0m");
                // User usr = new User("user tst", "1", 16542, new Address(1, "bac", "4",
                // "arb"));
                // Plan plan8 = UIMethods.creatPersonalPlan("tst3", 3050000, mobile3, 5, 4, new
                // MyDate(20222, 3, 4), "k");
                // usr.addPlan(plan8);
                // users.put(usr.getUserID(), usr);
                // User.save(users, "users.ser");
                // users.clear();
                // System.out.println(
                // "\u001B[32m" + "//////////////////////Loaded from binary with new
                // UserName:user tst ///////////////"
                // + "\u001B[0m");
                // users = User.load("users.ser");
                // User.printUsers(users);
                // System.out.println(
                // "\u001B[34m" +
                // "_________________________________________________________________________________________________________________________________________________"
                // + "\u001B[0m");

                // // testing text file and list of plans with toDilimitedString
                // System.out.println(
                // "\u001B[34m" + "_____________________________________________________testing
                // text file and list of plans with
                // toDilimitedString______________________________________________________"
                // + "\u001B[0m");
                // System.out.println(
                // "\u001B[32m" + "////////////////////Save to TextFiles://///////////////" +
                // "\u001B[0m");
                // UIMethods.savePlansToTextFiles(mobileCompany1.allPlans(), "plans.txt");
                // System.out.println("\u001B[32m" + "//////////////////////Loaded from
                // TextFiles///////////////"
                // + "\u001B[0m");
                // HashMap<Integer, Plan> plans2 = UIMethods.loadPlanFromTextFile("plans.txt");
                // UIMethods.printPlans(plans2);
                // System.out.println(
                // "\u001B[32m" + "////////////////////Add a new Plan to plans then Save to
                // TextFiles://///////////////"
                // + "\u001B[0m");
                // Plan plan9 = UIMethods.creatPersonalPlan("tst4", 3130000, mobile3, 5, 4, new
                // MyDate(20222, 3, 4), "k");
                // plans2.put(plan9.getId(), plan9);
                // UIMethods.savePlansToTextFiles(plans2, "plans.txt");
                // plans2.clear();
                // System.out.println(
                // "\u001B[32m" + "//////////////////////Loaded from TextFiles with new PlanID:
                // 3130000///////////////"
                // + "\u001B[0m");
                // plans2 = UIMethods.loadPlanFromTextFile("plans.txt");
                // UIMethods.printPlans(plans2);
                // System.out.println(
                // "\u001B[34m" +
                // "_________________________________________________________________________________________________________________________________________________"
                // + "\u001B[0m");

                // // testing text file and list of users with toDilimitedString
                // System.out.println(
                // "\u001B[34m" + "_____________________________________________________testing
                // text file and list of users with
                // toDilimitedString______________________________________________________"
                // + "\u001B[0m");
                // System.out.println(
                // "\u001B[32m" + "////////////////////Save to TextFiles://///////////////" +
                // "\u001B[0m");
                // UIMethods.saveUsersToTextFiles(mobileCompany1.getUsers(), "users.txt");
                // System.out.println("\u001B[32m" + "//////////////////////Loaded from
                // TextFiles///////////////"
                // + "\u001B[0m");
                // HashMap<Integer, User> users2 = UIMethods.loadUserFromTextFile("users.txt");
                // UIMethods.printUsers(users2);
                // System.out.println(
                // "\u001B[32m" + "////////////////////Add a new User to Users then Save to
                // TextFiles://///////////////"
                // + "\u001B[0m");
                // User usr2 = new User("user tst2", "1", 16232, new Address(1, "bac", "4",
                // "arb"));
                // usr2.addPlan(plan5);
                // users2.put(usr2.getUserID(), usr2);
                // UIMethods.saveUsersToTextFiles(users2, "users.txt");
                // users2.clear();
                // System.out.println(
                // "\u001B[32m" + "//////////////////////Loaded from TextFiles with new
                // UserName: user tst2///////////////"
                // + "\u001B[0m");
                // users2 = UIMethods.loadUserFromTextFile("users.txt");
                // UIMethods.printUsers(users2);
                // System.out.println(
                // "\u001B[34m" +
                // "_________________________________________________________________________________________________________________________________________________"
                // + "\u001B[0m");

                // // mobileCompany and binary file
                // System.out.println(
                // "\u001B[34m" +
                // "_____________________________________________________mobileCompany and
                // binary file______________________________________________________"
                // + "\u001B[0m");
                // System.out.println("\u001B[32m" + "/////////////Save mobileCompany1 to binary
                // files///////////////"
                // + "\u001B[0m");
                // UIMethods.saveCompanyToBinery(mobileCompany1, "company.ser");
                // System.out.println(
                // "\u001B[32m" + "/////////////load mobileCompany1 to mobileCompany2 from
                // binary files///////////////"
                // + "\u001B[0m");
                // MobileCompany mobileCompany2 = new MobileCompany();// using default
                // constructor
                // UIMethods.loadCompanyFromBinery(mobileCompany2, "company.ser");// all users
                // and all plans are loaded
                // mobileCompany2.print();
                // System.out.println(
                // "\u001B[32m" + "/////////////Add a new User to mobileCompany2 the save it to
                // binary files///////////////"
                // + "\u001B[0m");
                // User user5 = new User("user tst 4", "1", 4, new Address(2, "a", "b", "c"));
                // mobileCompany2.addUser(user5);
                // PersonalPlan plan10 = UIMethods.creatPersonalPlan("23", 3007000, mobile5, 6,
                // 4, new MyDate(122, 3, 4),
                // "cit");
                // mobileCompany2.addPlan(5, plan10);
                // UIMethods.saveCompanyToBinery(mobileCompany2, "company.ser");
                // System.out.println(
                // "\u001B[32m" + "/////////////load mobileCompany2 to mobileCompany3 from
                // binary files with new User userName: user tst4///////////////"
                // + "\u001B[0m");
                // MobileCompany mobileCompany3 = new MobileCompany();
                // UIMethods.loadCompanyFromBinery(mobileCompany3, "company.ser");
                // mobileCompany3.print();
                // System.out.println(
                // "\u001B[34m" +
                // "_________________________________________________________________________________________________________________________________________________"
                // + "\u001B[0m");

                // // mobileCompany and text file
                // System.out.println(
                // "\u001B[34m" +
                // "_____________________________________________________mobileCompany and text
                // file______________________________________________________"
                // + "\u001B[0m");
                // System.out.println("\u001B[32m" + "/////////////Save mobileCompany1 to Text
                // files///////////////"
                // + "\u001B[0m");
                // UIMethods.saveCompanyToTextFiles(mobileCompany1, "company.txt");
                // System.out.println("\u001B[32m"
                // + "/////////////Load mobileCompany1 to mobileCompany4 from text
                // files///////////////"
                // + "\u001B[0m");
                // MobileCompany mobileCompany4 = new MobileCompany();// using default
                // constructor
                // UIMethods.loadCompanyFromTextFiles(mobileCompany4, "company.txt");// all
                // users and all plans are loaded
                // mobileCompany4.print();
                // System.out.println(
                // "\u001B[32m" + "/////////////Add a new User to mobileCompany4 the save it to
                // text files///////////////"
                // + "\u001B[0m");
                // User user6 = new User("user tst5", "1", 126626, new Address(2, "a", "b",
                // "c"));
                // mobileCompany4.addUser(user6);
                // PersonalPlan plan11 = UIMethods.creatPersonalPlan("23", 3007020, mobile5, 6,
                // 4, new MyDate(122, 3, 4),
                // "cit");
                // mobileCompany4.addPlan(user6.getUserID(), plan11);
                // UIMethods.saveCompanyToTextFiles(mobileCompany4, "company.txt");
                // System.out.println(
                // "\u001B[32m" + "/////////////load mobileCompany4 to mobileCompany5 from text
                // files with new User userName: user tst5///////////////"
                // + "\u001B[0m");
                // MobileCompany mobileCompany5 = new MobileCompany();
                // UIMethods.loadCompanyFromTextFiles(mobileCompany5, "company.txt");
                // mobileCompany5.print();
                // System.out.println(
                // "\u001B[34m" +
                // "_________________________________________________________________________________________________________________________________________________"
                // + "\u001B[0m");

                // // Lab5
                // System.out.println(
                // "**********************User1 Data aggregation per
                // model:***************************");
                // user1.reportPaymentsPerMobileModel();
                // System.out
                // .println("**********************MobileCompany Data aggregation per
                // city******************************");
                // mobileCompany1.reportPaymentPerCity();
                // System.out.println(
                // "**********************MobileCompany Data aggregation per
                // model******************************");
                // mobileCompany1.reportPaymentsPerMobileModel();
                // // DeepCopy and ShallowCopy User1 plans
                // ArrayList<Plan> deepCopyPlans = UIMethods.deepCopyPlans(user1);
                // ArrayList<Plan> shallowCopyPlans = UIMethods.shallowCopyPlans(user1);

                // // Change Plan1
                // System.out.println("********Change plan1 city to New York********");
                // System.out.println("********Plan 1 City befor change:********" +
                // plan1.getCity());
                // plan1.setCity("New York");
                // System.out.println("********Plan 1 City After change:********" +
                // plan1.getCity());
                // System.out.println("********Change plan1 caplimit to 50:********");
                // System.out.println("********plan1 caplimit befor change:********" +
                // plan1.getCapLimit());
                // plan1.setCapLimit(50);
                // System.out.println("********plan1 caplimit after change:********" +
                // plan1.getCapLimit());
                // UIMethods.addPlan(user1, plan6);
                // System.out.println("********plan2 Mobile model befor change:********" +
                // plan2.getHandset().getModel());
                // plan2.setMobileModel("Iphone 10");
                // System.out.println("********plan2 Mobile model after change:********" +
                // plan2.getHandset().getModel());
                // System.out.println("********plan2 Expirydate befor change:********" +
                // plan2.getExpiryDate().toString());
                // plan2.setExpiryDate(new MyDate(2020, 1, 1));
                // System.out.println("********plan2 Expirydate after change:********" +
                // plan2.getExpiryDate().toString());

                // // Sort User1 plans by expirydate
                // ArrayList<Plan> sortedPlans = UIMethods.sortPlansByDate(user1);
                // System.out.println("********Not sorted Plans:********");
                // UIMethods.printPlans(user1.getPlans());
                // System.out.println("********Sorted Plans:********");
                // UIMethods.printPlans(sortedPlans);

                // // Print deepCopy and shallowCopy lists as well as User original plans
                // System.out.println("********Deep Copy List:********");
                // UIMethods.printPlans(deepCopyPlans);
                // System.out.println("********ShallowCopy List********");
                // UIMethods.printPlans(shallowCopyPlans);
                // System.out.println("********Original List********");
                // UIMethods.printPlans(user1.getPlans());

                // // Deep Copy and Shallow Copy Users
                // ArrayList<User> deepCopyUsers = mobileCompany1.deepCopyUsers();
                // ArrayList<User> shallowCopyUsers = mobileCompany1.shallowCopyUsers();

                // // Add a new User and change a User city to London
                // UIMethods.addUser(mobileCompany1, "New Ali", "1", 12, new Address(2,
                // "Ahmadi",
                // "12", "Kerman"));
                // user2.setCity("London");

                // // Sort Company users
                // ArrayList<User> sortedUsers = UIMethods.sortUsres(mobileCompany1);
                // System.out.println("********Not sorted Users:********");
                // UIMethods.printUsers(mobileCompany1.getUsers());
                // System.out.println("********Sorted Users:********");
                // UIMethods.printUsers(sortedUsers);

                // // Print deepCopy and shallowCopy lists as well as company original users
                // System.out.println("********Deep Copy Users List:********");
                // UIMethods.printUsers(deepCopyUsers);
                // System.out.println("********ShallowCopy Users List********");
                // UIMethods.printUsers(shallowCopyUsers);
                // System.out.println("********Original Users List********");
                // UIMethods.printUsers(mobileCompany1.getUsers());

                // // Make a clone of mobileCompany
                // MobileCompany mobileCompanyCopyTest = mobileCompany1.clone();
                // // Apply changes to mobile company
                // mobileCompany1.setName("Opal mall");
                // mobileCompanyCopyTest.removeUser(1);
                // System.out.println(
                // "********Company 1 name should be opal Mall and company name 2 should be
                // nakhlCity__User1 should not exist in company2******** ");
                // System.out.println("********Company 1********");
                // mobileCompany1.print();
                // System.out.println("********Company 2:********");
                // mobileCompanyCopyTest.print();
                // Lab4
                // DeepCopy and ShallowCopy User1 plans
                // ArrayList<Plan> deepCopyPlans = UIMethods.deepCopyPlans(user1);
                // ArrayList<Plan> shallowCopyPlans = UIMethods.shallowCopyPlans(user1);

                // // Change Plan1
                // System.out.println("********Change plan1 city to New York********");
                // System.out.println("********Plan 1 City befor change:********" +
                // plan1.getCity());
                // plan1.setCity("New York");
                // System.out.println("********Plan 1 City After change:********" +
                // plan1.getCity());
                // System.out.println("********Change plan1 caplimit to 50:********");
                // System.out.println("********plan1 caplimit befor change:********" +
                // plan1.getCapLimit());
                // plan1.setCapLimit(50);
                // System.out.println("********plan1 caplimit after change:********" +
                // plan1.getCapLimit());
                // UIMethods.addPlan(user1, plan6);
                // System.out.println("********plan2 Mobile model befor change:********" +
                // plan2.getHandset().getModel());
                // plan2.setMobileModel("Iphone 10");
                // System.out.println("********plan2 Mobile model after change:********" +
                // plan2.getHandset().getModel());
                // System.out.println("********plan2 Expirydate befor change:********" +
                // plan2.getExpiryDate().toString());
                // plan2.setExpiryDate(new MyDate(2020, 1, 1));
                // System.out.println("********plan2 Expirydate after change:********" +
                // plan2.getExpiryDate().toString());

                // // Sort User1 plans by expirydate
                // ArrayList<Plan> sortedPlans = UIMethods.sortPlansByDate(user1);
                // System.out.println("********Not sorted Plans:********");
                // UIMethods.printPlans(user1.getPlans());
                // System.out.println("********Sorted Plans:********");
                // UIMethods.printPlans(sortedPlans);

                // // Print deepCopy and shallowCopy lists as well as User original plans
                // System.out.println("********Deep Copy List:********");
                // UIMethods.printPlans(deepCopyPlans);
                // System.out.println("********ShallowCopy List********");
                // UIMethods.printPlans(shallowCopyPlans);
                // System.out.println("********Original List********");
                // UIMethods.printPlans(user1.getPlans());

                // // Deep Copy and Shallow Copy Users
                // ArrayList<User> deepCopyUsers = mobileCompany1.deepCopyUsers();
                // ArrayList<User> shallowCopyUsers = mobileCompany1.shallowCopyUsers();

                // // Add a new User and change a User city to London
                // UIMethods.addUser(mobileCompany1, "New Ali", 12, new Address(2, "Ahmadi",
                // "12", "Kerman"));
                // user2.setCity("London");

                // // Sort Company users
                // ArrayList<User> sortedUsers = UIMethods.sortUsres(mobileCompany1);
                // System.out.println("********Not sorted Users:********");
                // UIMethods.printUsers(mobileCompany1.getUsers());
                // System.out.println("********Sorted Users:********");
                // UIMethods.printUsers(sortedUsers);

                // // Print deepCopy and shallowCopy lists as well as company original users
                // System.out.println("********Deep Copy Users List:********");
                // UIMethods.printUsers(deepCopyUsers);
                // System.out.println("********ShallowCopy Users List********");
                // UIMethods.printUsers(shallowCopyUsers);
                // System.out.println("********Original Users List********");
                // UIMethods.printUsers(mobileCompany1.getUsers());

                // // Make a clone of mobileCompany
                // MobileCompany mobileCompany2 = mobileCompany1.clone();
                // // Apply changes to mobile company
                // mobileCompany1.setName("Opal mall");
                // mobileCompany2.removeUser(1);
                // System.out
                // .println("********Company 1 name should be opal Mall and company name 2
                // should be nakhlCity__User1 should not exist in company2******** ");
                // System.out.println("********Company 1********");
                // mobileCompany1.print();
                // System.out.println("********Company 2:********");
                // mobileCompany2.print();

                // // _____________________________Lab1 Test
                // // code______________________________________
                // // Put test plans in an arraylist
                // ArrayList<Plan> planList = new ArrayList<>();
                // planList.add(plan1);
                // planList.add(plan2);
                // planList.add(plan3);
                // planList.add(plan4);

                // // print plans by print method
                // System.out.println("****Print plans by print method:**** ");
                // for (Plan plan : planList) {
                // plan.print();
                // }
                // // print plans by to string
                // System.out.println("****Print plans by too string:**** ");
                // for (Plan plan : planList) {
                // System.out.println(plan);
                // }
                // double total = 0;
                // // calculate total payment
                // for (Plan plan : planList) {
                // total+=plan.calcPayment(flatRate);
                // }
                // System.out.println("****Total payment: Lab1: Should be 261 ****" + total);
                // // ___________________________________Lab2 Test
                // // Codes________________________________________
                // // Print user 1 by print method:
                // UIMethods.addPlan(user1, plan1);
                // UIMethods.addPlan(user1, plan2);
                // UIMethods.addPlan(user1, plan2);// not successfull

                // System.out.println("****Print user 1 by print method:****");
                // user1.print();

                // // print user1 by to string method:
                // System.out.println("****print user1 by to string method:****");
                // System.out.println(user1.toString());

                // // Find an invalid plan
                // System.out.println("****findPlan method:should be invalid message:****");
                // Plan tmpPlan = UIMethods.findPlan(user1, 7);
                // // Find an valid plan
                // System.out.println("****findPlan method:Should be valid and printed:****");
                // tmpPlan = UIMethods.findPlan(user1, 2);
                // // print valid plan
                // System.out.println("****Print above plan:****");
                // tmpPlan.print();

                // // change user name of our valid plan
                // tmpPlan.setUserName("Robert");
                // // chang handset model of our valid plan
                // tmpPlan.setMobileModel("Iphone 6");

                // // change city of our user
                // user1.setCity("Fardis");
                // // fully change our address of user
                // user1.setAddress(new Address(2, "Arghavan", "2", "Fardis"));

                // // calculate and print total payment of user
                // System.out.println("****Total payment User1: should be 87.85**** " +
                // user1.calcTotalPayment(flatRate));

                // // Rise all of our user plan handet price
                // System.out.println("****Total payment after 10% Rice all phone
                // price:SB:91.6****");
                // user1.mobilePriceRiseAll(0.1);

                // // print total payment after our price rise
                // System.out.println("Total payment: " + user1.calcTotalPayment(flatRate));

                // // filter our user plans by its handset moel
                // ArrayList<Plan> filtredPlans = UIMethods.filterByMobileModel("Iphone 6",
                // user1);
                // // print our filtred plan should be just :Iphone 6 models
                // System.out.println("****print our filtred plan should be just Iphone 6
                // models****");
                // Plan.printPlans(filtredPlans);
                // // _______________________________________Lab3 test
                // // codes__________________________________
                // // atempt log in
                // System.out.println("****Not valid Log in****");
                // UIMethods.validateAdmin("232", "12345", mobileCompany1); // Not valid
                // System.out.println("****Valod Login****");
                // UIMethods.validateAdmin("ar12", "ari00", mobileCompany1);// valid

                // // Add users to our mobile company by both methods
                // System.out.println(
                // "****Add user method test: The 2nd and 6th message should be fail other
                // message should show success****");
                // UIMethods.addUser(mobileCompany1, user2);
                // UIMethods.addUser(mobileCompany1, "Ali", 2, new Address(2, "Hoseini", "4",
                // "karaj"));// Not succsusfull
                // UIMethods.addUser(mobileCompany1, "Ali", 5, new Address(2, "Hoseini", "4",
                // "karaj"));
                // UIMethods.addUser(mobileCompany1, user4);
                // UIMethods.addUser(mobileCompany1, user3);
                // UIMethods.addUser(mobileCompany1, user4);// Not succsusfull
                // UIMethods.addUser(mobileCompany1, user1);
                // System.out.println("****Add plan method test: The 1st,2nd,7th,15th sould
                // fail**** ");
                // // Add serval plans by Mobile company method
                // UIMethods.addPlan(mobileCompany1, 1, plan1);// not successfull
                // UIMethods.addPlan(mobileCompany1, 1, plan2); // not successfull
                // UIMethods.addPlan(mobileCompany1, 1, plan3);
                // UIMethods.addPlan(mobileCompany1, 1, plan4);
                // UIMethods.addPlan(mobileCompany1, 2, plan6);
                // UIMethods.addPlan(mobileCompany1, 2, plan3);
                // UIMethods.addPlan(mobileCompany1, 2, plan3);// not successfull
                // UIMethods.addPlan(mobileCompany1, 2, plan1);
                // UIMethods.addPlan(mobileCompany1, 3, plan2);
                // UIMethods.addPlan(mobileCompany1, 3, plan5);
                // UIMethods.addPlan(mobileCompany1, 4, plan4);
                // UIMethods.addPlan(mobileCompany1, 4, plan5);
                // UIMethods.addPlan(mobileCompany1, 4, plan6);
                // UIMethods.addPlan(mobileCompany1, 5, plan2);
                // UIMethods.addPlan(mobileCompany1, 12, plan6); // not successfull

                // // Creat and add some plans by creatBussinesPLan and creatPersonalPlan
                // methods
                // System.out.println("****Creat business and personal plans method: the 1st and
                // 2nd should fail****");
                // UIMethods.creatBusinessPlan(mobileCompany1, 2, "Ron", 6, mobile5, 5, 4, new
                // MyDate(2045, 5, 7), 5, 12);// not
                // // succsusfull
                // UIMethods.creatPersonalPlan(mobileCompany1, 17, "Donald", 12, mobile3, 4, 6,
                // new MyDate(2047, 3, 7), "Kabol");// not
                // // succsusfull
                // UIMethods.creatBusinessPlan(mobileCompany1, 2, "Ron", 8, mobile5, 5, 4, new
                // MyDate(2045, 5, 7), 5, 12);
                // UIMethods.creatPersonalPlan(mobileCompany1, 4, "Donald", 10, mobile3, 4, 6,
                // new MyDate(2047, 3, 7), "Kabol");

                // System.out.println("****Print user3 by mobile company print method:****");
                // UIMethods.printUser(mobileCompany1, 3);
                // System.out.println("****Print plan3 by mobile company print method:****");
                // UIMethods.printPlan(mobileCompany1, 3, 1);

                // // print all mobile company users:
                // System.out.println("****print all mobile company users:****");
                // mobileCompany1.print();

                // // raise mobile price for all users in mobile company
                // System.out.println("****raise mobile price for all users in mobile company by
                // 10%****");
                // mobileCompany1.mobilePriceRise(0.1);
                // System.out.println("****Print mobile company:****");
                // mobileCompany1.print();

                // // Print total payment for given user id
                // System.out.println("****total payment of user 1 sould be: 310.814525****");
                // System.out.println(mobileCompany1.calcTotalPayments(1));
                // // print total payment of all users
                // System.out.println("****total price should be:1420.085021****");
                // System.out.println(mobileCompany1.calcTotalPayments());

                // // call allplans method and store it
                // ArrayList<Plan> plans = new ArrayList<>();
                // plans = mobileCompany1.allPlans();
                // // print all plans
                // System.out.println("****Print all plans of mobile Company:(Testing allPlan
                // method)****");
                // Plan.printPlans(plans);

                // // filter mobilecompany plans by expirydate
                // System.out.println("****Plan 2,3,4,5 IS EXPIRED by the date of 2027/5/10 and
                // should be shown below: ****");
                // ArrayList<Plan> filtredPlans1 = new ArrayList<>();
                // filtredPlans1 = mobileCompany1.filterByExpiryDate(new MyDate(2027, 5, 10));
                // Plan.printPlans(filtredPlans1);

                // // filter by mobile model
                // System.out.println("****Plan with id 8 has the moel of xiaomi 11 pro an
                // should shown below:****");
                // ArrayList<Plan> filtredPlans2 = new ArrayList<>();
                // filtredPlans2 = mobileCompany1.filterByMobileModel("xiaomi 11 pro");
                // Plan.printPlans(filtredPlans2);

                // // Assignment test code
                // System.out.println("****Report by cities:****");
                // UIMethods.reportPaymentPerCity(mobileCompany1, flatRate);
                // System.out.println("****Report by models:User1****");
                // UIMethods.reportPaymentsPerMobileModel(user1, flatRate);
                // System.out.println("Report by models:Mobile company");
                // UIMethods.reportPaymentsPerMobileModel(mobileCompany1, flatRate);

                // System.out.println(
                // "****Remove plan 2 from user 3 and print plans: It should only show plan 5
                // and removed plan successfully message:****");
                // UIMethods.removePlan(user3, 2);
                // UIMethods.printPlans(user3.getPlans());
                // System.out.println("****remove plan 1 from user3 :Should have been not
                // successfull message****");
                // UIMethods.removePlan(user3, 1);
                // System.out.println("****remove plan 8 from user4 by mobile company removePlan
                // method: successfull message:****");
                // UIMethods.removePlan(mobileCompany1, 4, 10);
                // System.out.println("Remove plan 16 (Not exist ) from user 1 : should be not
                // successfull mesage: ");
                // UIMethods.removePlan(mobileCompany1, 1, 16);
                // System.out.println("****Remove plan2 from user 36 (Not exist) should be not
                // successful:****");
                // UIMethods.removePlan(mobileCompany1, 36, 2);
                // mobileCompany1.addUser("Ahmad", new Address(2, "Test", "55", "Test"));
                // mobileCompany1.addUser("Ali", new Address(2, "Test", "55", "Test"));
                // System.out.println("****Two automatic generated user added too mobile Company
                // by following IDs :100,101**** ");
                // UIMethods.printUser(mobileCompany1, 100);
                // UIMethods.printUser(mobileCompany1, 101);

        }

}
