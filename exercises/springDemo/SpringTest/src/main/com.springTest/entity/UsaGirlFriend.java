package entity;

import lombok.Getter;
import lombok.Setter;

public class UsaGirlFriend implements GirlFriend{
    @Getter
    @Setter
    private String language;
    @Override
    public String speak() {
        return language;
    }
}
