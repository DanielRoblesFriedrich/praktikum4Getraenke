package factory;

public class csvCreator extends Creator {
	
	public csvCreator() {
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public Product factoryMethod() {
        return new csvProduct();
    }
}
