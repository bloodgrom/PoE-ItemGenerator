package com.poeitemgen.items.utils;
import java.util.HashSet;

public class LimitedModifierSet<E> extends HashSet<E> {

    private final int limit;

    public LimitedModifierSet(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E object) {
        if (this.size() > limit) return false;
        return super.add(object);
    }

}