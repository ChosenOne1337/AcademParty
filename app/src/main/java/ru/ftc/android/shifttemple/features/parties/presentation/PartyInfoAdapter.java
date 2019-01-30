package ru.ftc.android.shifttemple.features.parties.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

final class PartyInfoAdapter extends RecyclerView.Adapter<PartyInfoAdapter.PartyInfoHolder> {

    private final LayoutInflater inflater;
    private final List<Person> participant = new ArrayList<>();
    private final PartyInfoListener deleteMember;

    PartyInfoAdapter(Context context, PartyInfoListener selectPartyInfoListener) {
        inflater = LayoutInflater.from(context);
        this.deleteMember = selectPartyInfoListener;
    }

    @NonNull
    @Override
    public PartyInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.member_item, parent, false);
        return new PartyInfoHolder(itemView, deleteMember);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyInfoHolder holder, int position) {
        holder.bind(participant.get(position));
    }

    @Override
    public int getItemCount() {
        return participant.size();
    }

    public void setParticipant(List<Person> personList) {
        participant.clear();
        participant.addAll(personList);
        notifyDataSetChanged();
    }

    class PartyInfoHolder extends RecyclerView.ViewHolder {
        private final TextView memberNameView;

        private final ImageButton deleteMemberButton;

        private final PartyInfoListener partyInfoListener;

        PartyInfoHolder(View view, PartyInfoListener partyInfoListener) {
            super(view);

            this.partyInfoListener = partyInfoListener;

            memberNameView = view.findViewById(R.id.member_item_name);

            deleteMemberButton = view.findViewById(R.id.delete_member_button);
        }

        void bind(final Person participant) {
            memberNameView.setText(participant.getName());

            deleteMemberButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partyInfoListener.deleteMember(participant);
                }
            });
        }
    }

    interface PartyInfoListener {
        void deleteMember(Person participant);
    }
}