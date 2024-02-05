package structural.composite;

//Leaf node
public class BinaryFile extends File {
	
	//size of file
	private long size;
	
	public BinaryFile(String name, long size) {
		super(name);
		this.size = size;
	}

	@Override
	public void ls() {
		System.out.println(getName() + "\t" + size);
	}

	@Override
	public void addFile(File file) {
		throw new UnsupportedOperationException("leaf node doesnt support add operation");
	}

	@Override
	public File[] getFiles() {
		throw new UnsupportedOperationException("leaf node doesnt support get operation");

	}

	@Override
	public boolean removeFile(File file) {
		throw new UnsupportedOperationException("leaf node doesnt support remove operation");

	}
	
	
	
}
