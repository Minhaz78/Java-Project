package cqu.drs;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Resource> resources;

    public Department(String name) {
        this.name = name;
        this.resources = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }
}

