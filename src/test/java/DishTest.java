import com.menu.Dish;
import com.menu.DishRepository;
import com.menu.ProductRepository;
import org.junit.Test;

import static org.junit.Assert.*;


public class DishTest {


    @Test
    public void testEquals() throws Exception {
        Dish dish=new Dish();
        Dish dish1=new Dish();
        boolean expected;
        boolean actual;

        ProductRepository pr = new ProductRepository();
        DishRepository dr = new DishRepository(pr);
        dish=dr.getDishByName("Borsh");
        dish1=dr.getDishByName("Borsh");
        expected=true;
        actual=dish.equals(dish1);
        assertEquals("Not equal",expected,actual);

    }
}