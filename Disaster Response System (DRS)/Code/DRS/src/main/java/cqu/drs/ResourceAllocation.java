package cqu.drs;

public class ResourceAllocation {
    public static void allocateResources(Disaster disaster, Department department) {
        if (disaster.getSeverity() >= 7) {
            System.out.println(department.getName() + ": Allocate maximum resources for disaster at " + disaster.getLocation());
        } else {
            System.out.println(department.getName() + ": Allocate limited resources for disaster at " + disaster.getLocation());
        }
    }
}
