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

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public class CreatePartyActivity extends BaseActivity implements PartyCreationView  {
    static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat("HH:mm dd.MM.yy");
    private PartyCreationPresenter presenter;

    EditText nameEditor;
    EditText placeEditor;
    TextView timeEditor;
    EditText managerEditor;
    EditText maxParticipantsNumberEditor;
    EditText descriptionEditor;

    private Button createPartyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_creation);

        initView();
    }

    private void initView() {
        nameEditor = findViewById(R.id.party_name_editor);
        placeEditor = findViewById(R.id.party_place_editor);

        timeEditor = findViewById(R.id.party_time_editor);
        timeEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                 new DatePickerDialog(CreatePartyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, final int year, final int month, final int day) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(CreatePartyActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
        managerEditor = findViewById(R.id.party_manager_editor);
        maxParticipantsNumberEditor = findViewById(R.id.party_max_participants_editor);
        descriptionEditor = findViewById(R.id.party_description_editor);

        createPartyButton = findViewById(R.id.create_create_button);
        createPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Party party = new Party();

                party.setName(nameEditor.getText().toString());
                party.setPlace(placeEditor.getText().toString());
                try {
                    party.setDate(DATA_FORMAT.parse(timeEditor.getText().toString()).getTime());
                } catch (Exception e) {
                        e.printStackTrace();
                }
                party.setHost(managerEditor.getText().toString());
                if (!maxParticipantsNumberEditor.getText().toString().isEmpty()) {
                    party.setMaxPersons(Integer.valueOf(maxParticipantsNumberEditor.getText().toString()));
                }
                party.setDescription(descriptionEditor.getText().toString());

                presenter.onPartyCreated(party);
            }
        });
    }

    @Override
    protected MvpPresenter<PartyCreationView> getPresenter() {
        presenter = PresenterFactory.createPartyCreationPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showCreatedParty(Party party) {
        Intent intent = new Intent(CreatePartyActivity.this, PartyInfoActivity.class);
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
        Toast.makeText(this, "Мероприятие успешно создано", Toast.LENGTH_LONG).show();
    }
}
