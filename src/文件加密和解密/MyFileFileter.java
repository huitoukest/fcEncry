package �ļ����ܺͽ���;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFileter extends FileFilter {

private String s[];
	
	public MyFileFileter(String[] extString)
	{
		s= extString;
	}
	
	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		//return true;
		if(s[0].equalsIgnoreCase(".*(�����ļ�)"))
				return true;
		
		if (f.isDirectory()) {
			return true;
		}//�����ļ���
		
		for (String name  : s) {
			//ȫ��ת����Сд
			if(f.getPath().toLowerCase().endsWith(name.toLowerCase()))
		return true;
		}
		return false;
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
	String s1="";
	for(int i=0;i<s.length-1;i++)
	{
		if(s[i].startsWith("."))
		{
			s1=s1+s[i]+",";
			}
		else s1=s1+s[i];
	}
	s1=s1+s[s.length-1];
		return	s1;
		//return null;
	}

}
