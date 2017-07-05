package ru.sk.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.paukov.combinatorics3.Generator;

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
		
		for (int i = 0; i < LIST_SIZE; i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
		
		Assert.assertEquals(arrayList.get(10), lazyArrayList.get(10));
		Assert.assertEquals(arrayList.get(11), lazyArrayList.get(11));
	}
	
	@Test
	public void testSetToList() {
		
		arrayList.remove(2);
		
		lazyArrayList.remove(2);
		
		arrayList.remove(3);
		
		lazyArrayList.remove(3);
		
		arrayList.remove(8);
		
		lazyArrayList.remove(8);
		
		arrayList.remove(9);
		
		lazyArrayList.remove(9);

		for (int i = 0; i < LIST_SIZE - 2; i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
	
	@Test
	public void testRemoveFromList() {
		
		arrayList.remove(2);
		
		lazyArrayList.remove(2);
		
		arrayList.remove(3);
		
		lazyArrayList.remove(3);
		
		arrayList.remove(5);
		
		lazyArrayList.remove(5);
		
		arrayList.remove(6);
		
		lazyArrayList.remove(6);

		for (int i = 0; i < LIST_SIZE - 4; i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
}