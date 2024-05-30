package org.bank;

import lombok.extern.log4j.Log4j2;
import org.bank.model.User;

/**
 * Hello world!
 */

@Log4j2
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        User user = new User.Builder().balance(234d).build();
        System.out.println(user);
        log.info("popa");
    }
}
