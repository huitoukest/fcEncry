package �ļ����ܺͽ���;


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

		 frame=new JFrame("���ܽ���by����");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setBounds(400, 200, 450, 150);
	
	
	JTabbedPane jt=new JTabbedPane();
	jt.add("һ������/����",new yijian_jiami());
	jt.addTab("�Զ������/����",new jiami());
	jt.addTab("����˵��",new help());
	
	//jPanel_jiami1.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.X_AXIS));
	//jPanel_jiami2.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.X_AXIS));	
	
	frame.getContentPane().add(jt);
	frame.pack();
	frame.setVisible(true);
	frame.setResizable(false);//�̶����ڵĴ�С
	}
	

public static void show_dialog01()
{JOptionPane.showConfirmDialog(frame, "ѡȡ���ļ�������!","ע��:", JOptionPane.YES_NO_OPTION);}

public static void show_dialog02()
{JOptionPane.showConfirmDialog(frame, "������ļ�·��������!","ע��:", JOptionPane.YES_NO_OPTION);	}

public static void show_dialog03()
{JOptionPane.showConfirmDialog(frame, "����������!(1~300λ)","ע��:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog04()
{JOptionPane.showConfirmDialog(frame, "���ڼ���/����,���Ժ�......","ע��:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog05()
{JOptionPane.showConfirmDialog(frame, "�������,����������......","ע��:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog06()
{JOptionPane.showConfirmDialog(frame, "�ļ���,������ѡ��......","ע��:", JOptionPane.YES_NO_OPTION);	}


public static void show_dialog07()
{JOptionPane.showConfirmDialog(frame, "�����ļ��Ĵ��̿ռ䲻������,������ѡ��......","ע��:", JOptionPane.YES_NO_OPTION);	}

public static boolean show_dialog08(String s){
	int i=JOptionPane.showConfirmDialog(frame, "�ļ����д���һ�����ܺ���ļ�"+"\n"+s+"\n��Ҫ�������ܸ��Ǵ��ļ�ô?","ע��:", JOptionPane.YES_NO_OPTION);
	if(i==JOptionPane.OK_OPTION){return true;}
	else return false;
}

public static boolean show_dialog09(String s){
	int i=JOptionPane.showConfirmDialog(frame, "�ļ����д���һ�����ܺ���ļ�"+"\n"+s+"\n��Ҫ�������ܸ��Ǵ��ļ�ô?","ע��:", JOptionPane.YES_NO_OPTION);
	if(i==JOptionPane.OK_OPTION){return true;}
	else return false;
}

}

//һ������/����
class yijian_jiami extends JPanel{
	 public yijian_jiami(){
		    JPanel jPanel_jiami=this;
			JPanel jPanel_jiami1=new JPanel();
			JPanel jPanel_jiami2=new JPanel();
			JPanel jPanel_jiami4=new JPanel();
			//���ü��ܽ������Ĳ���,����
			jPanel_jiami.setLayout(new BoxLayout(jPanel_jiami, BoxLayout.Y_AXIS));
			//jPanel_jiami.setBackground(Color.CYAN);
			jPanel_jiami.setPreferredSize(new Dimension(400, 130));
			jPanel_jiami1.setPreferredSize(new Dimension(320, 20));
			jPanel_jiami2.setPreferredSize(new Dimension(320, 20));
			jPanel_jiami4.setPreferredSize(new Dimension(320,20));
			
			jPanel_jiami1.setLayout(new BoxLayout(jPanel_jiami1, BoxLayout.X_AXIS));
			jPanel_jiami2.setLayout(new BoxLayout(jPanel_jiami2, BoxLayout.X_AXIS));
			jPanel_jiami4.setLayout(new BoxLayout(jPanel_jiami4,BoxLayout.X_AXIS));
			
			//��һ��
			Label label1=new Label("��ѡ���ļ�·��:");
			JTextField l1=new JTextField("���ܽ��ܵ��ļ�·�� ");
			JButton jb1=new JButton("���ѡȡ�ļ�");
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
			//������
			
			Label label2=new Label("����������:");
			JTextField l2=new JTextField("��������");
			JButton jb2=new JButton("����/����");
			
			
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(21,20)));
			jPanel_jiami2.add(label2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(8,20)));
			jPanel_jiami2.add(l2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(16,20)));
			jPanel_jiami2.add(jb2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(28,20)));
			//jPanel_jiami2.setBorder(new EtchedBorder(Color.cyan, Color.blue));
			
			//��һ�еļ�����
			jb1.addActionListener(new Listener("jb1",l1,l1));//���������
	
			//������
			JLabel jt1=new JLabel("����=>");
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(10,20)));
			jPanel_jiami4.add(jt1);
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(70,20)));
			//jt1.setBorder(new BevelBorder(0, Color.cyan,Color.cyan));
			
			//���ܽ���ר�ü�����
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

