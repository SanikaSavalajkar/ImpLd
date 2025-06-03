package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Courier;
import entity.CourierCompanyCollection;

public class CourierUserServiceCollectionImpl implements ICourierUserService {

    private CourierCompanyCollection companyObj;

    public CourierUserServiceCollectionImpl(CourierCompanyCollection companyObj) {
        this.companyObj = companyObj;
    }

    @Override
    public String placeOrder(Courier courierObj) {
        companyObj.getCourierDetails().add(courierObj);
        return courierObj.getTrackingNumber();
    }

    @Override
    public String getOrderStatus(String trackingNumber) {
        for (Courier c : companyObj.getCourierDetails()) {
            if (c.getTrackingNumber().equalsIgnoreCase(trackingNumber)) {
                return c.getStatus();
            }
        }
        return "Tracking number not found.";
    }

    @Override
    public boolean cancelOrder(String trackingNumber) {
        for (Courier c : companyObj.getCourierDetails()) {
            if (c.getTrackingNumber().equalsIgnoreCase(trackingNumber)) {
                if (!c.getStatus().equalsIgnoreCase("Delivered")) {
                    c.setStatus("Cancelled");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) {
        // Placeholder: requires mapping of courier to staff
        return new ArrayList<>();
    }
}