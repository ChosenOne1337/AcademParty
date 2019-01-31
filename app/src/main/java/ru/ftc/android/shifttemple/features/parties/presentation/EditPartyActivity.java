package ru.ftc.android.shifttemple.features.parties.presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public class EditPartyActivity extends BaseActivity implements EditPartyView {
    static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("HH:mm dd.MM.yy");
    private EditPartyPresenter presenter;

    private String partyId;

    private EditText urlEditor;
    private EditText nameEditor;
    private EditText placeEditor;
    private TextView timeEditor;
    private EditText managerEditor;
    private EditText maxParticipantsNumberEditor;
    private EditText descriptionEditor;

    private Button editPartyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_party_activity);

        partyId = getIntent().getStringExtra("PartyId");

        initView();
    }

    private void initView() {
        urlEditor = findViewById(R.id.party_edit_url_editor);
        nameEditor = findViewById(R.id.party_edit_name_editor);
        placeEditor = findViewById(R.id.party_edit_place_editor);

        timeEditor = findViewById(R.id.party_edit_time_editor);
        timeEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                new DatePickerDialog(EditPartyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, final int year, final int month, final int day) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(EditPartyActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int h, int m) {
                                calendar.set(year, month, day, h, m);
                                timeEditor.setText(DATA_FORMAT.format(calendar.getTime()));
                            }
                        }, 21,0, true);
                        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                calendar.set(year, month, day, 21, 0);
                                timeEditor.setText(DATA_FORMAT.format(calendar.getTime()));
                            }
                        });
                        timePickerDialog.show();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        managerEditor = findViewById(R.id.party_edit_manager_editor);
        maxParticipantsNumberEditor = findViewById(R.id.party_edit_max_participants_editor);
        descriptionEditor = findViewById(R.id.party_edit_description_editor);

        editPartyButton = findViewById(R.id.create_create_button);
        editPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Party party = new Party();
                party.setId(partyId);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");

                party.setPictureUrl(urlEditor.getText().toString());
                party.setName(nameEditor.getText().toString());
                party.setPlace(placeEditor.getText().toString());
                try {
                    party.setDate(simpleDateFormat.parse(timeEditor.getText().toString()).getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                party.setHost(managerEditor.getText().toString());
                party.setMaxPersons(Integer.valueOf(maxParticipantsNumberEditor.getText().toString()));
                party.setDescription(descriptionEditor.getText().toString());

                presenter.onPartyChanged(party);
            }
        });
    }

    @Override
    protected MvpPresenter<EditPartyView> getPresenter() {
        presenter = PresenterFactory.createEditPartyPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public String getPartyId() {
        return partyId;
    }

    @Override
    public void showPartyFields(Party party) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");
        urlEditor.setText(party.getPictureUrl());
        nameEditor.setText(party.getName());
        placeEditor.setText(party.getPlace());
        timeEditor.setText(simpleDateFormat.format(party.getDate()));
        managerEditor.setText(party.getHost());
        maxParticipantsNumberEditor.setText(party.getMaxPersons().toString());
        descriptionEditor.setText(party.getDescription());
    }

    @Override
    public void showPartyInfo(Party party) {
        Intent intent = new Intent(EditPartyActivity.this, PartyInfoActivity.class);
        intent.putExtra("PartyId", party.getId());
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, "Мероприятие успешно изменено", Toast.LENGTH_LONG).show();
    }
}
