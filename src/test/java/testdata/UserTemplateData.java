package testdata;

import com.example.ganttback.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTemplateData {
//    @Autowired
//    PasswordEncoder passwordEncoder;

    public static User user1() {
        return new User("tempmail", new BCryptPasswordEncoder().encode("temppassword"));
    }
}
