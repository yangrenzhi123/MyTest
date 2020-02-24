import java.io.File;

public class Scan {

	public static void main(String[] args) {
		scan(args[0]);
	}

	public static void scan(String directory) {
		File f = new File(directory);
		File[] fs = f.listFiles();
		for (File ff : fs) {
			if (ff.isDirectory()) {
				scan(ff.getAbsolutePath());
			} else {
				System.out.println("scp " + ff.getAbsolutePath() + " root@172.16.16.228:/home/mysqldata2");
			}
		}
	}
}