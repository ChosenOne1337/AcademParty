package ru.ftc.android.shifttemple.features.parties.domain.model;

import java.util.List;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:40
 */

public final class Party {
    private String id;
    private String name;
    private String place;
    private String date;
    private String organizer;
    private String description;
    private int membersCount;
    private List<String> members;

    private boolean isAvailable;

    public Party(String name, String place, String date, String organizer, String description,
                 int membersCount, List<String> members) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.organizer = organizer;
        this.description = description;
        this.membersCount = membersCount;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getDescription() {
        return description;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public List<String> getMembers() {
        return members;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
