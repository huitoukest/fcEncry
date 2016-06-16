package 文件加密和解密;

/**文件完整性验证码将是文件的数据计算出的验证码，放在文件最后(不包括密码验证码)*/
public class Fileyanzheng {
	/**b作为128位文件完整性验证的字节数组*/
private	byte[] b;
//	/**output输出到文件的字节数组*/
//private	byte[] outPut;
	/**k是当前字节数组b下标的位置*/
private	int k;
/**加密文件不算此验证字节数组的长度*/
private long fileLength;

	public Fileyanzheng(long fileLength)
	{
		b=new byte[128];
		for(int i=0;i<128;i++)
			b[i]=(byte) i;
		this.fileLength=fileLength;
	}
	/**得到最终的数组验证码*/
	public byte[] getYanzheng()
	{
		return b;
	}
   /**将加入的字节数组和当前的字节数组做加运算，计算出新的验证码*/
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

