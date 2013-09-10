package lab3Source;

import java.util.AbstractList;
import java.util.ArrayList;

// YOUR CODE HERE
// extends? implements?
public class ABCFacade {
    private ModuleA a;
    private ModuleB b;
    private ModuleC c;

    public ABCFacade() {
	a = new ModuleA();
	b = new ModuleB();
	c = new ModuleC();
	new Thread(a).start();
    }

    // YOUR CODE HERE
    // overwrite some methods that you inherit/implement
}
