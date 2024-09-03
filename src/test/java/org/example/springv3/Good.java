package org.example.springv3;

interface Callable {
    void hello();
}

public class Good {


    void go(Callable a) {
        a.hello();
    }

}
