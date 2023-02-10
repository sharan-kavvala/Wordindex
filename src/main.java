import java.io.IOException;

public class main {

		    public static void main(String[] args) throws IOException {
		       WordIndex wordIndex = new WordIndex();
		       wordIndex.readFiles();
		       wordIndex.writeToFile();
		        
		    }
	
		
}
