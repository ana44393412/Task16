package models.pet;

import java.util.Objects;

public class Category {
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
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Category category = (Category) o;
            result = id == category.id && Objects.equals(name, category.name);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
