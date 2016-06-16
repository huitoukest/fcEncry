package 文件加密和解密;

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
		//密码框传入,以及进度文本框,方便调用
		public Listener3(JTextField jField,JLabel l1,JTextField input){
		
			this.jField=jField;
		    this.l1=l1;
		    this.input=input;	
		    
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(l1.getText().regionMatches(0, "进度", 0, 2)||l1.getText().regionMatches(0, "完成", 0, 2))
			{//System.out.println("匹配成功");
			passowrd=jField.getText();
			s_input=input.getText();
			try{
			filein=new File(s_input.replaceAll("\\\\","\\\\\\\\"));
			}//检查文件和路径的有效性
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
					//用另外一个线程来使用处理
					Thread t = new Thread() {
						@Override
						public void run() {
							//dosomething
							//线程在后台执行你想执行的东西
							//jlabel.setText(content+jingdu+"%......");
							//System.out.println('x'+content+jingdu+"%......");
						  //  System.out.println("正在解密:=>"+jingdu+"%......");
							//if(filein.renameTo(filein))
							//{System.out.println("filein="+"文件 没有被占用");
								
							//}
							//else System.out.println("\nfilein中文件已经被占用");
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
				//System.out.println("请稍等......");
				main.show_dialog04();
			}
		}
		
		public void choose() throws IOException
		{ boolean isOverWriteFile=false;
		 //boolean isFileExited=false;
		 String deleteFilePath=null;
			//String file_geshi="没有文件扩展名";
			String fileName=filein.getName();
		    //每一次调用之前都必修重新传入文件(包括路径)
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
		    //	System.out.println("调用一键加密"+filein.getPath()+"\tpassword:"+passowrd+"\tJlabel"+l1.toString());
		    	
		    }
		    else { 
		    	deleteFilePath=filein.getPath().replace("\\\\", "\\\\\\\\").substring(0, filein.getPath().length()-9);
		    	
		    	if(new File(deleteFilePath).isFile())
		    	{
		    		if(isOverWriteFile=main.show_dialog08(fileName.substring(0, fileName.length()-9)))	
		    		{   yijian_jiami_shuchu.yijianjiemi(isOverWriteFile,true,deleteFilePath);
		    		}
		    		//System.out.println("调用一键解密"+filein.getPath()+"\tpassword:"+passowrd+"\tJlabel"+l1.toString());
			    }
		    	else
		    		yijian_jiami_shuchu.yijianjiemi(true,false,deleteFilePath);
				//System.out.println("输出的完整文件(包含路径名称):"+outName);	
			}
		    }


}
