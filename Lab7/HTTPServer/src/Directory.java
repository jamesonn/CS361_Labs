import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Nate on 3/8/2016.
 */
public class Directory{
    private ArrayList<Employee> employees;

    public Directory(){
        employees = new ArrayList<Employee>();
    }

    public void addEmployees(String response){
        StringBuilder sb = new StringBuilder(response);
        sb.delete(0,5);
        Gson gson = new Gson();
        employees = gson.fromJson(sb.toString(), new TypeToken<ArrayList<Employee>>(){}.getType());
    }

    public class EmployeeCompare implements Comparator<Employee>{

        public int compare(Employee e1, Employee e2) {
            String lastName1 = e1.getLastName().toUpperCase();
            String lastName2 = e2.getLastName().toUpperCase();

            //ascending order
            return lastName1.compareTo(lastName2);
        }};

    public void sortByLastName(){
        Collections.sort(employees, new EmployeeCompare());
    }
}
