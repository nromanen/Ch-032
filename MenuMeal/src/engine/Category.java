package engine;

/**
 *
 * @author pluchtc
 */
public class Category {
    private String name;

    @Override
    public boolean equals (Object category)
    {
       if (!(category instanceof Category))
       {
    	   return false;
       }
    	
       Category cat = (Category)category;
    	if (this.name.equals(cat.getName()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Category ()
    {
        name="";
    }
    
    public Category (String nameOfCategory)
    {
        this.name=nameOfCategory;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString ()
    {
        return name;
    }
}
