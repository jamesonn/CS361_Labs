import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nate on 3/8/2016.
 */
public class Directory {

    ArrayList<Employee> employees;

    public void addEmployees(String response){
        employees = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        employees = gson.fromJson(response, new TypeToken<Collection<Employee>>(){}.getType());
    }

    public String print() {
        for (int i = 0; i < employees.size(); i++) {
            return employees.get(i).firstname;
        }
        return "0";
    }
}
