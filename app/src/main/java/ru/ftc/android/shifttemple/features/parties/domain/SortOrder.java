package ru.ftc.android.shifttemple.features.parties.domain;

public enum SortOrder {
    ASCENDING, DESCENDING;

    public String toString() {
        switch (this) {
            case ASCENDING:
                return "more";
            case DESCENDING:
                return "less";
            default:
                return name();
        }
    }
}
