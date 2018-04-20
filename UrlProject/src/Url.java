import java.io.IOException;
import java.net.URISyntaxException;

public class Url {

	final static MangeTheFile mtf = new MangeTheFile();
	private static String fp = System.getProperty("user.home") + "/Downloads/Subtitles/";
	private static String dd = System.getProperty("user.home") + "/Downloads/Subtitles/";;

	public static void main(String args[]) throws IOException, URISyntaxException {
//		final MangeTheFile mtf = new MangeTheFile();
		String dl = "";
		if (!args[0].equals(null)){
			System.out.println("im here"+args[0]);
			dl = mtf.GetTheDownloadibleLink("arabic", /* "the dark knight" */args[0], "brrip");
		}
		// mtf.downloadFile(dl, getFp(), getDd());
//		String s[] = mtf.getSubs();

	}

	String[] getSubs() {
		return MangeTheFile.getSubs();
	}
	// void setSubs(int size) {
	// return MangeTheFile.getSubs();
	// }

	public static String getFp() {
		return fp;
	}

	public static void setFp(String fp) {
		Url.fp = fp;
	}

	public static String getDd() {
		return dd;
	}

	public static void setDd(String dd) {
		Url.dd = dd;
	}
}
