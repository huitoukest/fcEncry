package 文件加密和解密;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;

//加密说明,将密码转换成ASCII码的值,
//解密的时候文件长度的变化要注意

public class jiami_shuchu {
	File inputf;// 输入的文件(包括路径)
	File outputf;
	String password;
	long count = 0;
	static JLabel jlabel;
	int len = -1;// 保存从文件中读取的文件长度
	static long have_read = 0;// 记录现在已经读取了多少的类容
	static long filelong;// 读入的文件的长度
	static String content;// 当前是加密还是解密
	static int jingdu_temp = 0;// 用来判断是否刷新jlabel的temp
	long starttime = System.currentTimeMillis();
	Boolean isAllOk = false;// 是否在加密解密的过程中一次性完成
	Fileyanzheng fileyanzheng;
	boolean isOverWriteFile;// 是否覆盖同名源文件
	boolean isFileExited;

	jiami_shuchu(File inputf, File outputf, String password, JLabel jlabel) {
		this.inputf = inputf;
		this.outputf = outputf;// 加上路径中的双斜杠,后面使用的时候只需要加上文件名就行
		this.password = password;
		jiami_shuchu.jlabel = jlabel;
		isOverWriteFile = false;
		isFileExited = false;
	}

	public void closeStream(BufferedOutputStream bufferedOutputStream,
			BufferedInputStream bufferedInputStream, FileInputStream fileinput) {

		if (bufferedInputStream != null)
			try {
				bufferedInputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				if (bufferedInputStream != null)
					try {
						bufferedInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		if (fileinput != null)
			try {
				fileinput.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				if (fileinput != null)
					try {
						fileinput.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		if (bufferedOutputStream != null)
			try {
				bufferedOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bufferedOutputStream != null)
					try {
						bufferedOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

	}

	// 加密处理,第一次简单加密
	public void jiami_diyici() {
		filelong=inputf.length();
		System.out.println("1当前检查到的可用空间和文件长度分别是_"+new File(outputf.getParent()).getUsableSpace()+"\n"+(filelong-256));
		if (new File(outputf.getParent()).getUsableSpace() < filelong + 256) {
			main.show_dialog07();// 检测加密的空间够不够
		} else {
			if (isFileExited = outputf.exists()) {
				isOverWriteFile = main.show_dialog08(outputf.getName());
			} else
				isOverWriteFile = true;
			if (isOverWriteFile = true)
				jiami_diyici_start();
		}
	}

	public void jiami_diyici_start() {
		FileInputStream fileinput = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(outputf));
			fileinput = new FileInputStream(inputf);
			long inputf_long = inputf.length();// 得到输入文件的大小(字节数)用来刷新精度
			filelong = inputf_long;
			// fileyanzheng.clearAll(fileLength);
			fileyanzheng = new Fileyanzheng((int) (filelong + 128));
			// System.out.println("加密的时候的文件的长度："+(filelong+128));
			bufferedInputStream = new BufferedInputStream(fileinput);
			byte[] b = new byte[8192];
			byte[] pa = password.getBytes();// 将密码转换成acsii码,方便使用,byte的范围是-128~127之间
			int pa_length = pa.length;// 密码的长度
			int nowpa = 0;// 记录当前的密码走到了哪一位
			// int lastfile=0;//记录上一次文件加密的数字距离现在的个数
			have_read = 0;// 初始化
			jingdu_temp = 0;
			// System.out.println("密码验证码:"+new String(mimayanzheng(pa)));
			isAllOk = true;
			starttime = System.currentTimeMillis();
			bufferedOutputStream.write(mimayanzheng(pa));
			fileyanzheng.addByte(mimayanzheng(pa), 128);
			// 加密代码
			nowpa = 0;
			while ((len = bufferedInputStream.read(b)) != -1) {// 将每一次读入的文件,以字节方式读取到b这个字节数组之中

				int nowb = 0;// 在这个字节数组中,游标到了哪一位
				// 如果文件比密码都还要短那么,执行此种加密方式
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

				have_read = have_read + len;
				content = "加密进度:=>";
				float jingdu = (float) (100.0 * have_read / filelong);
				// System.out.println("1."+content+jingdu+"%......");
				if (Math.abs(jingdu - jingdu_temp) < 3) {
					jingdu_temp++;
					jlabel.setText(content + jingdu + "%......");
					// System.out.println(content+jingdu+"%......");
				}

				// jingdutiao();
				bufferedOutputStream.write(b, 0, len);
				fileyanzheng.addByte(b, len);
			}

			try {// 最后写入验证码
				bufferedOutputStream.write(fileyanzheng.getYanzheng());
				// System.out.println("加密所得到的文件完整性验证码："+new
				// String(fileyanzheng.getYanzheng()));
				// bufferedInputStream.close();
				closeStream(bufferedOutputStream, bufferedInputStream,
						fileinput);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}// try
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			closeStream(bufferedOutputStream, bufferedInputStream, fileinput);
			if (!isAllOk)
				starttime = System.currentTimeMillis();
			jlabel.setText("完成加密,Time:"
					+ (System.currentTimeMillis() - starttime) / 1000.0 + "秒");
		}
	}

	public void jiemi_diyici_start() {
		FileInputStream fileinput = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(outputf));
			fileinput = new FileInputStream(inputf);
			long inputf_long = inputf.length();// 得到输入文件的大小(字节数)用来刷新精度
			filelong = inputf_long;
			// fileyanzheng.clearAll(filelong);
			bufferedInputStream = new BufferedInputStream(fileinput);
			byte[] b = new byte[8192];
			byte[] pa = password.getBytes();// 将密码转换成acsii码,方便使用,byte的范围是-128~127之间
			int pa_length = pa.length;// 密码的长度
			int nowpa = 0;// 记录当前的密码走到了哪一位
			// int lastfile=0;//记录上一次文件加密的数字距离现在的个数

			have_read = 0;// 初始化
			jingdu_temp = 0;
			// 提取密码中转验证码,作比较
			byte[] zhongzhuanma = new byte[128];
			len = bufferedInputStream.read(zhongzhuanma);
			have_read = have_read + 128;
			
			isAllOk = true;
			// starttime = System.currentTimeMillis();
            // 解密代码
				nowpa = 0;
				// bufferedInputStream.mark((int) (filelong-128));
				// boolean isLastRead=false;//确定在下面的循环之中是否是最后一次
				while ((len = bufferedInputStream.read(b)) != -1) {// 将每一次读入的文件,以字节方式读取到b这个字节数组之中

					if (have_read + 128 + len >= filelong)// 如果读取到了文件验证码
					{
						len = (int) (filelong - 128 - have_read);
					}// 重新设置长度
					int nowb = 0;// 在这个字节数组中,游标到了哪一位
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
					content = "解密进度:=>";
					float jingdu = (float) (100.0 * have_read / filelong);
					// System.out.println("1."+content+jingdu+"%......");
					if (Math.abs(jingdu - jingdu_temp) < 3) {
						jingdu_temp++;
						jlabel.setText(content + jingdu + "%......");
						// System.out.println(content+jingdu+"%......");
					}

					bufferedOutputStream.write(b, 0, len);

				
			}// end else
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			closeStream(bufferedOutputStream, bufferedInputStream, fileinput);

			if (!isAllOk)
				starttime = System.currentTimeMillis();
			jlabel.setText("完成解密,Time:"
					+ (System.currentTimeMillis() - starttime) / 1000.0 + "秒");
			// jlabel.setText("正在解密:=>"+"%......");
		}
	}

	public void jiemi_diyici() {
		filelong=inputf.length();
		//System.out.println("1当前检查到的可用空间和文件长度分别是_"+new File(outputf.getParent()).getUsableSpace()+"\n"+(filelong-256));
		if (new File(outputf.getParent()).getUsableSpace() < filelong-256) {
			main.show_dialog07();// 检测加密的空间够不够
		//System.out.println("当前检查到的可用空间和文件长度分别是_"+outputf.getPath()+"\n"+outputf.getFreeSpace()+"\n"+(filelong-256));
		 } else if (!IsFileOk.isFileOk(inputf)) {
			main.show_dialog06();
		} else {
			if (isFileExited = outputf.exists()) {
				isOverWriteFile = main.show_dialog09(outputf.getName());
			} else
				isOverWriteFile = true;
			if (isOverWriteFile) {
				FileInputStream fileinput = null;
				BufferedInputStream bufferedInputStream = null;
				try {
					fileinput = new FileInputStream(inputf);
					bufferedInputStream = new BufferedInputStream(fileinput);
					byte[] pa = password.getBytes();// 将密码转换成acsii码,方便使用,byte的范围是-128~127之间
					int pa_length = pa.length;// 密码的长度
					// 提取密码中转验证码,作比较
					byte[] zhongzhuanma = new byte[128];
					bufferedInputStream.read(zhongzhuanma);
					if (!new String(zhongzhuanma).equals(new String(
							mimayanzheng(pa)))) {// 如果密码不同
						main.show_dialog05();

					} else {// 如果都没有问题，那么就开始解密
						jiemi_diyici_start();
					}// end else
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						fileinput.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						bufferedInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
	}

	// 用来验证密码,将密码转换成128位的验证数组
	public static byte[] mimayanzheng(byte[] pa) {
		byte[] mima = new byte[128];
		int le = pa.length;
		int temp2 = le % 128;
		byte[] zhongzhuan = new byte[128];// 将密码变成128位的加密中转数字,第129位密码和第1位相加,并转换成byte类型,依次推断

		for (int i = 0, j = 0; i < 128; i++) {
			if (j >= le) {
				j = 0;
			}
			int temp = 0;
			zhongzhuan[i] = (byte) i;
			while (temp + j < le) {
				zhongzhuan[i] = (byte) (pa[j++] + j + temp + le);
				temp += 128;
			}
		}
		for (int i = 0; i < 128; i++) {

			if (zhongzhuan[i] < 0)
				temp2 = -zhongzhuan[i] % 128;

			// System.out.println(new String("temp2="+temp2+"i="+i));
			int k = (zhongzhuan[temp2]);
			if (k == 0) {
				k = zhongzhuan[temp2] + zhongzhuan[i];
			}
			zhongzhuan[i] = (byte) ((zhongzhuan[i] + zhongzhuan[temp2] + zhongzhuan[temp2]
					% zhongzhuan[i]) * k);
		}

		for (int i = 0; i < 128; i++) {
			mima[i] = (byte) i;
			mima[i] = (byte) (mima[i] ^ le + le * zhongzhuan[i]);
			mima[i] = (byte) (mima[i] ^ zhongzhuan[i]);
		}
		// System.out.println("mima="+new String(mima));
		return mima;
	}

}
