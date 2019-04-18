public class Testtt {

	public static void main(String[] args) {
		String s = "556454995554";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length()/2;i++) {
			String s1 = s.substring(i*2, (i+1)*2);
			if(s1.equals("55")) {
				s1 = "5401";
			}
			if(s1.equals("54")) {
				s1 = "5402";
			}
			sb.append(s1);
		}
		System.out.println(sb.toString());
		
		
		//System.out.println(turnHex(intToHex(0)));
	}

	public static String intToHex(int i) {
		String s = Integer.toHexString(i).toUpperCase();
		if (s.length() == 1) {
			s = "000" + s;
		}
		if (s.length() == 3) {
			s = "0" + s;
		}
		return s;
	}

	public static String turnHex(String hex) {
		String s2 = hex.substring(0, 2);
		String s1 = hex.substring(2, 4);
		return s1 + s2;
	}
}