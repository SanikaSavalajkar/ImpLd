package main;

import dao.CourierServiceDb;
import entity.Courier;
import exception.TrackingNumberNotFoundException;
import utility.CourierUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourierServiceDb service = new CourierServiceDb();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        boolean running = true;

        while (running) {
            System.out.println("\n=== Courier Management System ===");
            System.out.println("1. Place New Order");
            System.out.println("2. Check Order Status");
            System.out.println("3. Cancel Order");
            System.out.println("4. Show All Orders");
            System.out.println("5. Validate Customer Info");
            System.out.println("6. Format Address");
            System.out.println("7. Generate Order Confirmation Email");
            System.out.println("8. Calculate Shipping Cost");
            System.out.println("9. Generate Secure Password");
            System.out.println("10. Find Similar Addresses");
            System.out.println("11. Parcel Tracking (2D Array)");
            System.out.println("12. Real-Time Courier Tracking");
            System.out.println("13. Show Orders by User ID");
            System.out.println("14. Assign Nearest Courier");
            System.out.println("15. Weight Category");
            System.out.println("16. Login System");
            System.out.println("17. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Courier ID: ");
                        int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Sender Name: ");
                        String sender = scanner.nextLine();
                        System.out.print("Sender Address: ");
                        String sAddr = scanner.nextLine();
                        System.out.print("Receiver Name: ");
                        String receiver = scanner.nextLine();
                        System.out.print("Receiver Address: ");
                        String rAddr = scanner.nextLine();
                        System.out.print("Weight (kg): ");
                        double weight = scanner.nextDouble(); scanner.nextLine();
                        System.out.print("Status: ");
                        String status = scanner.nextLine();
                        System.out.print("Tracking Number: ");
                        String trackingNumber = scanner.nextLine();
                        System.out.print("Delivery Date (yyyy-MM-dd): ");
                        Date date = sdf.parse(scanner.nextLine());

                        Courier c = new Courier(id, sender, sAddr, receiver, rAddr, weight, status, trackingNumber, date, 1);
                        if (service.insertCourier(c))
                            System.out.println("Order placed successfully!");
                    }

                    case 2 -> {
                        System.out.print("Enter tracking number: ");
                        String track = scanner.nextLine();
                        Courier c = service.getCourierByTrackingNumber(track);
                        if (c != null) {
                            CourierUtils.checkStatus(c.getStatus());
                        } else {
                            throw new TrackingNumberNotFoundException("Tracking number not found.");
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter tracking number to cancel: ");
                        String track = scanner.nextLine();
                        if (service.updateStatus(track, "Cancelled")) {
                            System.out.println("Order cancelled.");
                        } else {
                            throw new TrackingNumberNotFoundException("Order not found.");
                        }
                    }

                    case 4 -> {
                        List<Courier> all = service.getDeliveryHistory();
                        for (Courier c : all) System.out.println(c);
                    }

                    case 5 -> {
                        System.out.print("Enter detail type (name/address/phone): ");
                        String type = scanner.nextLine();
                        System.out.print("Enter value: ");
                        String value = scanner.nextLine();
                        boolean valid = CourierUtils.validateCustomerData(value, type);
                        System.out.println(valid ? "Valid" : "Invalid");
                    }

                    case 6 -> {
                        System.out.print("Street: ");
                        String street = scanner.nextLine();
                        System.out.print("City: ");
                        String city = scanner.nextLine();
                        System.out.print("State: ");
                        String state = scanner.nextLine();
                        System.out.print("ZIP: ");
                        String zip = scanner.nextLine();
                        String formatted = CourierUtils.formatAddress(street, city, state, zip);
                        System.out.println("Formatted Address:\n" + formatted);
                    }

                    case 7 -> {
                        System.out.print("Customer Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Order Number: ");
                        String order = scanner.nextLine();
                        System.out.print("Delivery Address: ");
                        String addr = scanner.nextLine();
                        System.out.print("Expected Delivery (yyyy-MM-dd): ");
                        Date date = sdf.parse(scanner.nextLine());
                        System.out.println(CourierUtils.generateEmail(name, order, addr, date));
                    }

                    case 8 -> {
                        System.out.print("Source Address: ");
                        String src = scanner.nextLine();
                        System.out.print("Destination Address: ");
                        String dst = scanner.nextLine();
                        System.out.print("Weight (kg): ");
                        double wt = scanner.nextDouble();
                        double cost = CourierUtils.calculateShippingCost(src, dst, wt);
                        System.out.println("Shipping cost: ₹" + cost);
                    }

                    case 9 -> {
                        System.out.print("Password length: ");
                        int len = scanner.nextInt(); scanner.nextLine();
                        String password = CourierUtils.generatePassword(len);
                        System.out.println("Generated Password: " + password);
                    }

                    case 10 -> {
                        String[] db = {
                            "123 Prabhat Road, Pune",
                            "456 Marine Drive, Mumbai",
                            "321 FC Road, Pune"
                        };
                        System.out.print("Search for: ");
                        String keyword = scanner.nextLine();
                        List<String> matches = CourierUtils.findSimilarAddresses(db, keyword);
                        matches.forEach(System.out::println);
                    }

                    case 11 -> {
                        CourierUtils.trackParcel2D();
                    }

                    case 12 -> {
                        String[] path = {"Warehouse", "Sorting Center", "On Route", "Out for Delivery", "Delivered"};
                        CourierUtils.trackCourierLive(path);
                    }

                    case 13 -> {
                        System.out.print("Enter user ID to view orders: ");
                        int uid = scanner.nextInt(); scanner.nextLine();
                        List<Courier> all = service.getDeliveryHistory();
                        CourierUtils.displayOrdersByUser(all, uid);
                    }

                    case 14 -> {
                        int[] distances = {3, 5, 2, 8};
                        CourierUtils.findNearestCourier(distances);
                    }

                    case 15 -> {
                        System.out.print("Enter weight (kg): ");
                        double weight = scanner.nextDouble(); scanner.nextLine();
                        CourierUtils.categorizeWeight(weight);
                    }

                    case 16 -> {
                        CourierUtils.login();
                    }

                    case 17 -> {
                        running = false;
                        System.out.println("Thank you for using Courier Management System.");
                    }

                    default -> System.out.println("Invalid option.");
                }
            } catch (TrackingNumberNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}