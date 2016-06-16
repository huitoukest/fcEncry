package �ļ����ܺͽ���;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IsFileOk {

	/**
	 * ��ǰ�ļ��Ƿ�û����
	 */
	@SuppressWarnings("resource")
	public static boolean isFileOk(File file) {
		if (file.length() < 256)
			return false;
		Fileyanzheng fileyanzheng = new Fileyanzheng(
				(int) (file.length() - 128));
		//System.out.println("��ȡ��ʱ��õ����ļ��ĳ��ȣ�" + (file.length() - 128));
		byte[] fileReadyanzheng = new byte[128];
		RandomAccessFile rf = null;
		BufferedInputStream bufferedInputStream = null;
		try {
			FileInputStream fileinput = new FileInputStream(file);
			// FileOutputStream fileOutputStream=new FileOutputStream(file);
			bufferedInputStream = new BufferedInputStream(fileinput);
			byte[] b = new byte[8192];
			int len = -1;
			int haveRead = 0;
			// bufferedInputStream.skip(file.length()-128);
			// bufferedInputStream.read(fileReadyanzheng);
			// bufferedInputStream.reset();
			// System.out.println("�ļ��ж�ȡ���ļ���֤�룺"+new String(fileReadyanzheng));
			// bufferedInputStream.read(b, 0, 128);
			while ((len = bufferedInputStream.read(b)) != -1) {
				// fileyanzheng.addByte(b, len);
				if (haveRead + len <= file.length() - 128)
					fileyanzheng.addByte(b, len);
				else {
					fileyanzheng.addByte(b,
							(int) (file.length() - 128 - haveRead));
					break;
				}
				haveRead = haveRead + len;
			}

		} catch (IOException e) {

			//System.out.println("�ļ�У����ļ���2��" + e.getStackTrace());
		} finally {
			if (bufferedInputStream != null)
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		try {
			rf = new RandomAccessFile(file, "r");
			rf.skipBytes((int) (file.length() - 128));
			rf.read(fileReadyanzheng);
		} catch (IOException e) {
			//System.out.println("�ļ�У����Ķ�ȡ����1��" + e.getStackTrace());
		}
		//System.out.println("�ļ��ж�ȡ���ļ���֤��" + new String(fileReadyanzheng));
		try {
			if (rf != null)
				rf.close();
		} catch (IOException e) {
			//System.out.println("����ļ��Ķ�ȡ���������ڶ�ȡ�ļ���������У���������" + e.toString());
		} finally {
			try {
				rf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//System.out.println("�ļ��м�������ļ���֤��"
		//		+ new String(fileyanzheng.getYanzheng()));
		if (new String(fileReadyanzheng).equals(new String(fileyanzheng
				.getYanzheng()))) {
			return true;
		} else
			return false;
	}
}
