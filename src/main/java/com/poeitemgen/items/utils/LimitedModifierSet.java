package com.poeitemgen.items.utils;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class LimitedModifierSet<E> extends LinkedHashSet<E> {

    private final int limit;

    public LimitedModifierSet(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E object) {
        if (this.size() + 1 > limit) return false;
        return super.add(object);
    }

}