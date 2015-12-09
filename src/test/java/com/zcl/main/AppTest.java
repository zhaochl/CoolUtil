package com.zcl.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {

	


	 @Test
	public void testGenIDMutiThread() {
		System.out.println("-----testGenIDMutiThread()-----");
		long start = System.currentTimeMillis();
		List<Thread> threadList = new ArrayList<Thread>();
		final Set s = Collections.synchronizedSet(new HashSet<String>());
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 100 * 100; i++) {
						long end = System.currentTimeMillis();
//						String id = LogLib.getLogID();
//						s.add(id);
					}
				}

			});
			threadList.add(t);
		}
		for (int i = 0; i < threadList.size(); i++) {
			Thread tx = threadList.get(i);
			tx.start();
			try {
				tx.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000 + "s");
		assert(s.size()==100*10000);
		// getLocalServerHash();
	}

}
