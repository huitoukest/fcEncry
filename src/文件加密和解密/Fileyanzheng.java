package �ļ����ܺͽ���;

/**�ļ���������֤�뽫���ļ������ݼ��������֤�룬�����ļ����(������������֤��)*/
public class Fileyanzheng {
	/**b��Ϊ128λ�ļ���������֤���ֽ�����*/
private	byte[] b;
//	/**output������ļ����ֽ�����*/
//private	byte[] outPut;
	/**k�ǵ�ǰ�ֽ�����b�±��λ��*/
private	int k;
/**�����ļ��������֤�ֽ�����ĳ���*/
private long fileLength;

	public Fileyanzheng(long fileLength)
	{
		b=new byte[128];
		for(int i=0;i<128;i++)
			b[i]=(byte) i;
		this.fileLength=fileLength;
	}
	/**�õ����յ�������֤��*/
	public byte[] getYanzheng()
	{
		return b;
	}
   /**��������ֽ�����͵�ǰ���ֽ������������㣬������µ���֤��*/
	public void addByte(byte[] outPut,int length)
	{
		for(int i=0;i<length;i++)
		{
			if(k==128)
			{ k=0; }
			b[k]=(byte) (b[k]+outPut[i]^fileLength);
		    k++;
		}
	}
	
	public void clearAll(long fileLength)
	{
		b=new byte[128];
		for(int i=0;i<128;i++)
			b[i]=(byte) i;
		this.fileLength=fileLength;
	}
}

