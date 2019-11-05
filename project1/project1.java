package gyuwon;
import java.io.*;

public class project1 {
	 public static void main(String[] args) throws IOException
	 {
	File file1 = new File("file1.txt");	//���� ��ü ����
	File file2 = new File("file2.txt");	//���� ��ü ����
	try {
		BufferedReader fin = new BufferedReader(new FileReader(file1));//fin��ü����
		BufferedReader tin = new BufferedReader(new FileReader(file2));//tin��ü����
		//����ϴ� ������ ����� �����͸� ���۷� �б� ���� ����ߴ�. BufferReader�޼ҵ� ���
		String read;
		while ((read = fin.readLine())!=null) {	//������ ���α��� ����ϵ��� �����Ѵ�.
			System.out.println(read);			//readLine �޼ҵ带 ����Ͽ� �� �̻� �Է¹��� ���� ���� �� null�� ��ȯ�Ѵ�. �� �� ���� ���
		}
		fin.close();
		while ((read = tin.readLine())!=null) { //������ ���α��� ����ϵ��� �����Ѵ�.
			System.out.println(read);
		}
		tin.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.err.println("�����̾����....file1.txt�� file2.txt�� �޾��ּ���!");
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
}
}


