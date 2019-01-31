package ru.ftc.android.shifttemple.features.parties.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;

public class PartyInfoActivity extends BaseActivity implements PartyInfoView {
    private PartyInfoPresenter presenter;

    private String partyId;

    private ImageView partyImageView;

    private TextView partyNameView;
    private TextView partyDateView;
    private TextView partyPlaceView;
    private TextView partyManagerView;
    private TextView partyDescriptionView;
    private TextView partyCurrentMembersView;
    private TextView partyMaxMembersView;

    private RecyclerView recyclerView;
    private PartyInfoAdapter adapter;

    private EditText editParticipantName;

    private LinearLayout addParticipantLayout;

    private ImageButton addParticipantButton;
    private ImageButton acceptParticipantButton;

    private Button editPartyButton;
    private Button deletePartyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_information);

        partyId = getIntent().getStringExtra("PartyId");

        initView();
    }

    private void initView() {
        partyImageView = findViewById(R.id.party_image);
        partyNameView = findViewById(R.id.party_real_name);
        partyPlaceView = findViewById(R.id.party_real_place);
        partyDateView = findViewById(R.id.party_real_time);
        partyManagerView = findViewById(R.id.party_real_manager);
        partyDescriptionView = findViewById(R.id.party_real_description);
        partyCurrentMembersView = findViewById(R.id.party_current_members);
        partyMaxMembersView = findViewById(R.id.party_max_members);

        recyclerView = findViewById(R.id.parties_members_list);

        adapter = new PartyInfoAdapter(this, new PartyInfoAdapter.PartyInfoListener() {
            @Override
            public void deleteMember(Person participant) {
                presenter.deletePerson(partyId, participant);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addParticipantLayout = findViewById(R.id.participant_edit_layout);

        editParticipantName = findViewById(R.id.write_member);

        addParticipantButton = findViewById(R.id.add_member);

        addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipantButton.setVisibility(View.INVISIBLE);
                addParticipantLayout.setVisibility(View.VISIBLE);
            }
        });

        acceptParticipantButton = findViewById(R.id.accept_member);
        acceptParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipantButton.setVisibility(View.VISIBLE);
                addParticipantLayout.setVisibility(View.INVISIBLE);

                Person person = new Person();
                person.setName(editParticipantName.getText().toString());

               // presenter.addPerson(partyId, person);

                editParticipantName.setText("");
                hideKeyboard(v);
            }
        });

        editPartyButton = findViewById(R.id.edit_party_button);
        editPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartyInfoActivity.this, EditPartyActivity.class);
                intent.putExtra("PartyId", partyId);
                startActivity(intent);
                finish();
            }
        });

        deletePartyButton = findViewById(R.id.delete_party_button);
        deletePartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteParty(partyId);

                Intent intent = new Intent(PartyInfoActivity.this, PartiesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected MvpPresenter<PartyInfoView> getPresenter() {
        presenter = PresenterFactory.createPartyInfoPresenter(this);
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
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, "Участник успешно удален", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPartyInfo(Party party) {
        if (!party.getPictureUrl().isEmpty()) {
            Picasso.get().load(party.getPictureUrl()).into(partyImageView);
        }

        Date partyDate = new Date(party.getDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");

        partyNameView.setText(party.getName());
        partyPlaceView.setText(party.getPlace());
        partyDateView.setText(simpleDateFormat.format(party.getDate()));
        partyManagerView.setText(party.getHost());
        partyDescriptionView.setText(party.getDescription());
        partyCurrentMembersView.setText(party.getCurrentPersons() + "");
        partyMaxMembersView.setText(party.getMaxPersons() + "");

        adapter.setParticipant(party.getParticipants());
    }

    public void hideKeyboard(View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
