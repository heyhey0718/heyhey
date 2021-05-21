package com.example.demo;

import java.util.Random;

public class Congratulations 
{
	public void congratulations()
	{
	String[] congs = {"おめでとう！", "お疲れさま！", "その調子"};
	Random r = new Random();
	String cong = congs[r.nextInt(3)];
	System.out.println(cong);
	}
}
