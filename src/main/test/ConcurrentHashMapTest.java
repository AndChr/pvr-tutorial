package main.test;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentHashMapTest {
	
	private ConcurrentHashMap<Integer, String> hashMap;
	
	@Before
	public void setup() {
		hashMap = new ConcurrentHashMap<>();
		hashMap.put(1, "PVR");
		hashMap.put(2, "FSE");
		hashMap.put(3, "Rewe");
		hashMap.put(4, "Orga");
		hashMap.put(5, "MDD");
		hashMap.put(6, "E-Services");
	}
	
	@Test
	public void testForEach() {
		System.out.println("TEST FOREACH");
		hashMap.forEach(2, (k, v) -> System.out.println("Index ->" + k + " steht für Fach -> " + v +", by thread-> "+ Thread.currentThread().getName()));
	}
	
	@Test
	public void testSearch() {
		System.out.println("TEST SEARCH");
		String result = hashMap.search(1, (k, v) -> {
			  System.out.println(Thread.currentThread().getName());
			  if (k == 6) {
			    return k +"-" +v;
		      } else {
				  return null;		    	  
		      }
			});
		System.out.println("result => " +result);
	}
	
	@Test
	public void testMerge() {
		System.out.println("TEST MERGE");
		ConcurrentHashMap<Integer, String> anotherMap = new ConcurrentHashMap<>();
		anotherMap.put(1, "Wirtschaftsrecht");
		System.out.println("1st ==> " +anotherMap);
		System.out.println("2nd ==> " + anotherMap.merge(1, "Wirtschaftsrecht", (v1, v2) -> null));
		System.out.println("3rd ==> " +anotherMap);
		anotherMap.put(2, "Mathe für Informatik");
		System.out.println("4rd ==> " + anotherMap);
		System.out.println("5th ==> " +anotherMap.merge(2, "Mathe für Informatik", (v1, v2) -> "Mathe für BWL"));
		System.out.println("6th ==> " +anotherMap);
		System.out.println("7th ==> " +anotherMap.merge(
		 2, "Mathe für BWL", (v1, v2) -> v2.concat(" 2")));
		System.out.println("8th ==> " +anotherMap);	
	}
	
	@Test
	public void testGetOrDefault() {
		System.out.println("TEST GETORDEFAULT");
		System.out.println(hashMap.getOrDefault(7, "Modul nicht gefunden!"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Ab jetzt folgen weitere Code-Beispiele, die in der Präsentation nicht mehr gezeigt wurden
	 */
	
	
	
	@Test
	public void testComputeMethods() {
		System.out.println("TEST COMPUTE_METHODS");
		ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap<>();
		map1.put("A", 1);
		map1.put("B", 2);
		map1.put("C", 3);
		// Compute a new value for the existing key
		System.out.println("1st print => " +map1.compute("A",
		  (k, v) -> v == null ? 42 : v + 40));
		System.out.println("2nd print => " + map1);
		// This will add a new (key, value) pair
		System.out.println("3rd print => " + map1.compute("X",
		  (k, v) -> v == null ? 42 : v + 41));
		System.out.println("4th print => " + map1);
		//computeIfPresent method
		System.out.println("5th print => " + map1.computeIfPresent("X", (k, v) -> v == null ? 42 : v + 10));
		System.out.println("6th print => " + map1);
		//computeIfAbsent method
		System.out.println("7th print => " + map1.computeIfAbsent("Y", (k) -> 90));
		System.out.println("8th print => " + map1);
	}
	
	@Test
	public void testReduce() {
		System.out.println("TEST REDUCE");
		ConcurrentHashMap<String, Integer> reducedMap = new ConcurrentHashMap<>();
		reducedMap.put("One", 1);
		reducedMap.put("Two", 2);
		reducedMap.put("Three", 3);
		System.out.println("reduce example => " 
		 +reducedMap.reduce(2, (k, v) -> v*2, (total, elem) -> total + elem)); 
		System.out.println("reduceKeys example => " 
		 +reducedMap.reduceKeys(2, (key1, key2) -> key1.length() > key2.length() ? key1 + "-"+key2 : key2 + "-"+key1)); 
		System.out.println("reduceValues example => " 
		 +reducedMap.reduceValues(2, (v) -> v*2 , (value1, value2) -> value1 > value2 ? value1 - value2 : value2 - value1));
		System.out.println("After reduce => " +reducedMap);
	}

}
