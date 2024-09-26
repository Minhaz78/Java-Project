package cqu.drs;

public class Assessment {
    public static String generateAssessment(Disaster disaster) {
        if (disaster.isCritical() || disaster.getSeverity() >= 7) {
            return "High priority response needed.";
        } else if (disaster.getSeverity() >= 4) {
            return "Moderate priority response needed.";
        } else {
            return "Low priority response.";
        }
    }
}
