package engine;

import java.util.ArrayList;

/**
 * Created by skaraltc on 6/19/2015.
 */
public class Products {
    private static ArrayList<Product> allProducts = new ArrayList<Product>();

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

public void addProduct (Product newProduct)
{
    allProducts.add(newProduct);
}

    public void initProducts ()
    {
        Product carrot = new Product("carrot", 0.1, 10, true);
        Product meat = new Product("meat", 0.2, 10, true);
        Product corn = new Product("corn", 0.3, 10, true);
        Product sauce = new Product("sauce", 0.4, 10, true);
        Product oil = new Product("oil", 0.05, 1, true);
        Product bread = new Product("bread", 0.6, 10, true);
        Product water = new Product("water", 30, 1000, true);
        Product potato = new Product("potato", 0.4, 10, true);
        Product green_tea = new Product("greenTea", 0.5, 10, true);
        Product black_tea = new Product("blackTea", 0.4, 10, true);
        this.addProduct(carrot);
        this.addProduct(meat);
        this.addProduct(corn);
        this.addProduct(sauce);
        this.addProduct(oil);
        this.addProduct(bread);
        this.addProduct(water);
        this.addProduct(potato);
        this.addProduct(green_tea);
        this.addProduct(black_tea);
    }

    @Override
    public String toString ()
    {
        String output = null;
        for (Product currentProduct: allProducts)
        {
            output+="["+currentProduct.toString() + "]";
        }
        return output;
    }

    public Product getProductByName (String productName)
    {
        int tempForProduct = 0;
        boolean tempForChanging = false;
        for (int i=0;i<allProducts.size();i++)
        {
            if (allProducts.get(i).getName().equals(productName))
            {
                tempForProduct=i;
                tempForChanging=true;
                break;
            }
        }
        if (tempForChanging)
        {
            return allProducts.get(tempForProduct);
        }
        else
        {
            return null;
        }
    }
}
