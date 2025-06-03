package utility;

import entity.Courier;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class CourierUtils {

    // === Task 1 ===

    public static void checkStatus(String status) {
        if (status.equalsIgnoreCase("Delivered")) {
            System.out.println("Order has been delivered.");
        } else if (status.equalsIgnoreCase("Processing")) {
            System.out.println("Order is still being processed.");
        } else if (status.equalsIgnoreCase("Cancelled")) {
            System.out.println("Order was cancelled.");
        } else {
            System.out.println("Unknown status.");
        }
    }

    public static void categorizeWeight(double weight) {
        String category;
        if (weight < 5) category = "Light";
        else if (weight < 15) category = "Medium";
        else category = "Heavy";

        switch (category) {
            case "Light" -> System.out.println("This parcel is Light.");
            case "Medium" -> System.out.println("This parcel is Medium.");
            case "Heavy" -> System.out.println("This parcel is Heavy.");
        }
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        String storedUsername = "admin";
        String storedPassword = "pass123";

        System.out.print("Enter username: ");
        String inputUser = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPass = scanner.nextLine();

        if (inputUser.equals(storedUsername) && inputPass.equals(storedPassword)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public static void assignCourier(int[] loads) {
        int min = loads[0], index = 0;
        for (int i = 1; i < loads.length; i++) {
            if (loads[i] < min) {
                min = loads[i];
                index = i;
            }
        }
        System.out.println("Assign parcel to Courier ID: " + index + " (Load: " + min + ")");
    }

    // === Task 2 ===

    public static void displayOrdersByUser(List<Courier> orders, int userId) {
        for (Courier c : orders) {
            if (c.getUserId() == userId) {
                System.out.println(c);
            }
        }
    }

    public static void trackCourierLive(String[] path) throws InterruptedException {
        int i = 0;
        while (i < path.length) {
            System.out.println("Location: " + path[i]);
            Thread.sleep(1000);
            i++;
        }
        System.out.println("Courier delivered.");
    }

    // === Task 3 ===

    public static void displayTrackingHistory(String[] locations) {
        System.out.println("Tracking History:");
        for (String loc : locations) {
            System.out.println("→ " + loc);
        }
    }

    public static int findNearestCourier(int[] distances) {
        int min = distances[0], index = 0;
        for (int i = 1; i < distances.length; i++) {
            if (distances[i] < min) {
                min = distances[i];
                index = i;
            }
        }
        System.out.println("Nearest Courier ID: " + index + " | Distance: " + min + "km");
        return index;
    }

    // === Task 4 ===

    public static void trackParcel2D() {
        String[][] parcels = {
            {"TRK123", "In Transit"},
            {"TRK456", "Out for Delivery"},
            {"TRK789", "Delivered"}
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter tracking number: ");
        String track = sc.nextLine();

        boolean found = false;
        for (String[] p : parcels) {
            if (p[0].equalsIgnoreCase(track)) {
                System.out.println("Status: " + p[1]);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Tracking number not found.");
    }

    public static boolean validateCustomerData(String data, String type) {
        return switch (type.toLowerCase()) {
            case "name" -> data.matches("[A-Z][a-z]+( [A-Z][a-z]+)*");
            case "address" -> data.matches("[A-Za-z0-9 ,]+");
            case "phone" -> data.matches("\\d{3}-\\d{3}-\\d{4}");
            default -> false;
        };
    }

    public static String formatAddress(String street, String city, String state, String zip) {
        return capitalize(street) + ", " + capitalize(city) + ", " + capitalize(state) + " - " + zip.replaceAll("\\D", "");
    }

    private static String capitalize(String str) {
        String[] words = str.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static String generateEmail(String name, String order, String address, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Dear " + name + ",\nYour order #" + order + " will be delivered to:\n" + address +
               "\nExpected Delivery: " + sdf.format(date) + "\nThank you for choosing us!";
    }

    public static double calculateShippingCost(String src, String dest, double weight) {
        int dist = Math.abs(src.length() - dest.length()) * 10;
        return dist * (weight / 5.0) * 10;
    }

    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", lower = "abcdefghijklmnopqrstuvwxyz",
               digits = "0123456789", special = "!@#$%&*", all = upper + lower + digits + special;
        SecureRandom rand = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        return sb.toString();
    }

    public static List<String> findSimilarAddresses(String[] db, String keyword) {
        List<String> results = new ArrayList<>();
        for (String addr : db) {
            if (addr.toLowerCase().contains(keyword.toLowerCase())) {
                results.add(addr);
            }
        }
        return results;
    }
}