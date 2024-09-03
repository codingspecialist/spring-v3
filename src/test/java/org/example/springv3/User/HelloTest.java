package org.example.springv3.User;

import org.example.springv3.Good;
import org.example.springv3.user.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

public class HelloTest {

    @Test
    public void go(){
        User user = null;
        Optional<User> op = Optional.ofNullable(user);

//        User userPS = op.get();
//        System.out.println(userPS.getId());

//        if(op.isPresent()){
//            User userPS = op.get();
//        }else{
//            throw new RuntimeException("존재하지 않아요");
//        }


//        User u = User.builder().id(1).username("ssar").build();
//        User u2 = op.orElse(u);
//        System.out.println(u2.getUsername());

        User u = op.orElseThrow(() -> new RuntimeException("fdsafdsafdsa"));

    }



}
