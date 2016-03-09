import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 * Created by Nate on 3/8/2016.
 */
public class Directory {

    public void addEmployees(String response){
        StringBuilder sb = new StringBuilder(response);
        sb.delete(0,5);
        Gson gson = new Gson();
        ArrayList<Employee> employees = gson.fromJson(sb.toString(), new TypeToken<ArrayList<Employee>>(){}.getType());
    }
}
