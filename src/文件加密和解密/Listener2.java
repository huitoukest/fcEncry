package �ļ����ܺͽ���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTextField;


//����������ڼ��ܽ��ܰ�ť�ļ���,ͬʱ��ǰ�������ݽ����жϺʹ���,�˼�����ֻ����jb2
public class Listener2 implements ActionListener{
JTextField jField;
JLabel l1;
JTextField input;
JTextField output;
String s_input=null;
String s_output=null;
String passowrd=null;
jiami_shuchu jiami_jiemi;	
File filein;
File fileout;
	//�������,�Լ������ı���,�������
	public Listener2(JTextField jField,JLabel l1,JTextField input,JTextField output){
	
		this.jField=jField;
	    this.l1=l1;
	    this.input=input;
		this.output=output;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(l1.getText().regionMatches(0, "����", 0, 2)||l1.getText().regionMatches(0, "���", 0, 2))
		{//System.out.println("ƥ��ɹ�");
		passowrd=jField.getText();
		s_input=input.getText();
		s_output=output.getText();
		try{
		filein=new File(s_input.replaceAll("\\\\","\\\\\\\\"));
		fileout=new File(s_output.replaceAll("\\\\","\\\\\\\\"));
		}//����ļ���·������Ч��
		catch(Exception exception)
		{
			
		}
		
		if(!filein.canRead()||!filein.isFile())
			{
				main.show_dialog01();
			}
			else if(!fileout.canWrite()||!fileout.isDirectory()) {
				//System.out.println("�����·��������!");
				main.show_dialog02();
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
						choose(filein,fileout);
					}
				};
				t.start();
				
			}
			//System.out.println("password:="+passowrd);
		//	System.out.println("s_input:="+s_input);
			//System.out.println("s_output:="+s_output);
		
		}//end if match
		else {
			//System.out.println("���Ե�......");
			main.show_dialog04();
		}
	}
	
	public void choose(File filein,File fileout)
	{
		String file_geshi="û���ļ���չ��";
		String fileName=filein.getName();
		//��֤����ļ�·�������Ƶ���ȷ��
		String outName=fileout.getPath();
		if((fileout.getPath().charAt(fileout.getPath().length()-1))!='\\')
		{outName=outName+"\\";
			outName=outName.replaceAll("\\\\", "\\\\\\\\");
		}
		
		int lastPoint=fileName.lastIndexOf('.');
	    //ÿһ�ε���֮ǰ���������´����ļ�(����·��)
		if(lastPoint==-1)
	    {   outName=outName+fileName+".tingfeng";
	    fileout=new File(outName);
	    jiami_jiemi=new jiami_shuchu(filein, fileout, passowrd,l1);	
	    jiami_jiemi.jiami_diyici();
	    }
	    
	    else {
	    	file_geshi=fileName.substring(lastPoint);//������-1,��ʾ�����ļ���
		}
			 
	    if(lastPoint!=-1&&file_geshi.equals(".tingfeng"))
	    {
	    	outName=outName+fileName.substring(0, lastPoint);
	    	fileout=new File(outName);
		    jiami_jiemi=new jiami_shuchu(filein, fileout, passowrd,l1);
	    	jiami_jiemi.jiemi_diyici();
	    	
	    }
	    else if(lastPoint!=-1) {
	    	outName=outName+fileName+".tingfeng";
	    	fileout=new File(outName);
		    jiami_jiemi=new jiami_shuchu(filein, fileout, passowrd,l1);
	    	jiami_jiemi.jiami_diyici();
		}
			//System.out.println("����������ļ�(����·������):"+outName);	
		}

}
