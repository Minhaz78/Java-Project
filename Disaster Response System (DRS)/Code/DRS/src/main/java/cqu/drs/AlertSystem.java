package cqu.drs;

public class AlertSystem {
    public static void notifyDepartments(Disaster disaster) {
        System.out.println("ALERT: A new " + disaster.getType() + " has been reported at " + disaster.getLocation() + ". Notify all departments immediately!");
    }
}
