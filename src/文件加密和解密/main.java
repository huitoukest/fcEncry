package 文件加密和解密;


import java.awt.Dimension;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class main {
static JFrame frame;
public static void main(String[] args) {
		// TODO Auto-generated method stub

		 frame=new JFrame("加密解密by听风");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(400, 200, 450, 150);
	
	
	JTabbedPane jt=new JTabbedPane();
	jt.add("一键加密/解密",new yijian_jiami());
	jt.addTab("自定义加密/解密",new jiami());
	jt.addTab("帮助说明",new help());
	
	//jPanel_jiami1.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.X_AXIS));
	//jPanel_jiami2.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.X_AXIS));	
	
	frame.getContentPane().add(jt);
	frame.pack();
	frame.setVisible(true);
	frame.setResizable(false);//固定窗口的大小
	}
	

public static void show_dialog01()
{JOptionPane.showConfirmDialog(frame, "选取的文件有问题!","注意:", JOptionPane.YES_NO_OPTION);}

public static void show_dialog02()
{JOptionPane.showConfirmDialog(frame, "保存的文件路径有问题!","注意:", JOptionPane.YES_NO_OPTION);	}

public static void show_dialog03()
{JOptionPane.showConfirmDialog(frame, "请输入密码!(1~300位)","注意:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog04()
{JOptionPane.showConfirmDialog(frame, "正在加密/解密,请稍候......","注意:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog05()
{JOptionPane.showConfirmDialog(frame, "密码错误,请重新输入......","注意:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog06()
{JOptionPane.showConfirmDialog(frame, "文件损坏,请重新选择......","注意:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog07()
{JOptionPane.showConfirmDialog(frame, "保存文件的磁盘空间不够用了,请重新选择......","注意:", JOptionPane.YES_NO_OPTION);	}

public static boolean show_dialog08(String s){
	int i=JOptionPane.showConfirmDialog(frame, "文件夹中存在一个解密后的文件"+"\n"+s+"\n需要继续解密覆盖此文件么?","注意:", JOptionPane.YES_NO_OPTION);
	if(i==JOptionPane.OK_OPTION){return true;}
	else return false;
}

public static boolean show_dialog09(String s){
	int i=JOptionPane.showConfirmDialog(frame, "文件夹中存在一个加密后的文件"+"\n"+s+"\n需要继续加密覆盖此文件么?","注意:", JOptionPane.YES_NO_OPTION);
	if(i==JOptionPane.OK_OPTION){return true;}
	else return false;
}

}

//一键加密/解密
class yijian_jiami extends JPanel{
	 public yijian_jiami(){
		    JPanel jPanel_jiami=this;
			JPanel jPanel_jiami1=new JPanel();
			JPanel jPanel_jiami2=new JPanel();
			JPanel jPanel_jiami4=new JPanel();
			//设置加密解密面板的布局,方向
			jPanel_jiami.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.Y_AXIS));
			//jPanel_jiami.setBackground(Color.CYAN);
			jPanel_jiami.setPreferredSize(new Dimension(400, 130));
			jPanel_jiami1.setPreferredSize(new Dimension(320, 20));
			jPanel_jiami2.setPreferredSize(new Dimension(320, 20));
			jPanel_jiami4.setPreferredSize(new Dimension(320,20));
			
			jPanel_jiami1.setLayout(new BoxLayout(jPanel_jiami1, BoxLayout.X_AXIS));
			jPanel_jiami2.setLayout(new BoxLayout(jPanel_jiami2, BoxLayout.X_AXIS));
			jPanel_jiami4.setLayout(new BoxLayout(jPanel_jiami4,BoxLayout.X_AXIS));
			
			//第一行
			Label label1=new Label("请选择文件路径:");
			JTextField l1=new JTextField("加密解密的文件路径 ");
			JButton jb1=new JButton("点击选取文件");
			l1.setSize(50, 10);
			l1.setPreferredSize(new Dimension(50,10));
			
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(3,20)));
			jPanel_jiami1.add(label1);
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(3,20)));
			jPanel_jiami1.add(l1);
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(15,20)));
			jPanel_jiami1.add(jb1);
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(5,20)));
			//jPanel_jiami1.setBorder(new EtchedBorder(Color.cyan, Color.blue));
			//第三行
			
			Label label2=new Label("请输入密码:");
			JTextField l2=new JTextField("输入密码");
			JButton jb2=new JButton("加密/解密");
			
			
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(21,20)));
			jPanel_jiami2.add(label2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(8,20)));
			jPanel_jiami2.add(l2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(16,20)));
			jPanel_jiami2.add(jb2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(28,20)));
			//jPanel_jiami2.setBorder(new EtchedBorder(Color.cyan, Color.blue));
			
			//第一行的监听器
			jb1.addActionListener(new Listener("jb1",l1,l1));//加入监听器
	
			//第四行
			JLabel jt1=new JLabel("进度=>");
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(10,20)));
			jPanel_jiami4.add(jt1);
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(70,20)));
			//jt1.setBorder(new BevelBorder(0, Color.cyan,Color.cyan));
			
			//加密解密专用监听器
			jb2.addActionListener(new Listener3(l2,jt1,l1));
			
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami1);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami2);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami4);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
	 }
}

//加密和解密的面板
class jiami extends JPanel{

