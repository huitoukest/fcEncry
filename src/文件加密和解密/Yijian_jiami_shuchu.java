package �ļ����ܺͽ���;

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
	byte[] bpassword = new byte[128];// 128λ������֤��
	long have_read = 0;
	long have_wirte = 0;// ��¼�����д��ָ��λ��
	boolean isAllOk;
	long starttime = System.currentTimeMillis();
	Fileyanzheng fileyanzheng;

	public Yijian_jiami_shuchu(File file, String password, JLabel jlabel) {
		this.file = file;
		this.password = password;
		this.jlabel = jlabel;
		/*
		 * if(file.renameTo(file)) { System.out.println("��ʼ��һ���������ļ�û�б�ռ��"); }
		 * else{System.out.println("��ʼ��һ����������ռ��");}
		 */
		/*
		 * try { rf = new RandomAccessFile(file, "rw"); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		isAllOk = false;
		bpassword = jiami_shuchu.mimayanzheng(password.getBytes());
		// System.out.println("һ�����ܽ�����������֤��:" + new String(bpassword));
	}

	public void yijianjiami(boolean isOverWiteFile,boolean isFileExited,String deleteFilePath) throws IOException {
		if (new File(file.getParent()).getUsableSpace() < 256 + file.length()) {
			main.show_dialog07();// ���̵Ŀռ䲻������
		} else if(isOverWiteFile)
		    {
			 if(isFileExited)
				new File(deleteFilePath).delete();
			rf = new RandomAccessFile(file, "rw");
			have_wirte = 0;
			have_read = 0;
			rf.seek(0);
			fileyanzheng = new Fileyanzheng((int) file.length() + 128);// �����ļ��ĳ���
			//System.out.println("һ�������д�����ļ��ĳ����ǣ�" + (file.length() + 128));
			byte[] b = new byte[8192];
			long filelong = file.length();// �õ������ļ��Ĵ�С(�ֽ���)����ˢ�¾���
			byte[] pa = password.getBytes();// ������ת����acsii��,����ʹ��,byte�ķ�Χ��-128~127֮��
			int pa_length = pa.length;// ����ĳ���
			int nowpa = 0;// ��¼��ǰ�������ߵ�����һλ
			int len;
			boolean is_fisrt_mimayanzheng = true;// ��һ��д��������֤��֮ʱ����֤
			isAllOk = true;
			// starttime = System.currentTimeMillis();

			// ���ܴ���
			nowpa = 0;
			rf.seek(0);// �ƶ����ļ��Ŀ�ʼ
			byte[] readtemp = new byte[128];// 128�Ľ���������
			int len2 = 0;// ���뻺�������ļ�����
			while ((len = rf.read(b)) != -1) {// ��ÿһ�ζ�����ļ�,���ֽڷ�ʽ��ȡ��b����ֽ�����֮��
				have_read += len;
				int nowb = 0;// ������ֽ�������,�α굽����һλ
				/*
				 * if (!is_fisrt_mimayanzheng) {
				 * 
				 * for (int i = 0; i < len2; i++) { b[i] = readtemp[i];
				 * }//����ǲ��ǵ�һ��д��������֤��֮ǰ����ô�����뻺���������ݸ���b[i]��λ������ len2 =
				 * rf.read(readtemp);//�����ݶ�ȡ�������� } // ����ļ������붼��Ҫ����ô,ִ�д��ּ��ܷ�ʽ
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
							nowpa = 0;// �γ�ѭ��
						}
					}// end while
				}
				// rf.seek(have_wirte);// ���ڽ���

				if (is_fisrt_mimayanzheng) {
					// ����ǵ�һ�Σ���ôд��������֤��
					rf.seek(0);
					// rf.write(bpassword);
					for (int i = 0; i < 128; i++)
						readtemp[i] = bpassword[i];
					is_fisrt_mimayanzheng = false;
				}
				rf.seek(have_wirte);// �Ѿ�д�����ݲ��ܹ������Ѿ�д��ġ�
				rf.write(readtemp);
				fileyanzheng.addByte(readtemp, 128);
				have_wirte = have_wirte + 128;

				if (len < 8192 || have_read >= filelong)// ��ʾ�����Ѿ���ȡ�����
				{
					rf.write(b,0,len);
					fileyanzheng.addByte(b, len);
					have_wirte=have_wirte+len;
					//System.out.println("��ȡ���ݵ�ѭ��ֹͣ(һ������֮��)");
					break;
				}// ������û�ж�ȡ��ϵ����ݣ���д���ʱ���ܹ�����û�ж�ȡ������
				else {
					rf.write(b, 0, 8192 - 128);
					fileyanzheng.addByte(b, 8192 - 128);
					have_wirte = have_wirte + 8192 - 128;

					for (int i = 0; i < 128; i++) {
						readtemp[i] = b[8192 - 128 + i];// ����λû��д������ݱ��浽temp������
					}
				}

				rf.seek(have_read);
				// have_read = have_read + len;
				float jingdu = (float) (100.0 * have_read / filelong);
				jlabel.setText("���ܽ���:=>" + jingdu + "%......");

			}// end while

			// jingdutiao();
			// bufferedOutputStream.write(b, 0, len);

			try {
				//System.out.println("�Ѿ�д��������У�"+have_wirte);
				rf.seek(have_wirte);
				rf.write(fileyanzheng.getYanzheng());
				//System.out.println("һ��������д����ļ���֤�룺"
					//	+ new String(fileyanzheng.getYanzheng()));
				rf.close();
			} catch (Exception e) {
				rf.close();
				//System.out.println("����ļ���ȡ����ļ�������");
			} finally {
				rf.close();
				//System.out.println("����ļ���ȡ����ļ�������2");
			}

			String path = file.getPath();
			// File file2=new File(path);
			path = path + ".tingfeng";
			path = path.replaceAll("\\\\", "\\\\\\\\");

			//if (file.renameTo(file)) {
			//	System.out.println("�ļ�û�б�ռ��.");
		//	} else
			//	System.out.println("�ļ���ռ��");

			if (file.renameTo(new File(path))) {
				//System.out.println("һ�������������ɹ�");
			} 
				//System.out.println("һ��������������ʧ��  path=" + path
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
			//System.out.println("һ����������ļ�������");
		} finally {
			rf.close();
			//System.out.println("һ����������ļ�������2");
		}
		if (!isAllOk)
			starttime = System.currentTimeMillis();
		jlabel.setText("��ɼ���,Time:" + (System.currentTimeMillis() - starttime)
				/ 1000.0 + "��");

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
					//System.out.println("һ����������ļ�������");
				} finally {
					rf.close();
					//System.out.println("һ����������ļ�������2");
				}
			} else {// ���ܴ���
				if(isFileExited)
			    new File(deleteFilePath).delete();
				
				byte[] b = new byte[8192];
				long filelong = file.length();// �õ������ļ��Ĵ�С(�ֽ���)����ˢ�¾���
				byte[] pa = password.getBytes();// ������ת����acsii��,����ʹ��,byte�ķ�Χ��-128~127֮��
				int pa_length = pa.length;// ����ĳ���
				int nowpa = 0;// ��¼��ǰ�������ߵ�����һλ
				int len;
				boolean fisrt_mimayanzheng = false;// ��һ��д��������֤��֮ʱ����֤
				isAllOk = true;
				// starttime = System.currentTimeMillis();
				// ���ܴ���
				nowpa = 0;
				rf.seek(0);// �ƶ����ļ��Ŀ�ʼ
				int len2 = 0;// ���뻺�������ļ�����
				rf.seek(128);
				while ((len = rf.read(b)) != -1) {// ��ÿһ�ζ�����ļ�,���ֽڷ�ʽ��ȡ��b����ֽ�����֮��
					// have_read += len;
					int nowb = 0;// ������ֽ�������,�α굽����һλ

					if (have_read + 128 + len >= filelong)// �����ȡ�����ļ���֤��
					{
						len = (int) (filelong - 128 - have_read);
					}// �������ó���
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
					float jingdu = (float) (100.0 * have_read / filelong);
					jlabel.setText("���ܽ���:=>" + jingdu + "%......");
					rf.seek(have_wirte);// ���ڽ���
					rf.write(b, 0, len);
					have_wirte = have_wirte + len;
					rf.seek(have_wirte + 128);
					// rf.skipBytes(128);
				}// end while

				// jingdutiao();
				// bufferedOutputStream.write(b, 0, len);

				rf.seek(0);
				rf.setLength(file.length() - 256);
			
				// �ļ��ĳ����Ǵϸ翼�Լ����

			// rf.close();
			try {
				rf.close();
			} catch (Exception e) {
				rf.close();
				//System.out.println("һ����������ļ�������");
			} finally {
				rf.close();
				//System.out.println("һ����������ļ�������2");
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
			System.out.print("һ�������ļ���������ʧ��");
			}
			finally{
				if(path.endsWith(".tingfeng"))
				{	path = path.replaceAll("\\\\", "\\\\\\\\");
				file.renameTo(new File(path));
				}
			}
			//if (file.renameTo(new File(path))) {
			//	System.out.println("һ������������ʧ��");
			//} else {
			//	System.out.println("һ������������ʧ��,new File(path).getPath=" + path);

				// file2.renameTo(new File(path));
			//}
		}
		}// end else
		
		try {
			if(rf!=null)
			rf.close();
		} catch (Exception e) {
			rf.close();
			//System.out.println("һ����������ļ�������");
		} finally {
			rf.close();
			//System.out.println("һ����������ļ�������2");
		}
		
		if (!isAllOk)
			starttime = System.currentTimeMillis();
		jlabel.setText("��ɽ���,Time:" + (System.currentTimeMillis() - starttime)
				/ 1000.0 + "��");

	}

}
