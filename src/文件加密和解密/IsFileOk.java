package 文件加密和解密;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class IsFileOk {

	/**
	 * 当前文件是否没有损坏
	 */
	@SuppressWarnings("resource")
	public static boolean isFileOk(File file) {
		if (file.length() < 256)
			return false;
		Fileyanzheng fileyanzheng = new Fileyanzheng(
				(int) (file.length() - 128));
		//System.out.println("读取的时候得到的文件的长度：" + (file.length() - 128));
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
			// System.out.println("文件中读取的文件验证码："+new String(fileReadyanzheng));
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

			//System.out.println("文件校验码的计算2：" + e.getStackTrace());
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
			//System.out.println("文件校验码的读取出错1：" + e.getStackTrace());
		}
		//System.out.println("文件中读取的文件验证：" + new String(fileReadyanzheng));
		try {
			if (rf != null)
				rf.close();
		} catch (IOException e) {
			//System.out.println("随机文件的读取发生错误，在读取文件的完整性校验码过程中" + e.toString());
		} finally {
			try {
				rf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//System.out.println("文件中计算出的文件验证："
		//		+ new String(fileyanzheng.getYanzheng()));
		if (new String(fileReadyanzheng).equals(new String(fileyanzheng
				.getYanzheng()))) {
			return true;
		} else
			return false;
	}
}
