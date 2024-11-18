package dev.ococa.api.domain.model.mood;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonValue;

public class Note {
    private final String value;

    public Note(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note moodNote = (Note) o;
        return Objects.equals(value, moodNote.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
