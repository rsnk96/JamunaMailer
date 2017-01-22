package in.aqel.jamunamailer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    EditText etRollNumber, etRoomNumber;
    AutoCompleteTextView etName;
    Context context;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInitialData();
        context = MainActivity.this;
        etRollNumber = (EditText) findViewById(R.id.etRollNumber);

        etName = (AutoCompleteTextView) findViewById(R.id.etName);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int s, int i1, int i2) {
                String text = charSequence.toString();
                if (text.length() >= 3){
                    List<Student> students = Student.loadAll(text);
                    String[] names = new String[students.size()];
                    Log.d("Name", " " + names.length);
                    for (int i=0; i<names.length; i++) names[i] = students.get(i).getName();
                    adapter = new ArrayAdapter<String>
                            (MainActivity.this,android.R.layout.simple_list_item_1, names);
                    etName.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etRoomNumber = (EditText) findViewById(R.id.etRoomNumber);

    }

    private void loadInitialData() {
        if (!Utils.checkBoolean(this, Utils.spIsConfig)){
            String jsonText = Utils.loadJSONFromAsset(this, "data.json");
            Log.d("Data", "Saving data");
            try {
                JSONArray jsonArray = new JSONArray(jsonText);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    Student student = new Student(json.getInt("roomNumber"), json.getString("mobileNumber"),
                            json.getString("name"), json.getString("rollNumber"));
                    student.save();

                }
                Utils.setBoolean(this,Utils.spIsConfig, true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void submit(View v){
        if (etRoomNumber.getText().toString().length() > 2){
            Student stud = Student.loadStudent( Integer.parseInt(etRoomNumber.getText().toString()) );
            if (stud == null) showError();
            else student = stud;
        } else if (etRollNumber.getText().toString().length() == 8){
            Student stud = Student.loadStudent(etRollNumber.getText().toString());
            if (stud == null) showError();
            else student = stud;
        } else if (etName.getText().toString().length() > 3){
            List<Student> students = Student.loadAll(etName.getText().toString());
            student = students.get(0);
        } else {
            Toast.makeText(MainActivity.this, "Please enter valid details", Toast.LENGTH_SHORT).show();
        }

        if (student != null ){
            Intent intent = new Intent(context, ConfirmActivity.class);
            intent.putExtra(ConfirmActivity.EXTRA_NAME, student.getName());
            intent.putExtra(ConfirmActivity.EXTRA_ROLL, student.getRollNumber());
            intent.putExtra(ConfirmActivity.EXTRA_ROOM, student.getRoomNumber());
            intent.putExtra(ConfirmActivity.EXTRA_MOBILE, student.getMobileNumber());
            startActivity(intent);
            Log.d("Student " , " " +  student.getRoomNumber());
        }


    }
    public void clear(View v){
        etName.setText("");
        etRollNumber.setText("");
        etRoomNumber.setText("");
    }

    public void showError(){
        Toast.makeText(MainActivity.this, "No records found", Toast.LENGTH_SHORT).show();
    }

}