//���ܺͽ��ܵ����
class jiami extends JPanel{

	 public jiami(){
		    JPanel jPanel_jiami=this;
			JPanel jPanel_jiami1=new JPanel();
			JPanel jPanel_jiami2=new JPanel();
			JPanel jPanel_jiami3=new JPanel();
			JPanel jPanel_jiami4=new JPanel();
			//���ü��ܽ������Ĳ���,����
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
			
			//��һ��
			Label label1=new Label("��ѡ���ļ�·��:");
			JTextField l1=new JTextField("���ܽ��ܵ��ļ�·�� ");
			JButton jb1=new JButton("���ѡȡ�ļ�");
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
			//������
			
			Label label2=new Label("����������:");
			JTextField l2=new JTextField("��������");
			JButton jb2=new JButton("����/����");
			
			
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(21,20)));
			jPanel_jiami2.add(label2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(8,20)));
			jPanel_jiami2.add(l2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(16,20)));
			jPanel_jiami2.add(jb2);
			jPanel_jiami2.add(Box.createRigidArea(new Dimension(28,20)));
			//jPanel_jiami2.setBorder(new EtchedBorder(Color.cyan, Color.blue));
			
			//����
			Label label3=new Label("�����ļ�·��:");
			JTextField l3=new JTextField("�����ļ�·��");
			JButton jb3=new JButton("����·��");
			jb3.addActionListener(new Listener("jb2",l3,l1));
			//��һ�еļ�����
			jb1.addActionListener(new Listener("jb1",l3,l1));//���������
			
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(10,20)));
			jPanel_jiami3.add(label3);
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(8,20)));
			jPanel_jiami3.add(l3);
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(16,20)));
			jPanel_jiami3.add(jb3);
			jPanel_jiami3.add(Box.createRigidArea(new Dimension(31,20)));
			
			//������
			JLabel jt1=new JLabel("����=>");
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(10,20)));
			jPanel_jiami4.add(jt1);
			jPanel_jiami4.add(Box.createRigidArea(new Dimension(70,20)));
			//jt1.setBorder(new BevelBorder(0, Color.cyan,Color.cyan));
			
			//���ܽ���ר�ü�����
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
	 //������˵�������
	 class help extends JPanel{
		 public help (){
			 JPanel jpanel_help1=this;
			// jpanel_help1.setLayout(null);
			 jpanel_help1.setPreferredSize(new Dimension(400,130));
			
			 
			 String shuoming01="����,����ʹ�õ��������������,һ�н���Ȩ����������!";
			String shuoming02="�������:�������һ���ļ����ܽ��ܳ���,Ŀǰ���볤��������300λ��\n��!";
			String shuoming03="�������ڼ���/���ܵ�ʱ���\".tingfeng\"�ļ����Զ����н��ܴ���\n�����ļ��Զ�����.\t";
			String shuoming04="���,��������������������ȷ�Եļ�"
					+ "\n��,���ǲ��ų��д�������ͨ�����鲢���ܳ������ļ������!"
					+ "\n�汾:1.3.1(ע:��1.3֮ǰ�İ汾������)"
					+ "\n����ͽ���:1935590190@qq.com(20140604)";
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




