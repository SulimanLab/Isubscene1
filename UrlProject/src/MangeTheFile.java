import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

public class MangeTheFile {
	public static String subs[];
	public static int subsSize = 0;
	public static String allFormat;
	public static ObservableValue<? extends Number> ti;
	private String name = "";
	private static String subsLinks[] = new String[1000];
	private org.jsoup.nodes.Document d = null;
	private String downloadibleLink = "";
	private String LinkToConnect;
	private org.jsoup.select.Elements links;
	private static String glopalDis;

	public MangeTheFile() {
		subs = new String[1000];
	}

	public static String[] getSubsLinks() {
		return subsLinks;
	}

	public static void setSubsLinks() {
		MangeTheFile.subsLinks = new String[1000];
	}

	static String[] getSubs() {
		return subs;
	}

	static void setSubs(int size) {
		subs = new String[1000];
	}

	public static String getGlopalDis() {
		return glopalDis;
	}

	public static void setGlopalDis(String glopalDis) {
		MangeTheFile.glopalDis = glopalDis;
	}

	private int containsByChar(String[] s, String link) {
		int c = 0;
		int truness = 0;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < link.length(); j++) {
				c = s[i].length();
				while (c < s[i].length())
					if (s[i].charAt(c) == link.charAt(j)) {
						c++;

					} else if (c > 0) {
						c = 0;
						break;
					}
			}
			truness++;
		}

		return truness;
	}

