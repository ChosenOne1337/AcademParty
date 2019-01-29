package ru.ftc.android.shifttemple.features.parties.presentation;

import android.os.Bundle;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;

public class PartyInfoActivity extends BaseActivity {
    private PartiesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_information);

        initView();
    }

    private void initView() {

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
