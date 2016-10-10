package org.eclipse.dawnsci.analysis.dataset.slice;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.january.dataset.DatasetFactory;
import org.eclipse.january.dataset.DoubleDataset;
import org.eclipse.january.dataset.IndexIterator;
import org.junit.Test;

public class Java8Test {

	
	@Test
	public void test2Datasets() {
		double[] buffer = new double[4000*4000];
		DoubleDataset d = (DoubleDataset) DatasetFactory.createFromObject(buffer, new int[]{4000,4000});
		
		double[] buffer1 = new double[4000*4000];
		DoubleDataset d1 = (DoubleDataset) DatasetFactory.createFromObject(buffer, new int[]{4000,4000});
		
		d.iadd(1);
		d1.iadd(2);
		
		double[] currentVal = new double[2];
		
	}
	
	
	@Test
	public void test() {
		
		double[] buffer = new double[4000*4000];
		
		DoubleDataset d = (DoubleDataset) DatasetFactory.createFromObject(buffer, new int[]{4000,4000});
		
		for (int i = 0; i < buffer.length;i++) buffer[i] = buffer[i]+1;
		for (int i = 0; i < buffer.length;i++) buffer[i] = buffer[i]+1;
		for (int i = 0; i < buffer.length;i++) buffer[i] = buffer[i]+1;
		IntStream.range(0, 4000*4000).forEach(i-> d.setAbs(i, d.getAbs(i)+1));
		IntStream.range(0, 4000*4000).forEach(i-> d.setAbs(i, d.getAbs(i)+1));
		IntStream.range(0, 4000*4000).forEach(i-> d.setAbs(i, d.getAbs(i)+1));
		IntStream.range(0, 4000*4000).parallel().forEach(i-> d.setAbs(i, d.getAbs(i)+1));
		IntStream.range(0, 4000*4000).parallel().forEach(i-> d.setAbs(i, d.getAbs(i)+1));
		IntStream.range(0, 4000*4000).parallel().forEach(i-> d.setAbs(i, d.getAbs(i)+1));
		IndexIterator it = d.getIterator();
		while (it.hasNext()) {
			d.setAbs(it.index, d.getAbs(it.index)+1);
		}

		 it = d.getIterator();
		while (it.hasNext()) {
			d.setAbs(it.index, d.getAbs(it.index)+1);
		}

		 it = d.getIterator();
		while (it.hasNext()) {
			d.setAbs(it.index, d.getAbs(it.index)+1);
		}
		
		d.iadd(1);
		d.iadd(1);
		d.iadd(1);
		
		
		
		long t = System.currentTimeMillis();
		IntStream.range(0, 4000*4000).forEach(i-> buffer[i]=buffer[i]+1);
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" Stream");
		t = System.currentTimeMillis();
		IntStream.range(0, 4000*4000).parallel().forEach(i-> buffer[i]=buffer[i]+1);
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" ParStream");
		t = System.currentTimeMillis();
		for (int i = 0; i < buffer.length;i++) buffer[i] = buffer[i]+1+1;
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" primative");
		
		t = System.currentTimeMillis();
		it = d.getIterator();
		while (it.hasNext()) {
			d.setAbs(it.index, d.getAbs(it.index)+1+1);
		}
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" index iterator");
		
		t = System.currentTimeMillis();
		d.iadd(1.0).iadd(1);
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" iadd");
		
		
		System.out.println(d.get(0));
		System.out.println("SINE");
		t = System.currentTimeMillis();
		IntStream.range(0, 4000*4000).forEach(i-> d.setAbs(i, Math.sin(d.getAbs(i))));
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" Stream");
		t = System.currentTimeMillis();
		IntStream.range(0, 4000*4000).parallel().forEach(i-> d.setAbs(i, Math.sin(d.getAbs(i))));
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" Par Stream");
		t = System.currentTimeMillis();
		for (int i = 0; i < buffer.length;i++) buffer[i] = Math.sin(buffer[i]);
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" primative");
		t = System.currentTimeMillis();
		it = d.getIterator();
		while (it.hasNext()) {
			d.setAbs(it.index, Math.sin(d.getAbs(it.index)));
		}
		System.out.print(System.currentTimeMillis()-t);
		System.out.println(" index iterator");


	}

}
