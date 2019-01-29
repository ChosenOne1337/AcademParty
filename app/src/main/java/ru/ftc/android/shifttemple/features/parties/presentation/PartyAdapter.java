package ru.ftc.android.shifttemple.features.parties.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:24
 */

final class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.PartyHolder> {

    private final LayoutInflater inflater;
    private final List<Party> parties = new ArrayList<>();
    private final PartyListener selectPartyListener;

    PartyAdapter(Context context, PartyListener selectPartyListener) {
        inflater = LayoutInflater.from(context);
        this.selectPartyListener = selectPartyListener;
    }

    @NonNull
    @Override
    public PartyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.party_item, parent, false);
        return new PartyHolder(itemView, selectPartyListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyHolder holder, int position) {
        holder.bind(parties.get(position));
    }

    @Override
    public int getItemCount() {
        return parties.size();
    }

    public void setParties(List<Party> partyList) {
        parties.clear();
        parties.addAll(partyList);
        notifyDataSetChanged();
    }

    class PartyHolder extends RecyclerView.ViewHolder {
        private final TextView partyNameView;
        private final TextView partyDateView;
        private final TextView partyPlaceView;

        private final Button showPartyInformationButton;

        private final PartyListener partyListener;

        PartyHolder(View view, PartyListener partyListener) {
            super(view);

            this.partyListener = partyListener;

            partyNameView = view.findViewById(R.id.party_name_2);
            partyPlaceView = view.findViewById(R.id.party_place_2);
            partyDateView = view.findViewById(R.id.party_time_2);

            showPartyInformationButton = view.findViewById(R.id.view_button);
        }

        void bind(final Party party) {
            partyNameView.setText(party.getName());
            partyPlaceView.setText(party.getName());
            partyDateView.setText(party.getName());

            showPartyInformationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partyListener.onPartyButtonClick(party);
                }
            });
        }
    }

    interface PartyListener {
        void onPartyButtonClick(Party party);
    }
}
