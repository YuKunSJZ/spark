package fz.spark;

public class LearnSpark {
	public enum Language{
		HADDOP
		,HIVE
		,STORM
		,SPARK
	}
	
	public String getLangBaseCode(Language lang) {
		switch(lang) {
		case HADDOP:
			return "Java";
		case HIVE:
			return "Java";
		default:
			break;
		}
		return null;
	}
	
	public void LearnSycrozed() {
		/**
		修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁

		修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁

		修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
		 */
		//https://www.bilibili.com/video/av19953981/?p=30
		
		
	}

}
