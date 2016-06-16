package 文件加密和解密;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
//这个主要是打开文件和保存文件的监听器
public class Listener implements ActionListener{
String buttonName;//传入按钮的名称
private JFileChooser fc;///文件选择对话框
File fl;
String s_output;
JTextField textField1;
JTextField textField3;
//JTextField textField2;
	//JButton jButton;
	//public Listener(JButton jButton){
	//this.jButton=jButton;
//}
public Listener(String buttonName,JTextField textField3,JTextField textField1){//按钮名称,保存路径,输入路径
	this.buttonName=buttonName;
    this.textField1=textField1;
    this.textField3=textField3;
    //this.textField2=textField2;
	
	fc=null;
	fl=null;
	s_output=null;
}
	//对传进来的jButton调用
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//jb1,jb2分别表示第一行,第二行的按钮,一次类推
		//打开文件
		if(buttonName.equalsIgnoreCase("jb1"))
		{
			//System.out.println("jb1");
			//打开文件选取框
			JFileChooser file = new JFileChooser();
			//是否可多选  
	        file.setMultiSelectionEnabled(false);  
	        //选择模式，可选择文件
	        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
	      //设置文件筛选器  
	        file.setAcceptAllFileFilterUsed(false);
	      //加入多种格式,方便筛选 
	        file.setFileFilter(new MyFileFileter(new String[]{"常见图片格式:",
	        		".jpg",".jpeg",".raw",".gif",".psd",".png",".raw"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"常见音频格式:",
	        		".mp3",".wma",".ogg",".flac",".ape",".wmv",".wav",".acc",".arm"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"常见视频格式:",
	        		".mpeg",".avi",".mov",".asf",".wmv",".navi",".3gp",
	        		".rm",".rmvb",".flv",".f4v",".swf",".mkv",".qt",".divx",".MP4"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"常见文档和存储格式:",".rar",".7z",
	        		".zip",".txt",".doc",".wps",".ppt",".xls",".docx",".pptx",".xlsx",".pdf"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"tingfeng加密文档:",".tingfeng"}));
	        file.setFileFilter(new MyFileFileter(new String[]{".*(所有文件)"}));
	        int returnVal=file.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				fl = file.getSelectedFile();
			textField1.setText(fl.getPath());
			//如果不相同,那么给textField3重设文件夹路径
			if(!textField1.equals(textField3)){
			textField3.setText(fl.getParent());
			}
			}
		}
		//保存路径
		if(buttonName.equalsIgnoreCase("jb2"))
		{   
			saveFile();
		}
		
	}
	private void saveFile() //保存文件    
    {fc=new JFileChooser();
    //是否可多选  
    fc.setMultiSelectionEnabled(false);  
    //选择模式，文件夹  
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   
    String fileName;    
    //设置保存文件对话框的标题    
    fc.setDialogTitle("保存");     
    //fc.showSaveDialog(null);
    int returnVal = fc.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    	File out=fc.getSelectedFile();
        s_output=out.getPath();//的到文件夹路径
        textField3.setText(s_output);
        //replaceAll参数是正则表达式，"\\"同样表示"\"。
        //s_output=s_output.replaceAll("\\\\", "\\\\\\\\");
    }

    //System.out.println("保存文件的路径是:"+s_output);
    
    }   
}
