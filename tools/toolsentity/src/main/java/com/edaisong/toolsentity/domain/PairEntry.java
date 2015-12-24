package com.edaisong.toolsentity.domain;

import java.util.Map;

public final class PairEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public PairEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

	@Override
	public V setValue(V value) {
		// TODO Auto-generated method stub
		return null;
	}
}
