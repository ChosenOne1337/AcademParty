package ru.ftc.android.shifttemple.features.parties.domain;

public enum SortOrder {
    ASCENDING, DESCENDING;

    public String toString() {
        switch (this) {
            case ASCENDING:
                return "less";
            case DESCENDING:
                return "more";
            default:
                return name();
        }
    }
}
