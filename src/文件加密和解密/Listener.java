package �ļ����ܺͽ���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
//�����Ҫ�Ǵ��ļ��ͱ����ļ��ļ�����
public class Listener implements ActionListener{
String buttonName;//���밴ť������
private JFileChooser fc;///�ļ�ѡ��Ի���
File fl;
String s_output;
JTextField textField1;
JTextField textField3;
//JTextField textField2;
	//JButton jButton;
	//public Listener(JButton jButton){
	//this.jButton=jButton;
//}
public Listener(String buttonName,JTextField textField3,JTextField textField1){//��ť����,����·��,����·��
	this.buttonName=buttonName;
    this.textField1=textField1;
    this.textField3=textField3;
    //this.textField2=textField2;
	
	fc=null;
	fl=null;
	s_output=null;
}
	//�Դ�������jButton����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//jb1,jb2�ֱ��ʾ��һ��,�ڶ��еİ�ť,һ������
		//���ļ�
		if(buttonName.equalsIgnoreCase("jb1"))
		{
			//System.out.println("jb1");
			//���ļ�ѡȡ��
			JFileChooser file = new JFileChooser();
			//�Ƿ�ɶ�ѡ  
	        file.setMultiSelectionEnabled(false);  
	        //ѡ��ģʽ����ѡ���ļ�
	        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
	      //�����ļ�ɸѡ��  
	        file.setAcceptAllFileFilterUsed(false);
	      //������ָ�ʽ,����ɸѡ 
	        file.setFileFilter(new MyFileFileter(new String[]{"����ͼƬ��ʽ:",
	        		".jpg",".jpeg",".raw",".gif",".psd",".png",".raw"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"������Ƶ��ʽ:",
	        		".mp3",".wma",".ogg",".flac",".ape",".wmv",".wav",".acc",".arm"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"������Ƶ��ʽ:",
	        		".mpeg",".avi",".mov",".asf",".wmv",".navi",".3gp",
	        		".rm",".rmvb",".flv",".f4v",".swf",".mkv",".qt",".divx",".MP4"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"�����ĵ��ʹ洢��ʽ:",".rar",".7z",
	        		".zip",".txt",".doc",".wps",".ppt",".xls",".docx",".pptx",".xlsx",".pdf"}));
	        file.setFileFilter(new MyFileFileter(new String[]{"tingfeng�����ĵ�:",".tingfeng"}));
	        file.setFileFilter(new MyFileFileter(new String[]{".*(�����ļ�)"}));
	        int returnVal=file.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				fl = file.getSelectedFile();
			textField1.setText(fl.getPath());
			//�������ͬ,��ô��textField3�����ļ���·��
			if(!textField1.equals(textField3)){
			textField3.setText(fl.getParent());
			}
			}
		}
		//����·��
		if(buttonName.equalsIgnoreCase("jb2"))
		{   
			saveFile();
		}
		
	}
	private void saveFile() //�����ļ�    
    {fc=new JFileChooser();
    //�Ƿ�ɶ�ѡ  
    fc.setMultiSelectionEnabled(false);  
    //ѡ��ģʽ���ļ���  
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   
    String fileName;    
    //���ñ����ļ��Ի���ı���    
    fc.setDialogTitle("����");     
    //fc.showSaveDialog(null);
    int returnVal = fc.showOpenDialog(null);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    	File out=fc.getSelectedFile();
        s_output=out.getPath();//�ĵ��ļ���·��
        textField3.setText(s_output);
        //replaceAll������������ʽ��"\\"ͬ����ʾ"\"��
        //s_output=s_output.replaceAll("\\\\", "\\\\\\\\");
    }

    //System.out.println("�����ļ���·����:"+s_output);
    
    }   
}
