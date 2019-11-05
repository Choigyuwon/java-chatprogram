package gyuwon;
import java.io.*;

public class project1 {
	 public static void main(String[] args) throws IOException
	 {
	File file1 = new File("file1.txt");	//파일 객체 생성
	File file2 = new File("file2.txt");	//파일 객체 생성
	try {
		BufferedReader fin = new BufferedReader(new FileReader(file1));//fin객체생성
		BufferedReader tin = new BufferedReader(new FileReader(file2));//tin객체생성
		//기억하는 공간을 만들어 데이터를 버퍼로 읽기 위해 사용했다. BufferReader메소드 사용
		String read;
		while ((read = fin.readLine())!=null) {	//마지막 라인까지 출력하도록 설정한다.
			System.out.println(read);			//readLine 메소드를 사용하여 더 이상 입력받을 것이 없을 때 null을 반환한다. 그 전 까지 출력
		}
		fin.close();
		while ((read = tin.readLine())!=null) { //마지막 라인까지 출력하도록 설정한다.
			System.out.println(read);
		}
		tin.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.err.println("파일이없어요....file1.txt와 file2.txt를 받아주세요!");
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
}
}


