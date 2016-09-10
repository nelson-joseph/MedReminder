/**
 * Created by Nelson Joseph on 26-May-2016.
 * Reg. No: 75814200014
 * Roll. No: 1406FOSS0011
 */

package com.medreminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.medreminder.database.MedicationLab;

import java.util.List;

public class MedicationListFragment extends Fragment {


    private RecyclerView mMedicationRecyclerView;
    private MedicationAdapter mAdapter;
    MedicationLab medicationLab = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medication_list, container, false);
        mMedicationRecyclerView = (RecyclerView) view.findViewById(R.id.medication_recycler_view);
        mMedicationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent addMedicationIntent = new Intent(getActivity(), AddMedication.class);
                addMedicationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(addMedicationIntent);
            }
        });

        if (savedInstanceState != null) {

        }

        updateUI();
        return view;
    }

    private void updateUI() {
        MedicationLab medicationLab = MedicationLab.get(getActivity());
        List<Medication> medicationList = medicationLab.getMedicationList();

        if (mAdapter == null) {
            mAdapter = new MedicationAdapter(medicationList);
            mMedicationRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setMedications(medicationList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MedicationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mMedicineNameTextView;
        private TextView mDosageTextView;
        private TextView muuid;
        //private CheckBox mSelectCheckBox;

        private Medication mMedication;

        public MedicationHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(v.getContext(), "Long Click",
                            Toast.LENGTH_SHORT).show();
                    muuid = (TextView) itemView.findViewById(R.id.list_item_uuid_text_view);
                    alertMessage(muuid);
                    return true;

                }
            });

            mMedicineNameTextView = (TextView) itemView.findViewById(R.id.list_item_medicine_name_text_view);
            mDosageTextView = (TextView) itemView.findViewById(R.id.list_item_medicine_dosage_text_view);
            muuid = (TextView) itemView.findViewById(R.id.list_item_uuid_text_view);
           // mSelectCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_medicine_select_check_box);
        }

        public void bindMedicine(Medication medication) {
            mMedication = medication;
            mMedicineNameTextView.setText(mMedication.getMedicineName());
            mDosageTextView.setText(mMedication.getDosage());
            muuid.setText(mMedication.getId().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getContext(), "Yet to implement", Toast.LENGTH_SHORT).show();
        }
    }

    private class MedicationAdapter extends RecyclerView.Adapter<MedicationHolder> {

        private List<Medication> mMedications;

        public MedicationAdapter(List<Medication> medications) {
            mMedications = medications;
        }

        @Override
        public MedicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_medication, parent, false);

            return new MedicationHolder(view);
        }

        @Override
        public void onBindViewHolder(MedicationHolder holder, int position) {
            Medication medication = mMedications.get(position);
            holder.bindMedicine(medication);
        }

        @Override
        public int getItemCount() {
            return mMedications.size();
        }

        public void setMedications(List<Medication> medications) {
            mMedications = medications;
        }
    }

    public void alertMessage(final TextView id) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
                        medicationLab = MedicationLab.get(getContext());
                        medicationLab.deleteMedication(id.getText().toString());
                        updateUI();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete this Medication?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}