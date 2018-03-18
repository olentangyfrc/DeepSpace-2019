package org.usfirst.frc.team4611.robot.utilities;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayReader {
	static ArrayReader	instance	= null;
	
	public static ArrayReader getInstance() {
		if (instance == null)
				instance	= new ArrayReader();
		
		return instance;
	}
	
	public ArrayList<double []> getNumbers(String filename) throws IOException {

		ArrayList<double []>	numbers	= new ArrayList <double[]>();

		InputStream	is = null;
		InputStreamReader isr = null;
		
		BufferedReader br	= null;
		double numberSet[]	= new double[4];
		
		
		try {
			String	line;
			String[]	parts;
			is	= this.getClass().getClassLoader().getResourceAsStream(filename);

	        isr = new InputStreamReader(is);
	        br = new BufferedReader(isr);		
			
	        while ((line = br.readLine()) != null) {
	        	parts	= line.split(",");
	        	
	        	numberSet	= new double[4];
	        	numberSet[0]	= Double.parseDouble(parts[0].trim());
	        	numberSet[1]	= Double.parseDouble(parts[1].trim());
	        	numberSet[2]	= Double.parseDouble(parts[2].trim());
	        	numberSet[3]	= Double.parseDouble(parts[3].trim());
	        	
	        	numbers.add(numberSet);
	        }
			
			
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return numbers;
		
	}

	public static void main(String[] args) {
		ArrayList<double[]> numbers;
		String	f	= args[0];
		double[] d;
		try {
			ArrayReader.getInstance();
			numbers = ArrayReader.getInstance().getNumbers(f);
			
			Iterator<double[]>	it	= numbers.iterator();
			while (it.hasNext()) {
				d	= it.next();
				System.out.println("[" + d[0] + "][" + d[1] + "][" + d[2] + "][" + d[3] + "]");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
