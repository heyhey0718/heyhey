package com.example.demo;

import java.util.Random;

import org.springframework.stereotype.Service;

//DIコンテナに登録
@Service
public class Congratulations 
{
	public String congratulations(String[] args)
	{
	String[] congs = {"おめでとう！", "お疲れさま！", "その調子"};
	Random r = new Random();
	String cong = congs[r.nextInt(3)];
	return cong;
	}

}
