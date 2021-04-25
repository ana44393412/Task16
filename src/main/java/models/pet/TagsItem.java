package models.pet;

import java.util.Objects;

public class TagsItem {
    private String name;
    private int id;

    public final String getName() {
        return name;
    }

    public final int getId() {
        return id;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    @SuppressWarnings("checkstyle:NeedBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagsItem tagsItem = (TagsItem) o;
        return id == tagsItem.id && Objects.equals(name, tagsItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}