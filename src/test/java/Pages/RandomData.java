package Pages;

import java.util.Random;

public class RandomData {

	
	
	public static String randomStringGenerator() {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		// System.out.println(generatedString);
		return generatedString;
	}

	public static String randomIntegerGenerator() {

		int leftLimit = 48; // letter 0
		int rightLimit = 57; // letter 10'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		// System.out.println(generatedString);
		return generatedString;
	}

	/*public static String getDateOfBirth(int startYear, int endYear) {
		GregorianCalendar gc = new GregorianCalendar();
		int year = randBetween(startYear, endYear);
		gc.set(Calendar.YEAR, year);
		int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		String month = Integer.toString(gc.get(Calendar.MONTH));
		String yearS = Integer.toString(gc.get(Calendar.YEAR));
		String day = "";
		Integer dayPre = gc.get(Calendar.DAY_OF_MONTH);

		if (dayPre > 28) {
			day = "28";
		} else {
			day = Integer.toString(dayPre);
		}

		return month.replace("0", "1") + "-" + day + "-" + yearS;
	}

	public static Integer getInteger(int minValue, int maxValue) {
		java.util.Random rand = new java.util.Random();

		return rand.nextInt((maxValue - minValue) + 1) + minValue;
	}

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}*/
}
