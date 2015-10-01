package com.phonaylin.techconf.management.entity;

public enum TimeSlotType {
	TALK("Talk"),
    LUNCH("Lunch"),
	NETWORKING("Networking Event");
	
	private String value;

    private TimeSlotType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static TimeSlotType getEnum(final String value) {
        for (final TimeSlotType type : TimeSlotType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getValue(final TimeSlotType type) {
        return type.value;
    }
}
