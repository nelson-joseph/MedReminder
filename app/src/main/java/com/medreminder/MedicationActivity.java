/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MedicationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MedicationListFragment();
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final ListView listView = (ListView) findViewById(R.id.listViewMedications);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked: "+id , Toast.LENGTH_LONG).show();
            }
        });

        MedicationLab medicationLab = MedicationLab.get(getApplicationContext());
        List<String> medicationList = new ArrayList<String>();

        int listSize = medicationLab.getMedicationList().size();
        Log.i("List Size: ", " "+listSize);
        if (listSize < 1) {
            Toast.makeText(this.getApplicationContext(), "No Medication Available!", Toast.LENGTH_LONG).show();
        }
        else {
            for (int i = 0; i < listSize; i++) {
                medicationList.add(medicationLab.getMedicationList().get(i).getMedicineName());
                i = +6;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, medicationList);
            listView.setAdapter(arrayAdapter);
        }*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addMedicationIntent = new Intent(MedicationActivity.this, AddMedication.class);
                addMedicationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(addMedicationIntent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }*/
}
