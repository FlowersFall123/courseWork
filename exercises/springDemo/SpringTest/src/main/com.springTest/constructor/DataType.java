package constructor;

import entity.User;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Data
public class DataType {
    private String specialCharacter1;
    private String specialCharacter2;
    private User innerBean;
    private List<String> list;
    private String[] array;
    private Set<String> set;
    private Map<String,String>map;
    private Properties properties;
    private String emptyValue;
    private String nullValue = "init null";

    public void printValue(){
        System.out.println("specialCharacter1:"+specialCharacter1);
        System.out.println("specialCharacter2:"+specialCharacter2);
        System.out.println("innerBean:"+innerBean);
        System.out.println("list:"+list);
        System.out.println("array:"+array);
        System.out.println("set:"+set);
        System.out.println("map:"+map);
        System.out.println("properties:"+properties);
        System.out.println("emptyValue:"+emptyValue);
        System.out.println("nullValue:"+nullValue);
    }
}
