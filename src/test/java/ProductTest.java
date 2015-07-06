import com.menu.DishRepository;
import com.menu.Product;
import com.menu.ProductRepository;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProductTest {

    @Test
    public void testGetByName() throws Exception {
        Product prod=new Product();
        Product expected=new Product();
        Product actual=new Product();
        ProductRepository pr = new ProductRepository();
        DishRepository dr = new DishRepository();
        expected=pr.getProductByName("Borsh");
        actual=prod.getByName("Borsh",pr.productList);
        assertEquals("Error",expected,actual);
    }

    @Test
    public void testEquals() throws Exception {
        Product prod=new Product();
        Product prod1=new Product();
        boolean expected;
        boolean actual;
        ProductRepository pr=new ProductRepository();
        prod=pr.getProductByName("Potato");
        prod1=pr.getProductByName("Potato");
        expected=true;
        actual=prod.equals(prod1);
        assertEquals("Not equal",expected,actual);
    }
}