//	private static boolean containsByString(String[] s, String link) {
//		String xx = new String(link);
//		String[] x = xx.split(".");
//		String p = x[x.length - 1];
//
//		for (int i = 0; i < s.length; i++) {
//
//			if (p.equalsIgnoreCase(s[i])) {
//				return true;
//			}
//		}
//		return false;
//	}

	private boolean containsIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null)
			return false;

		final int length = searchStr.length();
		if (length == 0)
			return true;

		for (int i = str.length() - length; i >= 0; i--) {
			if (str.regionMatches(true, i, searchStr, 0, length))
				return true;
		}
		return false;
	}

	public String GetTheDownloadibleLink(String language, String NameOfTheMovie, String extension) throws IOException {

		LinkToConnect = "https://www.google.com.sa/search?safe=strict&ei=nDFiWpj_HoLjU_Dyr4gK&q=subscene "
				+ NameOfTheMovie
				+ "&oq=&gs_l=psy-ab.3..0i13k1j0i13i30k1l9.3534.7071.0.7624.11.10.0.0.0.0.309.1682.0j2j4j1.7.0....0...1c.1.64.psy-ab..6.5.1226...0i10i67k1j0i10k1j0i10i203k1j0i7i10i30k1j0i8i7i10i30k1j0i7i10i30i19k1j0i19k1j0i8i7i10i30i19k1j0i13i30i19k1.0.xLIgn4fZyQw";

		d = Jsoup.connect(LinkToConnect).get();

		links = d.select("#rso > div > div > div:nth-child(1) > div > div > h3 > a[href]");

		for (org.jsoup.nodes.Element line : links) {
			
			System.err.println(line.absUrl("href") + " sd");
			LinkToConnect = line.absUrl("href");
			break;

		}

		String as[] = LinkToConnect.split("/");

		int i = as.length - 1;

		while (i > 4) {
			LinkToConnect = LinkToConnect.replace("/" + as[i--], "");

		}

		d = Jsoup.connect(LinkToConnect).get();

		LinkToConnect = null;
		links = d.select("table td.a1 a");

		String[] spilitNameOfTheMovie = NameOfTheMovie.split(" ");
		int movieQuality = 0;
		String qualityType = "";
		String allType[] = { "DVDRip", "HDTV", "BDRip", "WEBRip", "WEBDL", "DVDSCR", "brrip", "bluray" };
		for (int z = 0; z < allType.length; z++) {
			System.out.println(NameOfTheMovie + " " + allType[z]);
			if (NameOfTheMovie.toLowerCase().contains(allType[z].toLowerCase())) {

				qualityType = allType[z];
				System.out.println("im in");
			}
		}
		for (org.jsoup.nodes.Element line : links) {

			String nameOfTheMovieInSubscene = line.select("a").text();

			if (containsIgnoreCase(nameOfTheMovieInSubscene, language) && containsByChar(spilitNameOfTheMovie,
					nameOfTheMovieInSubscene) > (spilitNameOfTheMovie.length / 2)) {

				// if (containsIgnoreCase(title, extension)) {

				// ""(line.absUrl("href") + "\n" + title);
				System.err.println(line.absUrl("href"));
				subsLinks[subsSize] = line.absUrl("href");
				subs[subsSize++] = nameOfTheMovieInSubscene;
				if (subs[subsSize - 1].toLowerCase().contains(qualityType.toLowerCase())) {
					System.err.println("165 true");
					String temp = subs[movieQuality];
					subs[movieQuality] = subs[subsSize - 1];
					subs[subsSize - 1] = temp;
					String temp2 = subsLinks[movieQuality];
					subsLinks[movieQuality++] = subsLinks[subsSize - 1];
					subsLinks[subsSize - 1] = temp2;
				}
				// }
			}
		}
		// ""(subs[0]);

		if (subsSize == 0) {

			for (org.jsoup.nodes.Element line : links) {

				final String title = line.select("a").text();
				// ""(title);
				String[] s = NameOfTheMovie.split(" ");
				// ""(contains(s, title) + " >> " + (s.length /
				// 2));
				if (containsIgnoreCase(title, language) && containsByChar(s, title) > (s.length / 2)) {
					// ""(line.absUrl("href") + "\n" + title);
					subs[subsSize++] = line.absUrl("href");
				}
			}
		}
		// ""(subsSize + " <<<<<<<<<<<<<<");
		System.out.println(subsLinks[0]);
		LinkToConnect = subsLinks[0];

		d = Jsoup.connect(LinkToConnect).get();
		name = LinkToConnect;

		links = d.select("div.download a");

		downloadibleLink = links.get(0).absUrl("href");

		return downloadibleLink;
	}

	public void changeDowloadLink(int link, String zipFilePath, String destinationDirectory) throws IOException {

		LinkToConnect = subsLinks[link];
		// ""("------------");
		// ""(subsLinks[2]);
		name = LinkToConnect;

		d = Jsoup.connect(LinkToConnect).get();

		links = d.select("div.download a");

		downloadibleLink = links.get(0).absUrl("href");

		// ""(downloadibleLink+"\n"+zipFilePath+"\n"+destinationDirectory);
		// ""("------------");
		downloadFile(downloadibleLink, zipFilePath, destinationDirectory);

	}

	public void downloadFile(String url, String zipFilePath, String destinationDirectory) throws IOException {

		org.jsoup.Connection.Response r = Jsoup.connect(url).ignoreContentType(true).maxBodySize(10 * 1000 * 1000)
				.execute();
		// out.println(r.statusCode());

		InputStream in = new BufferedInputStream(r.bodyStream());
		// out.println(in.available()+ " in");
		ReadableByteChannel rbc = Channels.newChannel(in);

		name = name.replace("https://subscene.com/subtitles/", "");
		name = name.replace("/", "-");

		File f = new File(zipFilePath);
		if (!f.exists()) {
			f.mkdirs();
		}

		zipFilePath = zipFilePath + name + ".zip";

		FileOutputStream fos = new FileOutputStream(zipFilePath);

		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		in.close();
		fos.close();

		// ""(destinationDirectory);

		unzip(zipFilePath, destinationDirectory);
	}

	public void unzip(String zipFilePath, String destDir) throws IOException {

		InputStream in2 = new BufferedInputStream(new FileInputStream(zipFilePath));
		ZipInputStream zis = new ZipInputStream(in2);
		ZipEntry ze = zis.getNextEntry();

		ReadableByteChannel rbc2 = Channels.newChannel(zis);

		String getName = ze.getName();
		// String getName = "suliman.srt";
		setGlopalDis(destDir + getName);

		setGlopalDis(getGlopalDis().replace(" ", "."));
		setGlopalDis(getGlopalDis().replace("(", ""));
		setGlopalDis(getGlopalDis().replace(")", ""));

		FileOutputStream fos2 = new FileOutputStream(getGlopalDis()); // instead
																		// of
																		// (destDir
																		// +
																		// File.separator
																		// +
																		// getName)
		fos2.getChannel().transferFrom(rbc2, 0, Long.MAX_VALUE);
		fos2.close();

	}

	public void openFileWithVlc(String filePath) throws IOException {

		Runtime.getRuntime().exec("open " + filePath + " -a vlc.app");

	}

	public void getProcesses() throws IOException {
		

		try {
			String process;
			// getRuntime: Returns the runtime object associated with the
			// current Java application.
			// exec: Executes the specified string command in a separate
			// process.
			// Process p = Runtime.getRuntime().exec("ps -few");
			// Process p = Runtime.getRuntime().exec("lsof");
			String exec = "lsof -c VLC -a -d 12";
			Process p = Runtime.getRuntime().exec(exec);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String c = "";
			while ((process = input.readLine()) != null) {

				c = c + process; // by line
			}
			p = Runtime.getRuntime().exec(exec);
			// lsof -c VLC -a -r # to get all the Files opened by VLC.
			// lsof +D /dir path/ # to get all files opened in this path.
			// lsof -d ''12,r'' -a -c VLC # to check what files are opened by
			// VLC.

			p = Runtime.getRuntime().exec(exec);
			InputStream in = new BufferedInputStream(p.getInputStream());

			ReadableByteChannel rbc = Channels.newChannel(in);

			FileOutputStream fos = new FileOutputStream("/Users/Sllom/Desktop/sllom101.txt");

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			in.close();
			input.close();
			fos.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

//	public void getThreads() {
//		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//
//		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
//		for (Thread thread : threadArray) {
//
//		}
//
//	}

	public static String getTheMovieName() throws IOException {
		String c = "";
		System.out.println("daf");
		try {
			String process = "";
			
			double d = System.currentTimeMillis();
			String exec = "lsof -c VLC -a -d 0-20";

			Process p = Runtime.getRuntime().exec(exec);

			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			if ((process = input.readLine()) != null) {

				String[] arrAllFormat = getAllFormat();

				while (!(process.equals(null))) {

					for (int i = 0; i < arrAllFormat.length; i++) {

						if (process.toLowerCase().endsWith(arrAllFormat[i].toLowerCase())) {

							c = process;
							break;
							// c = c.replaceAll("."," ");
						}
					}
					if (c.equals(process))
						break;
					process = input.readLine();
				}
				
				
				c = c.split("/")[c.split("/").length - 1];

			} else
				return null;
			double s = System.currentTimeMillis();
			double ftime = s-d;
			System.out.println("------------- "+ftime+" ------------------");

		} catch (Exception e) {
			e.getStackTrace();
		}

		return c;
	}

	public static String[] getAllFormat() {
		String formats[] = { ".ASX", ".DTS", ".GXF", ".M2V", ".M3U", ".M4V", ".AAC", ".MKV", ".MOV", ".MPEG4", ".OMA",
				".TS", ".IFO", ".AVI", ".MPEG", ".MPG", ".M4A", ".WAV", ".WMV", ".AC3", ".MP4", ".WMA", ".MKA",
				".M4P" };
		return formats;

	}

}
