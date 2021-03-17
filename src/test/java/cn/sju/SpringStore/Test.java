package cn.sju.SpringStore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;



public class Test {
	public static void main(String[] args) throws JSONException {
		
		Date now =new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateNowStr = sdf.format(now);
		System.out.println(dateNowStr);
		Random random = new Random();
		String ran = String.format("%04d", random.nextInt(9999));

		System.out.println(ran);
	}
}
