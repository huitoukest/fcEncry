package �ļ����ܺͽ���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Listener3 implements ActionListener {
	JTextField jField;
	JLabel l1;
	JTextField input;
	String s_input=null;
	String passowrd=null;
	File filein;
	Yijian_jiami_shuchu yijian_jiami_shuchu;
		//�������,�Լ������ı���,�������
		public Listener3(JTextField jField,JLabel l1,JTextField input){
		
			this.jField=jField;
		    this.l1=l1;
		    this.input=input;	
		    
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(l1.getText().regionMatches(0, "����", 0, 2)||l1.getText().regionMatches(0, "���", 0, 2))
			{//System.out.println("ƥ��ɹ�");
			passowrd=jField.getText();
			s_input=input.getText();
			try{
			filein=new File(s_input.replaceAll("\\\\","\\\\\\\\"));
			}//����ļ���·������Ч��
			catch(Exception exception)
			{
				
			}
			
			if(!filein.canRead()||!filein.isFile())
				{
					main.show_dialog01();
				}
				
				else if(passowrd==null||passowrd.length()>300)
				{
					main.show_dialog03();
				}
				else {
					//������һ���߳���ʹ�ô���
					Thread t = new Thread() {
						@Override
						public void run() {
							//dosomething
							//�߳��ں�ִ̨������ִ�еĶ���
							//jlabel.setText(content+jingdu+"%......");
							//System.out.println('x'+content+jingdu+"%......");
						  //  System.out.println("���ڽ���:=>"+jingdu+"%......");
							//if(filein.renameTo(filein))
							//{System.out.println("filein="+"�ļ� û�б�ռ��");
								
							//}
							//else System.out.println("\nfilein���ļ��Ѿ���ռ��");
							yijian_jiami_shuchu=new Yijian_jiami_shuchu(filein,passowrd,l1);
							//System.out.println("filein.getPath="+filein.getPath()
						   try {
								choose();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					};
					t.start();
					
				}
			}//end if match
			else {
				//System.out.println("���Ե�......");
				main.show_dialog04();
			}
		}
		
		public void choose() throws IOException
		{ boolean isOverWriteFile=false;
		 //boolean isFileExited=false;
		 String deleteFilePath=null;
			//String file_geshi="û���ļ���չ��";
			String fileName=filein.getName();
		    //ÿһ�ε���֮ǰ���������´����ļ�(����·��)
		    if(!fileName.endsWith(".tingfeng"))
		    {deleteFilePath=filein.getPath().replace("\\\\", "\\\\\\\\")+".tingfeng";
		    	if(new File(deleteFilePath).isFile())
		    	{ 
		    		if(isOverWriteFile=main.show_dialog09(fileName+".tingfeng"))	
		    		{   yijian_jiami_shuchu.yijianjiami(isOverWriteFile,true,deleteFilePath);
		    		}
		    		
		    	}
		    	else
		    		yijian_jiami_shuchu.yijianjiami(true,false,deleteFilePath);
		    //	System.out.println("����һ������"+filein.getPath()+"\tpassword:"+passowrd+"\tJlabel"+l1.toString());
		    	
		    }
		    else { 
		    	deleteFilePath=filein.getPath().replace("\\\\", "\\\\\\\\").substring(0, filein.getPath().length()-9);
		    	
		    	if(new File(deleteFilePath).isFile())
		    	{
		    		if(isOverWriteFile=main.show_dialog08(fileName.substring(0, fileName.length()-9)))	
		    		{   yijian_jiami_shuchu.yijianjiemi(isOverWriteFile,true,deleteFilePath);
		    		}
		    		//System.out.println("����һ������"+filein.getPath()+"\tpassword:"+passowrd+"\tJlabel"+l1.toString());
			    }
		    	else
		    		yijian_jiami_shuchu.yijianjiemi(true,false,deleteFilePath);
				//System.out.println("����������ļ�(����·������):"+outName);	
			}
		    }


}
