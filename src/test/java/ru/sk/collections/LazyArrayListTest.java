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
		
		for (int i = 0; i < arrayList.size(); i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
	
	@Test
	public void testSetToList() {
		
		arrayList.set(1, 1);
		
		lazyArrayList.set(1, 1);
		
		arrayList.set(8, 1);
		
		lazyArrayList.set(8, 1);
		
		for (int i = 0; i < arrayList.size(); i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
	
	@Test
	public void testInsertToList() {
		
		arrayList.add(1, 1);
		
		lazyArrayList.add(1, 1);
		
		arrayList.add(8, 1);
		
		lazyArrayList.add(8, 1);
		
		for (int i = 0; i < arrayList.size(); i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
	
	@Test
	public void testRemoveFromList() {
		
		arrayList.remove(2);
		
		lazyArrayList.remove(2);
		
		arrayList.remove(2);
		
		lazyArrayList.remove(2);
		
		/*arrayList.remove(3);
		
		lazyArrayList.remove(3);
		
		arrayList.remove(5);
		
		lazyArrayList.remove(5);
		
		arrayList.remove(6);
		
		lazyArrayList.remove(6);*/

		for (int i = 0; i < arrayList.size(); i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
	
	@Test
	public void testMultipleOperationsOnList() {
		
		arrayList.remove(2);
		arrayList.add(3, 1);
		arrayList.add(7, 1);
		arrayList.add(4, 1);
		arrayList.remove(0);
		arrayList.remove(4);
		
		lazyArrayList.remove(2);
		lazyArrayList.add(3, 1);
		lazyArrayList.add(7, 1);
		lazyArrayList.add(4, 1);
		lazyArrayList.remove(0);
		lazyArrayList.remove(4);
		
		for (int i = 0; i < arrayList.size(); i++) {
			Assert.assertEquals(arrayList.get(i), lazyArrayList.get(i));
		}
	}
}