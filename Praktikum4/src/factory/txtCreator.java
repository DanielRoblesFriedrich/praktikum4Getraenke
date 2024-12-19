package factory;

public class txtCreator extends Creator {
    
	public txtCreator() {
	}
	
	@Override
    public Product factoryMethod() {
        return new txtProduct();
    }
}
