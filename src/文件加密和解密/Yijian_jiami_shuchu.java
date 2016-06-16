package 文件加密和解密;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.SeekableByteChannel;

import javax.swing.JLabel;

public class Yijian_jiami_shuchu {
	File file;
	String password;
	JLabel jlabel;
	RandomAccessFile rf;
	byte[] bpassword = new byte[128];// 128位密码验证码
	long have_read = 0;
	long have_wirte = 0;// 记录随机读写的指针位置
	boolean isAllOk;
	long starttime = System.currentTimeMillis();
	Fileyanzheng fileyanzheng;

	public Yijian_jiami_shuchu(File file, String password, JLabel jlabel) {
		this.file = file;
		this.password = password;
		this.jlabel = jlabel;
		/*
		 * if(file.renameTo(file)) { System.out.println("开始在一件加密中文件没有被占用"); }
		 * else{System.out.println("开始在一件加密中文占用");}
		 */
		/*
		 * try { rf = new RandomAccessFile(file, "rw"); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		isAllOk = false;
		bpassword = jiami_shuchu.mimayanzheng(password.getBytes());
		// System.out.println("一键加密解密中密码验证码:" + new String(bpassword));
	}

	public void yijianjiami(boolean isOverWiteFile,boolean isFileExited,String deleteFilePath) throws IOException {
		if (new File(file.getParent()).getUsableSpace() < 256 + file.length()) {
			main.show_dialog07();// 磁盘的空间不够用了
		} else if(isOverWiteFile)
		    {
			 if(isFileExited)
				new File(deleteFilePath).delete();
			rf = new RandomAccessFile(file, "rw");
			have_wirte = 0;
			have_read = 0;
			rf.seek(0);
			fileyanzheng = new Fileyanzheng((int) file.length() + 128);// 传入文件的长度
			//System.out.println("一键加密中传入的文件的长度是：" + (file.length() + 128));
			byte[] b = new byte[8192];
			long filelong = file.length();// 得到输入文件的大小(字节数)用来刷新精度
			byte[] pa = password.getBytes();// 将密码转换成acsii码,方便使用,byte的范围是-128~127之间
			int pa_length = pa.length;// 密码的长度
			int nowpa = 0;// 记录当前的密码走到了哪一位
			int len;
			boolean is_fisrt_mimayanzheng = true;// 第一次写入密码验证码之时的验证
			isAllOk = true;
			// starttime = System.currentTimeMillis();

			// 加密代码
			nowpa = 0;
			rf.seek(0);// 移动到文件的开始
			byte[] readtemp = new byte[128];// 128的交换缓冲区
			int len2 = 0;// 读入缓冲区的文件长度
			while ((len = rf.read(b)) != -1) {// 将每一次读入的文件,以字节方式读取到b这个字节数组之中
				have_read += len;
				int nowb = 0;// 在这个字节数组中,游标到了哪一位
				/*
				 * if (!is_fisrt_mimayanzheng) {
				 * 
				 * for (int i = 0; i < len2; i++) { b[i] = readtemp[i];
				 * }//如果是不是第一次写入密码验证码之前，那么将密码缓冲区的数据覆盖b[i]低位的数据 len2 =
				 * rf.read(readtemp);//将数据读取到缓冲区 } // 如果文件比密码都还要短那么,执行此种加密方式
				 */
				if (pa_length > filelong) {

					for (int i = 0; i < len; i++) {
						for (int j = 0; j < pa_length; j++) {
							b[i] = (byte) (b[i] ^ pa[pa_length - j - 1] ^ pa_length);
						}
						for (int j = 0; j < pa_length; j++) {
							b[i] = (byte) (b[i] + pa[pa_length - j - 1] + pa_length);
						}
					}

				} else {
					while (nowb < len) {
						b[nowb] = (byte) (b[nowb] + pa[nowpa]);
						b[nowb] = (byte) (b[nowb] ^ pa_length);

						nowpa++;
						nowb++;
						if (nowpa == pa_length) {
							nowpa = 0;// 形成循环
						}
					}// end while
				}
				// rf.seek(have_wirte);// 调节进度

				if (is_fisrt_mimayanzheng) {
					// 如果是第一次，那么写入密码验证码
					rf.seek(0);
					// rf.write(bpassword);
					for (int i = 0; i < 128; i++)
						readtemp[i] = bpassword[i];
					is_fisrt_mimayanzheng = false;
				}
				rf.seek(have_wirte);// 已经写的数据不能够覆盖已经写入的。
				rf.write(readtemp);
				fileyanzheng.addByte(readtemp, 128);
				have_wirte = have_wirte + 128;

				if (len < 8192 || have_read >= filelong)// 表示数据已经读取完毕了
				{
					rf.write(b,0,len);
					fileyanzheng.addByte(b, len);
					have_wirte=have_wirte+len;
					//System.out.println("读取数据的循环停止(一键加密之中)");
					break;
				}// 否则还有没有读取完毕的数据，在写入的时候不能够覆盖没有读取的数据
				else {
					rf.write(b, 0, 8192 - 128);
					fileyanzheng.addByte(b, 8192 - 128);
					have_wirte = have_wirte + 8192 - 128;

					for (int i = 0; i < 128; i++) {
						readtemp[i] = b[8192 - 128 + i];// 将高位没有写入的数据保存到temp的数组
					}
				}

				rf.seek(have_read);
				// have_read = have_read + len;
				float jingdu = (float) (100.0 * have_read / filelong);
				jlabel.setText("解密进度:=>" + jingdu + "%......");

			}// end while

