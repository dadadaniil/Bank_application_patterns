package org.bank;

import lombok.extern.flogger.Flogger;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bank.model.User;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */

@Log4j2
public class App 
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        User user=  new User.Builder().balance(234d).build();
        System.out.println(user);
        log.info("popa");
    }
}
