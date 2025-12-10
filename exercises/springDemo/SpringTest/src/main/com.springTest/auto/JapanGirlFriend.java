package auto;

import lombok.Getter;
import lombok.Setter;

public class JapanGirlFriend implements GirlFriend{
    @Getter
    @Setter
    private String language;

    @Override
    public String speak() {
        return language;
    }
}
