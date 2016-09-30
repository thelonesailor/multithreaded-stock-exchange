import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class checker
{
	public static void main ( String Args[])
	{
		BufferedReader br = null;
		stock r = new stock();
		int u=0;
		try {
			String actionString;
			br = new BufferedReader(new FileReader("input.txt"));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
				++u;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
if(u>0)
{test.st();}
	}
}