	 public jiami(){
		    JPanel jPanel_jiami=this;
			JPanel jPanel_jiami1=new JPanel();
			JPanel jPanel_jiami2=new JPanel();
			JPanel jPanel_jiami3=new JPanel();
			JPanel jPanel_jiami4=new JPanel();
			//设置加密解密面板的布局,方向
			jPanel_jiami.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.Y_AXIS));
			//jPanel_jiami.setBackground(Color.CYAN);
			jPanel_jiami.setPreferredSize(new Dimension(400, 130));
			jPanel_jiami1.setPreferredSize(new Dimension(320, 20));
			jPanel_jiami2.setPreferredSize(new Dimension(320, 20));
			jPanel_jiami3.setPreferredSize(new Dimension(320,20));
			jPanel_jiami4.setPreferredSize(new Dimension(320,20));
			
			jPanel_jiami1.setLayout(new BoxLayout(jPanel_jiami1, BoxLayout.X_AXIS));
			jPanel_jiami2.setLayout(new BoxLayout(jPanel_jiami2, BoxLayout.X_AXIS));
			jPanel_jiami3.setLayout(new BoxLayout(jPanel_jiami3,BoxLayout.X_AXIS));
			jPanel_jiami4.setLayout(new BoxLayout(jPanel_jiami4,BoxLayout.X_AXIS));
			
			//第一行
			Label label1=new Label("请选择文件路径:");
			JTextField l1=new JTextField("加密解密的文件路径 ");
			JButton jb1=new JButton("点击选取文件");
			l1.setSize(50, 10);
			l1.setPreferredSize(new Dimension(50,10));
			
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(3,20)));
			jPanel_jiami1.add(label1);
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(3,20)));
			jPanel_jiami1.add(l1);
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(15,20)));
			jPanel_jiami1.add(jb1);
			jPanel_jiami1.add(Box.createRigidArea(new Dimension(5,20)));
			//jPanel_jiami1.setBorder(new EtchedBorder(Color.cyan, Color.blue));
			//第三行
			
			Label label2=new Label("请输入密码:");
			JTextField l2=new JTextField("输入密码");
			JButton jb2=new JButton("加密/解密");
			
			
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(21,20)));
			jPanel_jiami2.add(label2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(8,20)));
			jPanel_jiami2.add(l2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(16,20)));
			jPanel_jiami2.add(jb2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(28,20)));
			//jPanel_jiami2.setBorder(new EtchedBorder(Color.cyan, Color.blue));
			
			//二行
			Label label3=new Label("保存文件路径:");
			JTextField l3=new JTextField("保存文件路径");
			JButton jb3=new JButton("保存路径");
			jb3.addActionListener(new Listener("jb2",l3,l1));
			//第一行的监听器
			jb1.addActionListener(new Listener("jb1",l3,l1));//加入监听器
			
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(10,20)));
			jPanel_jiami3.add(label3);
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(8,20)));
			jPanel_jiami3.add(l3);
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(16,20)));
			jPanel_jiami3.add(jb3);
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(31,20)));
			
			//第四行
			JLabel jt1=new JLabel("进度=>");
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(10,20)));
			jPanel_jiami4.add(jt1);
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(70,20)));
			//jt1.setBorder(new BevelBorder(0, Color.cyan,Color.cyan));
			
			//加密解密专用监听器
			jb2.addActionListener(new Listener2(l2,jt1,l1, l3));
			
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami1);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami3);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami2);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
			jPanel_jiami.add(jPanel_jiami4);
			jPanel_jiami.add(Box.createRigidArea(new Dimension(350,5)));
	 }}
	 //帮助和说明的面板
	 class help extends JPanel{
		 public help (){
			 JPanel jpanel_help1=this;
			// jpanel_help1.setLayout(null);
			 jpanel_help1.setPreferredSize(new Dimension(400,130));
			
			 
			 String shuoming01="您好,现在使用的软件是听风所做,一切解释权归听风所有!";
			String shuoming02="关于软件:本软件是一个文件加密解密程序,目前密码长度限制在300位以\n内!";
			String shuoming03="本程序在加密/解密的时候对\".tingfeng\"文件会自动进行解密处理\n其他文件自动加密.\t";
			String shuoming04="最后,本程序留有留有密码正确性的检"
					+ "\n测,但是不排除有错误密码通过检验并解密出错误文件的情况!"
					+ "\n版本:1.3.1(注:和1.3之前的版本不兼容)"
					+ "\n意见和建议:1935590190@qq.com(20140604)";
			 JLabel jLabel01=new JLabel(shuoming01);
			// JLabel jLabel02=new JLabel(shuoming02);
			 //JLabel jLabel03=new JLabel(shuoming03);
			// JLabel jLabel04=new JLabel(shuoming04);
			JTextArea ja=new JTextArea(shuoming02+shuoming03+shuoming04);
			 ja.setEditable(false);
			 ja.setPreferredSize(new Dimension(380,120));
			 jpanel_help1.add(jLabel01);
			 //jpanel_help1.add(jLabel02);
			 //jpanel_help1.add(jLabel03);
			 //jpanel_help1.add(jLabel04);
			 jpanel_help1.add(ja);
			// jpanel_help1.setPreferredSize(new Dimension(500,300) );
		 }
	
	 
	 }




