package �ļ����ܺͽ���;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;

//����˵��,������ת����ASCII���ֵ,
//���ܵ�ʱ���ļ����ȵı仯Ҫע��

public class jiami_shuchu {
	File inputf;// ������ļ�(����·��)
	File outputf;
	String password;
	long count = 0;
	static JLabel jlabel;
	int len = -1;// ������ļ��ж�ȡ���ļ�����
	static long have_read = 0;// ��¼�����Ѿ���ȡ�˶��ٵ�����
	static long filelong;// ������ļ��ĳ���
	static String content;// ��ǰ�Ǽ��ܻ��ǽ���
	static int jingdu_temp = 0;// �����ж��Ƿ�ˢ��jlabel��temp
	long starttime = System.currentTimeMillis();
	Boolean isAllOk = false;// �Ƿ��ڼ��ܽ��ܵĹ�����һ�������
	Fileyanzheng fileyanzheng;
	boolean isOverWriteFile;// �Ƿ񸲸�ͬ��Դ�ļ�
	boolean isFileExited;

	jiami_shuchu(File inputf, File outputf, String password, JLabel jlabel) {
		this.inputf = inputf;
		this.outputf = outputf;// ����·���е�˫б��,����ʹ�õ�ʱ��ֻ��Ҫ�����ļ�������
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

	// ���ܴ���,��һ�μ򵥼���
	public void jiami_diyici() {
		filelong=inputf.length();
		System.out.println("1��ǰ��鵽�Ŀ��ÿռ���ļ����ȷֱ���_"+new File(outputf.getParent()).getUsableSpace()+"\n"+(filelong-256));
		if (new File(outputf.getParent()).getUsableSpace() < filelong + 256) {
			main.show_dialog07();// �����ܵĿռ乻����
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
			long inputf_long = inputf.length();// �õ������ļ��Ĵ�С(�ֽ���)����ˢ�¾���
			filelong = inputf_long;
			// fileyanzheng.clearAll(fileLength);
			fileyanzheng = new Fileyanzheng((int) (filelong + 128));
			// System.out.println("���ܵ�ʱ����ļ��ĳ��ȣ�"+(filelong+128));
			bufferedInputStream = new BufferedInputStream(fileinput);
			byte[] b = new byte[8192];
			byte[] pa = password.getBytes();// ������ת����acsii��,����ʹ��,byte�ķ�Χ��-128~127֮��
			int pa_length = pa.length;// ����ĳ���
			int nowpa = 0;// ��¼��ǰ�������ߵ�����һλ
			// int lastfile=0;//��¼��һ���ļ����ܵ����־������ڵĸ���
			have_read = 0;// ��ʼ��
			jingdu_temp = 0;
			// System.out.println("������֤��:"+new String(mimayanzheng(pa)));
			isAllOk = true;
			starttime = System.currentTimeMillis();
			bufferedOutputStream.write(mimayanzheng(pa));
			fileyanzheng.addByte(mimayanzheng(pa), 128);
			// ���ܴ���
			nowpa = 0;
			while ((len = bufferedInputStream.read(b)) != -1) {// ��ÿһ�ζ�����ļ�,���ֽڷ�ʽ��ȡ��b����ֽ�����֮��

				int nowb = 0;// ������ֽ�������,�α굽����һλ
				// ����ļ������붼��Ҫ����ô,ִ�д��ּ��ܷ�ʽ
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
							nowpa = 0;// �γ�ѭ��
						}
					}// end while
				}

				have_read = have_read + len;
				content = "���ܽ���:=>";
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

			try {// ���д����֤��
				bufferedOutputStream.write(fileyanzheng.getYanzheng());
				// System.out.println("�������õ����ļ���������֤�룺"+new
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
			jlabel.setText("��ɼ���,Time:"
					+ (System.currentTimeMillis() - starttime) / 1000.0 + "��");
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
			long inputf_long = inputf.length();// �õ������ļ��Ĵ�С(�ֽ���)����ˢ�¾���
			filelong = inputf_long;
			// fileyanzheng.clearAll(filelong);
			bufferedInputStream = new BufferedInputStream(fileinput);
			byte[] b = new byte[8192];
			byte[] pa = password.getBytes();// ������ת����acsii��,����ʹ��,byte�ķ�Χ��-128~127֮��
			int pa_length = pa.length;// ����ĳ���
			int nowpa = 0;// ��¼��ǰ�������ߵ�����һλ
			// int lastfile=0;//��¼��һ���ļ����ܵ����־������ڵĸ���

			have_read = 0;// ��ʼ��
			jingdu_temp = 0;
			// ��ȡ������ת��֤��,���Ƚ�
			byte[] zhongzhuanma = new byte[128];
			len = bufferedInputStream.read(zhongzhuanma);
			have_read = have_read + 128;
			
			isAllOk = true;
			// starttime = System.currentTimeMillis();
            // ���ܴ���
				nowpa = 0;
				// bufferedInputStream.mark((int) (filelong-128));
				// boolean isLastRead=false;//ȷ���������ѭ��֮���Ƿ������һ��
				while ((len = bufferedInputStream.read(b)) != -1) {// ��ÿһ�ζ�����ļ�,���ֽڷ�ʽ��ȡ��b����ֽ�����֮��

					if (have_read + 128 + len >= filelong)// �����ȡ�����ļ���֤��
					{
						len = (int) (filelong - 128 - have_read);
					}// �������ó���
					int nowb = 0;// ������ֽ�������,�α굽����һλ
					if (len <= 0)
						break;
					else
					// ���������ļ����ý��ܷ�ʽ
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
								nowpa = 0;// �γ�ѭ��
							}
						}// end while
					}
					have_read = have_read + len;
					content = "���ܽ���:=>";
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
			jlabel.setText("��ɽ���,Time:"
					+ (System.currentTimeMillis() - starttime) / 1000.0 + "��");
			// jlabel.setText("���ڽ���:=>"+"%......");
		}
	}

	public void jiemi_diyici() {
		filelong=inputf.length();
		//System.out.println("1��ǰ��鵽�Ŀ��ÿռ���ļ����ȷֱ���_"+new File(outputf.getParent()).getUsableSpace()+"\n"+(filelong-256));
		if (new File(outputf.getParent()).getUsableSpace() < filelong-256) {
			main.show_dialog07();// �����ܵĿռ乻����
		//System.out.println("��ǰ��鵽�Ŀ��ÿռ���ļ����ȷֱ���_"+outputf.getPath()+"\n"+outputf.getFreeSpace()+"\n"+(filelong-256));
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
					byte[] pa = password.getBytes();// ������ת����acsii��,����ʹ��,byte�ķ�Χ��-128~127֮��
					int pa_length = pa.length;// ����ĳ���
					// ��ȡ������ת��֤��,���Ƚ�
					byte[] zhongzhuanma = new byte[128];
					bufferedInputStream.read(zhongzhuanma);
					if (!new String(zhongzhuanma).equals(new String(
							mimayanzheng(pa)))) {// ������벻ͬ
						main.show_dialog05();

					} else {// �����û�����⣬��ô�Ϳ�ʼ����
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

	// ������֤����,������ת����128λ����֤����
	public static byte[] mimayanzheng(byte[] pa) {
		byte[] mima = new byte[128];
		int le = pa.length;
		int temp2 = le % 128;
		byte[] zhongzhuan = new byte[128];// ��������128λ�ļ�����ת����,��129λ����͵�1λ���,��ת����byte����,�����ƶ�

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
