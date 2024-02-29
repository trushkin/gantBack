package testdata;

import com.example.ganttback.resource.ResourceBuilder;

public class ResourceTemplateData {
    public static ResourceBuilder resource1() {
        return new ResourceBuilder()
                .setCapacity(25L)
                .setName("Vlad")
                .setSalary(10L);
    }
    public static ResourceBuilder resource2() {
        return new ResourceBuilder()
                .setCapacity(10L)
                .setName("John")
                .setSalary(45L);
    }


}
