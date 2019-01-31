package ru.ftc.android.shifttemple.features.parties.domain;

public enum SortParameter {
    BY_DATE, BY_PARTICIPANTS_NUMBER;

    public String toString() {
        switch (this) {
            case BY_DATE:
                return "date";
            case BY_PARTICIPANTS_NUMBER:
                return "currentpersons";
            default:
                return name();
        }
    }
}
