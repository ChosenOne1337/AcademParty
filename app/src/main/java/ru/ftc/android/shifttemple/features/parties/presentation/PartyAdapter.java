package ru.ftc.android.shifttemple.features.parties.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private final PartyListener changeToInfoActivity;

    PartyAdapter(Context context, PartyListener selectPartyListener) {
        inflater = LayoutInflater.from(context);
        this.changeToInfoActivity = selectPartyListener;
    }

    @NonNull
    @Override
    public PartyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.party_item, parent, false);
        return new PartyHolder(itemView, changeToInfoActivity);
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
        private final ImageView partyImageView;

        private final Button showPartyInformationButton;

        private final PartyListener partyListener;

        PartyHolder(View view, PartyListener partyListener) {
            super(view);

            this.partyListener = partyListener;

            partyNameView = view.findViewById(R.id.party_real_name_2);
            partyPlaceView = view.findViewById(R.id.party_real_place_2);
            partyDateView = view.findViewById(R.id.party_real_time_2);

            partyImageView = view.findViewById(R.id.party_image_2);

            partyNameView.setSelected(true);
            partyPlaceView.setSelected(true);

            showPartyInformationButton = view.findViewById(R.id.view_button);
        }

        void bind(final Party party) {
            Date partyDate = new Date(party.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");

            partyNameView.setText(party.getName());
            partyPlaceView.setText(party.getPlace());
            partyDateView.setText(simpleDateFormat.format(partyDate));


            if(!party.getPictureUrl().isEmpty()) {
                Picasso.get().load(party.getPictureUrl()).resize(120, 120).into(partyImageView);
            }


            showPartyInformationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partyListener.changeToInfoActivity(party);
                }
            });
        }
    }

    interface PartyListener {
        void changeToInfoActivity(Party party);
    }
}
