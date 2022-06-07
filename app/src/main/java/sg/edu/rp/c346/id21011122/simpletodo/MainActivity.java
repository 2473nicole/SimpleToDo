package sg.edu.rp.c346.id21011122.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNewTodo;
    Button btnAdd;
    Button btnClearAll;
    Button btnDelete;
    ListView lvTodo;
    Spinner spnAddRemove;

    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNewTodo = findViewById(R.id.editTextTodo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClearAll = findViewById(R.id.buttonClearItem);
        btnDelete = findViewById(R.id.buttonDelete);
        lvTodo = findViewById(R.id.listViewTodo);
        spnAddRemove = findViewById(R.id.spinner);

        alTodo = new ArrayList<>();

        aaTodo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener((v) -> {
            String newTodo = etNewTodo.getText().toString();
            alTodo.add(newTodo);
            aaTodo.notifyDataSetChanged();
            etNewTodo.setText(null);
        });

        btnClearAll.setOnClickListener((v) -> {
            alTodo.clear();
            aaTodo.notifyDataSetChanged();
        });

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etNewTodo.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etNewTodo.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int pos = Integer.parseInt(etNewTodo.getText().toString());
                                alTodo.remove(pos);
                                aaTodo.notifyDataSetChanged();
                            }
                        });

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}