			// jingdutiao();
			// bufferedOutputStream.write(b, 0, len);

			try {
				//System.out.println("已经写入的数据有："+have_wirte);
				rf.seek(have_wirte);
				rf.write(fileyanzheng.getYanzheng());
				//System.out.println("一键加密中写入的文件验证码："
					//	+ new String(fileyanzheng.getYanzheng()));
				rf.close();
			} catch (Exception e) {
				rf.close();
				//System.out.println("随机文件读取输出文件出问题");
			} finally {
				rf.close();
				//System.out.println("随机文件读取输出文件出问题2");
			}

			String path = file.getPath();
			// File file2=new File(path);
			path = path + ".tingfeng";
			path = path.replaceAll("\\\\", "\\\\\\\\");

			//if (file.renameTo(file)) {
			//	System.out.println("文件没有被占用.");
		//	} else
			//	System.out.println("文件被占用");

			if (file.renameTo(new File(path))) {
				//System.out.println("一件加密重命名成功");
			} 
				//System.out.println("一件加密重命名成失败  path=" + path
					//	+ "\nfile.exists()  "
					//	+ file.exists() + "\nnew File(path).exists()  "
					//	+ new File(path).exists());
			// file2.renameTo(new File(path));

		}// end else
		
		try {
			if(rf!=null)
			rf.close();
		} catch (Exception e) {
			rf.close();
			//System.out.println("一件解密输出文件出问题");
		} finally {
			rf.close();
			//System.out.println("一件解密输出文件出问题2");
		}
		if (!isAllOk)
			starttime = System.currentTimeMillis();
		jlabel.setText("完成加密,Time:" + (System.currentTimeMillis() - starttime)
				/ 1000.0 + "秒");

	}

	public void yijianjiemi(boolean isOverWiteFile,boolean isFileExited,String deleteFilePath) throws IOException {
		// filelong=file.length();
		if (!IsFileOk.isFileOk(file)) {
			main.show_dialog06();
		} else if(isOverWiteFile)
		    {  
			rf = new RandomAccessFile(file, "rw");
			rf.seek(0);
			have_wirte = 0;
			have_read = 0;
			byte[] readtemp = new byte[128];
			rf.read(readtemp);
			String t1 = new String(readtemp);
			String t2 = new String(bpassword);
			if (!t1.equals(t2)) {
				main.show_dialog05();
				try {
					if(rf!=null)
					rf.close();
				} catch (Exception e) {
					rf.close();
					//System.out.println("一件解密输出文件出问题");
				} finally {
					rf.close();
					//System.out.println("一件解密输出文件出问题2");
				}
			} else {// 解密代码
				if(isFileExited)
			    new File(deleteFilePath).delete();
				
				byte[] b = new byte[8192];
				long filelong = file.length();// 得到输入文件的大小(字节数)用来刷新精度
				byte[] pa = password.getBytes();// 将密码转换成acsii码,方便使用,byte的范围是-128~127之间
				int pa_length = pa.length;// 密码的长度
				int nowpa = 0;// 记录当前的密码走到了哪一位
				int len;
				boolean fisrt_mimayanzheng = false;// 第一次写入密码验证码之时的验证
				isAllOk = true;
				// starttime = System.currentTimeMillis();
				// 加密代码
				nowpa = 0;
				rf.seek(0);// 移动到文件的开始
				int len2 = 0;// 读入缓冲区的文件长度
				rf.seek(128);
				while ((len = rf.read(b)) != -1) {// 将每一次读入的文件,以字节方式读取到b这个字节数组之中
					// have_read += len;
					int nowb = 0;// 在这个字节数组中,游标到了哪一位

					if (have_read + 128 + len >= filelong)// 如果读取到了文件验证码
					{
						len = (int) (filelong - 128 - have_read);
					}// 重新设置长度
					if (len <= 0)
						break;
					else
					// 如果密码比文件长得解密方式
					if (pa_length > filelong - 256) {

						for (int i = 0; i < len; i++) {
							for (int j = 0; j < pa_length; j++) {
								b[i] = (byte) (b[i] - pa[pa_length - j - 1] - pa_length);
							}

							for (int j = 0; j < pa_length; j++) {
								b[i] = (byte) (b[i] ^ pa[pa_length - j - 1] ^ pa_length);
							}

						}

					} else {
						while (nowb < len) {
							b[nowb] = (byte) (b[nowb] ^ pa_length);
							b[nowb] = (byte) (b[nowb] - pa[nowpa]);

							nowpa++;
							nowb++;
							if (nowpa == pa_length) {
								nowpa = 0;// 形成循环
							}
						}// end while
					}
					have_read = have_read + len;
					float jingdu = (float) (100.0 * have_read / filelong);
					jlabel.setText("解密进度:=>" + jingdu + "%......");
					rf.seek(have_wirte);// 调节进度
					rf.write(b, 0, len);
					have_wirte = have_wirte + len;
					rf.seek(have_wirte + 128);
					// rf.skipBytes(128);
				}// end while

				// jingdutiao();
				// bufferedOutputStream.write(b, 0, len);

				rf.seek(0);
				rf.setLength(file.length() - 256);
			
				// 文件的长度是聪哥考试计算的

			// rf.close();
			try {
				rf.close();
			} catch (Exception e) {
				rf.close();
				//System.out.println("一件解密输出文件出问题");
			} finally {
				rf.close();
				//System.out.println("一件解密输出文件出问题2");
			}
			String path = file.getPath();

			// File file2=new File(path);
			path = path.substring(0, path.length() - 9);
			try{
			path = path.replaceAll("\\\\", "\\\\\\\\");
			file.renameTo(new File(path));
			}
			catch(Exception e)
			{
				//path = path.replaceAll("\\\\", "\\\\\\\\");
			System.out.print("一键解密文件，重命名失败");
			}
			finally{
				if(path.endsWith(".tingfeng"))
				{	path = path.replaceAll("\\\\", "\\\\\\\\");
				file.renameTo(new File(path));
				}
			}
			//if (file.renameTo(new File(path))) {
			//	System.out.println("一键解密重命名失败");
			//} else {
			//	System.out.println("一件解密重命名失败,new File(path).getPath=" + path);

				// file2.renameTo(new File(path));
			//}
		}
		}// end else
		
		try {
			if(rf!=null)
			rf.close();
		} catch (Exception e) {
			rf.close();
			//System.out.println("一件解密输出文件出问题");
		} finally {
			rf.close();
			//System.out.println("一件解密输出文件出问题2");
		}
		
		if (!isAllOk)
			starttime = System.currentTimeMillis();
		jlabel.setText("完成解密,Time:" + (System.currentTimeMillis() - starttime)
				/ 1000.0 + "秒");

	}

}
