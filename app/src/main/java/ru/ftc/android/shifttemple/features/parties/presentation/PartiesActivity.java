package ru.ftc.android.shifttemple.features.parties.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.SortOrder;
import ru.ftc.android.shifttemple.features.parties.domain.SortParameter;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.presentation.PartyAdapter.PartyListener;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class PartiesActivity extends BaseActivity implements PartyListView {
    private SortParameter sortParameter = SortParameter.BY_DATE;
    private SortOrder sortOrder = SortOrder.ASCENDING;

    private ProgressBar progressBar;

    private Spinner spinner;

    private RecyclerView recyclerView;
    private PartyAdapter adapter;

    private Button createPartyButton;

    private PartiesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parties_activity);

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.parties_progress);
        recyclerView = findViewById(R.id.parties_recycle_view);
        createPartyButton = findViewById(R.id.add_button);

        createPartyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartiesActivity.this, CreatePartyActivity.class);
                startActivity(intent);
            }
        });

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<?> sortedPartiesAdapter =
                ArrayAdapter.createFromResource(this, R.array.sorting_parties, android.R.layout.simple_spinner_item);
        sortedPartiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(sortedPartiesAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                       long id) {
                // TODO Auto-generated method stub
                ((TextView) parent.getChildAt(0)).setTextSize(18);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                if (selectedItemPosition == 0) {
                    sortParameter = SortParameter.BY_DATE;
                    sortOrder = SortOrder.ASCENDING;
                } else if (selectedItemPosition == 1) {
                    sortParameter = SortParameter.BY_DATE;
                    sortOrder = SortOrder.DESCENDING;
                } else if (selectedItemPosition == 2) {
                    sortParameter = SortParameter.BY_PARTICIPANTS_NUMBER;
                    sortOrder = SortOrder.ASCENDING;
                } else if (selectedItemPosition == 3) {
                    sortParameter = SortParameter.BY_PARTICIPANTS_NUMBER;
                    sortOrder = SortOrder.DESCENDING;
                }
                presenter.loadParties(sortParameter, sortOrder);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        adapter = new PartyAdapter(this, new PartyListener() {
            @Override
            public void changeToInfoActivity(Party party) {
                Intent intent = new Intent(PartiesActivity.this, PartyInfoActivity.class);
                intent.putExtra("PartyId", party.getId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPartyList(List<Party> list) {
        adapter.setParties(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public SortParameter sortListBy() {
        return sortParameter;
    }

    @Override
    public SortOrder listSortOrder() {
        return sortOrder;
    }

    @Override
    protected MvpPresenter<PartyListView> getPresenter() {
        presenter = PresenterFactory.createPartiesListPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
