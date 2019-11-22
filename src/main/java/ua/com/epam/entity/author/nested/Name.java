package ua.com.epam.entity.author.nested;

import java.util.Objects;

public class Name {
    private String first;
    private String second;

    public Name() {
    }

    public Name(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(first, name.first) &&
                Objects.equals(second, name.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
