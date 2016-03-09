import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Client {
	
	public static void main(String[] args) {
		try {
			System.out.println("in the client");

			//Client will connect to this location
			URL site = new URL("http://localhost:8000/sendresults");
			HttpURLConnection conn = (HttpURLConnection) site.openConnection();

			// now create a POST request
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());

			// build a string that contains JSON from console
			String content = "";
			content += "data=" + getJSON();
			System.out.println("\n" + content);

			// write out string to output buffer for message
			out.writeBytes(content);
			out.flush();
			out.close();

			System.out.println("Done sent to server");

			InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

			// string to hold the result of reading in the response
			StringBuilder sb = new StringBuilder();

			// read the characters from the request byte by byte and build up the sharedResponse
			int nextChar = inputStr.read();
			while (nextChar > -1) {
				sb=sb.append((char)nextChar);
				nextChar=inputStr.read();
			}
			System.out.println("Return String: "+ sb);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getJSON() {
		Scanner s = new Scanner(System.in);
		String fname, lname, dept, pnumber;

		int i=1;

		// opening square brace of JSON collection
		String JSon = "[";
		boolean flag = false;
		while(i==1) {
			System.out.print("Enter first name: ");
			fname = s.next();
			System.out.print("Enter last name: ");
			lname = s.next();
			System.out.print("Enter Dept: ");
			dept = s.next();
			System.out.print("Enter Phone Number: ");
			pnumber = s.next();
			
			try {

				JSon += "{\"firstName\":\"" + fname + "\",";
				JSon += "\"lastName\":\"" + lname + "\",";
				JSon += "\"dept\":\"" + dept + "\",";
				JSon += "\"phoneNumber\":\"" + pnumber + "\"}";
				
			} catch(Exception E) {
				System.out.println("BIB incorrect, employee is not added.");
				flag = true;
			}
			System.out.print("Enter more employees? YES[1], NO[0] ");
			i = s.nextInt();
			if (i==1) {
				if (flag) {
					flag = false;
				}
				else {
					JSon += ",";
				}
			}
		}

		// add closing squre brace to end JSON collection
		JSon += "]";

		s.close();
		
		return JSon;
	}

}
