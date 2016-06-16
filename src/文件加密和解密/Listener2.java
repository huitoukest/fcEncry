package 文件加密和解密;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTextField;


//这个类是用于加密解密按钮的监听,同时对前面获得数据进行判断和处理,此监听器只用于jb2
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
	//密码框传入,以及进度文本框,方便调用
	public Listener2(JTextField jField,JLabel l1,JTextField input,JTextField output){
	
		this.jField=jField;
	    this.l1=l1;
	    this.input=input;
		this.output=output;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(l1.getText().regionMatches(0, "进度", 0, 2)||l1.getText().regionMatches(0, "完成", 0, 2))
		{//System.out.println("匹配成功");
		passowrd=jField.getText();
		s_input=input.getText();
		s_output=output.getText();
		try{
		filein=new File(s_input.replaceAll("\\\\","\\\\\\\\"));
		fileout=new File(s_output.replaceAll("\\\\","\\\\\\\\"));
		}//检查文件和路径的有效性
		catch(Exception exception)
		{
			
		}
		
		if(!filein.canRead()||!filein.isFile())
			{
				main.show_dialog01();
			}
			else if(!fileout.canWrite()||!fileout.isDirectory()) {
				//System.out.println("输出的路径有问题!");
				main.show_dialog02();
			}
			
			else if(passowrd==null||passowrd.length()>300)
			{
				main.show_dialog03();
			}
			else {
				//用另外一个线程来使用处理
				Thread t = new Thread() {
					@Override
					public void run() {
						//dosomething
						//线程在后台执行你想执行的东西
						//jlabel.setText(content+jingdu+"%......");
						//System.out.println('x'+content+jingdu+"%......");
					  //  System.out.println("正在解密:=>"+jingdu+"%......");
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
			//System.out.println("请稍等......");
			main.show_dialog04();
		}
	}
	
	public void choose(File filein,File fileout)
	{
		String file_geshi="没有文件扩展名";
		String fileName=filein.getName();
		//保证输出文件路径和名称的正确性
		String outName=fileout.getPath();
		if((fileout.getPath().charAt(fileout.getPath().length()-1))!='\\')
		{outName=outName+"\\";
			outName=outName.replaceAll("\\\\", "\\\\\\\\");
		}
		
		int lastPoint=fileName.lastIndexOf('.');
	    //每一次调用之前都必修重新传入文件(包括路径)
		if(lastPoint==-1)
	    {   outName=outName+fileName+".tingfeng";
	    fileout=new File(outName);
	    jiami_jiemi=new jiami_shuchu(filein, fileout, passowrd,l1);	
	    jiami_jiemi.jiami_diyici();
	    }
	    
	    else {
	    	file_geshi=fileName.substring(lastPoint);//不等于-1,表示存在文件名
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
			//System.out.println("输出的完整文件(包含路径名称):"+outName);	
		}

}
