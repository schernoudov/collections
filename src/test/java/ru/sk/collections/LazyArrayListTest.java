package ru.sk.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javafx.util.Callback;

public class LazyArrayListTest {

	private static final int LIST_SIZE = 10;
	
	private LazyArrayList<Integer> lazyArrayList;
	private List<Integer> arrayList;

	@Before
	public void initialize() {
		
		arrayList = new ArrayList<>();
		
		for (int i = 0; i < LIST_SIZE; i++) {
			arrayList.add(i);
		}
		
		Callback<Integer, ? extends Integer> factory = param -> param;
		
		Comparator<Integer> comparator = (o1, o2) -> {
			
			if (o1 > o2) {
				return 1;
			} else if (o1 < o2) {
				return -1;
			}
			
			return 0;
		};
		
		lazyArrayList = new LazyArrayList<>(LIST_SIZE, factory, comparator);
	}
	
	@Test
	public void testAddToList() {
		
		arrayList.add(2);
		arrayList.add(4);
		
		lazyArrayList.add(2);
		lazyArrayList.add(4);
		
		Assert.assertEquals(arrayList.get(10), lazyArrayList.get(10));
		Assert.assertEquals(arrayList.get(11), lazyArrayList.get(11));
	}
	
	@Test
	public void testRemoveFromList() {
		
		arrayList.remove(2);
		arrayList.remove(4);
		
		lazyArrayList.remove(2);
		lazyArrayList.remove(4);
		
		Assert.assertEquals(arrayList.get(3), lazyArrayList.get(3));
		Assert.assertEquals(arrayList.get(5), lazyArrayList.get(5));
	}
}